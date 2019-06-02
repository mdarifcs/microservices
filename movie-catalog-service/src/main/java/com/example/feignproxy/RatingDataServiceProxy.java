package com.example.feignproxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.models.UserRating;

@FeignClient(name="rating-data-service")
public interface RatingDataServiceProxy {
	
	@RequestMapping("/ratingsdata/users/{userId}")
	public UserRating getUserRating(@PathVariable(value="userId") String userId);
	
}
