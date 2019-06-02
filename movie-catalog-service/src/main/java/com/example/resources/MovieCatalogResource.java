package com.example.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.feignproxy.MovieInfoServiceProxy;
import com.example.feignproxy.RatingDataServiceProxy;
import com.example.models.CatalogItem;
import com.example.models.Movie;
import com.example.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Value("${rating.data.service.url}")
	private String ratingDataServiceUrl;
	
	@Value("${movie.info.service.url}")
	private String movieInfoServiceUrl;
	
	@Autowired
	private RatingDataServiceProxy ratingDataServiceProxy;
	
	@Autowired
	private MovieInfoServiceProxy movieInfoServiceProxy;
	
	private static final Logger LOG = LoggerFactory.getLogger(MovieCatalogResource.class);

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

		LOG.info("starting movie-catalog-service");
		LOG.info(discoveryClient.getServices().toString());
		LOG.info(discoveryClient.getInstances("rating-data-service").toString());
		
		//restClient
		//UserRating ratings = restTemplate.getForObject(ratingDataServiceUrl+userId, UserRating.class);
		
		//feign client
		//UserRating ratings = ratingDataServiceProxy.getUserRating(userId);
		
		//webClient
		UserRating ratings = webClientBuilder.build()
								.get()
								.uri(ratingDataServiceUrl+userId)
								.retrieve()
								.bodyToMono(UserRating.class)
								.block();
		
		return ratings.getUserRating().stream().map(rating -> {
			//Movie movie = restTemplate.getForObject(movieInfoServiceUrl+rating.getMovieId(), Movie.class);
			Movie movie = movieInfoServiceProxy.getMovie(rating.getMovieId());
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
			})
			.collect(Collectors.toList());
		
		
	}
}
