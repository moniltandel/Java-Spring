package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReviewDto;
import com.example.demo.exception.APIResponse;
import com.example.demo.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping("/blog/{bid}")
	public ResponseEntity<ReviewDto> addReview(@PathVariable("bid") int bid, @RequestBody ReviewDto review) {
		ReviewDto dto = reviewService.addReview(review, bid, 1);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ReviewDto>> allreviews() {
		List<ReviewDto> dtos = reviewService.allReviews();
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReviewDto> reviewById(@PathVariable("id") int id) {
		ReviewDto reviewDto = reviewService.reviewById(id);
		
		return new ResponseEntity<>(reviewDto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto dto, @PathVariable("id") int id) {
		ReviewDto updatedReview = reviewService.updateReview(dto, id);
		
		return new ResponseEntity<>(updatedReview, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse> deleteReview(@PathVariable("id") int id) {
		reviewService.deleteReview(id);
		
		APIResponse api = new APIResponse();
		api.setMessage("review deleted successfully");
		api.setSuccess("True");
		return new ResponseEntity<>(api, HttpStatus.OK);
	}
	
}
