package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BlogCategoryDto;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.BlogCategory;
import com.example.demo.repo.BlogCategoryRepo;
import com.example.demo.service.BlogCategoryService;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {
	
	@Autowired
	BlogCategoryRepo blogCategoryRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public BlogCategoryDto addBlogCategory(BlogCategoryDto blogcatdto) {
		// TODO Auto-generated method stub
		
		return mapper.map(blogCategoryRepo.save(mapper.map(blogcatdto, BlogCategory.class)),BlogCategoryDto.class);
	}

	@Override
	public List<BlogCategoryDto> AllBlogCategories() {
		// TODO Auto-generated method stub
		List<BlogCategory> categories = blogCategoryRepo.findAll();
		return categories.stream().map(category->mapper.map(category, BlogCategoryDto.class)).collect(Collectors.toList());
	}

	@Override
	public BlogCategoryDto BlogCategoryById(int id) {
		// TODO Auto-generated method stub
		BlogCategory category = blogCategoryRepo.findById(id).orElseThrow(()->new ResourceNotFound("BlogCategory", "Id", id)); 
		return mapper.map(category, BlogCategoryDto.class);
	}

	@Override
	public BlogCategoryDto updateBlogCategory(BlogCategoryDto blogcatdto, int id) {
		// TODO Auto-generated method stub
		
		BlogCategory category = blogCategoryRepo.findById(id).orElseThrow(()->new ResourceNotFound("BlogCategory", "Id", id));
		
		category.setName(blogcatdto.getName());
		
		BlogCategory updatedcategory = blogCategoryRepo.save(category); 
		
		return mapper.map(updatedcategory, BlogCategoryDto.class);
	}

	@Override
	public void deleteBlogCategory(int id) {
		// TODO Auto-generated method stub
		blogCategoryRepo.deleteById(id);
	}
	
}
