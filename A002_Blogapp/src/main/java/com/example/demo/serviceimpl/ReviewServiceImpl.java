package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ReviewDto;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.Review;
import com.example.demo.repo.ReviewRepo;
import com.example.demo.service.BlogService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewRepo reviewRepo;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public ReviewDto addReview(ReviewDto dto,int bid, int uid) {
		// TODO Auto-generated method stub
		
		dto.setBlog(blogService.BlogById(bid));
		dto.setUser(userService.userById(uid));
		
		Review review = mapper.map(dto, Review.class);
		
		
		return mapper.map(reviewRepo.save(review), ReviewDto.class);
	}

	@Override
	public List<ReviewDto> allReviews() {
		// TODO Auto-generated method stub
		
		List<Review> reviews = reviewRepo.findAll();
		
		return reviews.stream().map(review->mapper.map(review, ReviewDto.class)).collect(Collectors.toList());
	}

	@Override
	public ReviewDto reviewById(int id) {
		// TODO Auto-generated method stub
		
		Review review = reviewRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Review", "ID", id));
		return mapper.map(review, ReviewDto.class);
	}

	@Override
	public ReviewDto updateReview(ReviewDto dto, int id) {
		// TODO Auto-generated method stub
		Review review = reviewRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Review", "ID", id));
		review.setTitle(dto.getTitle());
		
		Review updatedReview = reviewRepo.save(review);
		
		return mapper.map(updatedReview, ReviewDto.class);
	}

	@Override
	public void deleteReview(int id) {
		// TODO Auto-generated method stub
		Review review = reviewRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Review", "ID", id));
		reviewRepo.delete(review);
	}


	

}
