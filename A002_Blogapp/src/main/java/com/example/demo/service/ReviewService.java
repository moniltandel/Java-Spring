package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ReviewDto;

public interface ReviewService {
	
	public ReviewDto addReview(ReviewDto review,int bid,int uid);
	public List<ReviewDto> allReviews();
	public ReviewDto reviewById(int id);
	public ReviewDto updateReview(ReviewDto review, int id);
	public void deleteReview(int id);
	
}
