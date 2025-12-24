package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BlogCategoryDto;
import com.example.demo.dto.BlogDto;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.Blog;
import com.example.demo.model.BlogCategory;
import com.example.demo.model.User;
import com.example.demo.repo.BlogRepo;
import com.example.demo.repo.ReviewRepo;
import com.example.demo.service.BlogCategoryService;
import com.example.demo.service.BlogService;
import com.example.demo.service.UserService;

@Service
public class BlogServiceImpl implements BlogService{

    private final ReviewRepo reviewRepo;
	@Autowired
	BlogRepo blogRepo;
	
	@Autowired
	BlogCategoryService blCategoryService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper mapper;

    BlogServiceImpl(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }
	
	@Override
	public BlogDto addBlog(BlogDto blogDto, int catid, int userid) {
		// TODO Auto-generated method stub
		
		blogDto.setImage(null);
		blogDto.setCategory(blCategoryService.BlogCategoryById(catid));
		blogDto.setUser(userService.userById(userid));
		return mapper.map(blogRepo.save(mapper.map(blogDto, Blog.class)),BlogDto.class);
	}

	@Override
	public List<BlogDto> viewAllBlogs() {
		// TODO Auto-generated method stub
		
		List<Blog> blogs = blogRepo.findAll();
		return blogs.stream().map(blog->mapper.map(blog, BlogDto.class)).collect(Collectors.toList());
	}

	@Override
	public BlogDto BlogById(int id) {
		// TODO Auto-generated method stub
		Blog blog = blogRepo.findById(id).orElseThrow(()->new ResourceNotFound("Blog", "Id", id));
		
		return mapper.map(blog, BlogDto.class);
	}

	@Override
	public BlogDto updateBlog(BlogDto blogDto,int id, int catid, int userid) {
		// TODO Auto-generated method stub
		Blog blog = blogRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Blog", "Id", id));
		blog.setTitle(blogDto.getTitle());
		blog.setContent(blogDto.getContent());
		blog.setImage(blogDto.getImage());
		blog.setCategory(mapper.map(blCategoryService.BlogCategoryById(catid),BlogCategory.class));
		blog.setUser(mapper.map(userService.userById(userid),User.class));
		

		return mapper.map(blogRepo.save(blog),BlogDto.class);
	}

	@Override
	public void deleteBlog(int id) {
		// TODO Auto-generated method stub
		Blog blog = blogRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Blog", "ID", id));
		
		blogRepo.delete(blog);
	}

	@Override
	public BlogDto imageUpload(BlogDto dto) {
		Blog blog = blogRepo.save(mapper.map(dto, Blog.class));
		return mapper.map(blog, BlogDto.class);
	}
	
}
