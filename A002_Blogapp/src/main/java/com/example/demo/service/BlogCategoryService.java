package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BlogCategoryDto;

public interface BlogCategoryService {   
	public BlogCategoryDto addBlogCategory(BlogCategoryDto blogcatdto);
	public List<BlogCategoryDto> AllBlogCategories();
	public BlogCategoryDto BlogCategoryById(int id);
	public BlogCategoryDto updateBlogCategory(BlogCategoryDto blogcatdto, int id);
	public void deleteBlogCategory(int id);
}
