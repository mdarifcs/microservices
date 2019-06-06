package com.example.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(MovieResource.class);
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		LOG.info("starting movie-resource-service");
		return new Movie(movieId, "Test Name: "+port);
	}
}
