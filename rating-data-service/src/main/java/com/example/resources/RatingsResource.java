package com.example.resources;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Rating;
import com.example.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(RatingsResource.class);
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		LOG.info("Starting rataing-data-service");
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		LOG.info("Starting rataing-data-service");
		List<Rating> ratings = Arrays.asList(
				new Rating("1234:"+port, 2),
				new Rating("5678:"+port, 3));
		
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}
}
