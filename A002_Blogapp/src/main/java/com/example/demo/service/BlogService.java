package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BlogDto;

public interface BlogService {
	public BlogDto addBlog(BlogDto blogDto,int catid, int userid);
	public List<BlogDto> viewAllBlogs();
	public BlogDto BlogById(int id);
	public BlogDto updateBlog(BlogDto blogDto,int id, int catid, int userid);
	public void deleteBlog(int id);
	public BlogDto imageUpload(BlogDto dto);
}
