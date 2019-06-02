package com.example.feignproxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.models.Movie;

@FeignClient(name="movie-info-service")
public interface MovieInfoServiceProxy {
	
	@RequestMapping("/movies/{movieId}")
	public Movie getMovie(@PathVariable(value="movieId") String movieId);
	

}
