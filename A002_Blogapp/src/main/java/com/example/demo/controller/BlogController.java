package com.example.demo.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.BlogDto;
import com.example.demo.exception.APIResponse;
import com.example.demo.service.BlogService;
import com.example.demo.service.ImageService;

@RestController
@RequestMapping("/blog")
public class BlogController {

    
	@Autowired
	BlogService blogService;
	
	@Autowired
	ImageService imageService;
	
	@Value("${project.image}")
	String path;

	
	
	@PostMapping("/category/{catid}")
	public ResponseEntity<BlogDto> addBlog(@RequestBody BlogDto blogDto,@PathVariable("catid") int catid ) {
		BlogDto blogdto = blogService.addBlog(blogDto, catid, 6);
		
		return new ResponseEntity<>(blogdto, HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<List<BlogDto>> viewAllblogs() {
		List<BlogDto> blogs = blogService.viewAllBlogs();
		
		return new ResponseEntity<>(blogs, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BlogDto> blogById(@PathVariable("id") int id) {
		BlogDto dto = blogService.BlogById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PutMapping("/category/{catid}/{blogid}")
	public ResponseEntity<BlogDto> updateBlog(@RequestBody BlogDto dto, @PathVariable("catid") int catid,
					@PathVariable("blogid") int blogid) {
		BlogDto updatedBlog = blogService.updateBlog(dto, blogid, catid, 1);
		return new ResponseEntity<>(updatedBlog, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBlog(@PathVariable("id") int id) {
		blogService.deleteBlog(id);
//		APIResponse resp = new APIResponse();
//		resp.setMessage("blog deleted");
//		resp.setSuccess("true");
		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}
	
	@PostMapping("/upload/{blogid}")
	public ResponseEntity<BlogDto> uploadImage(@PathVariable("blogid") int blogid, @RequestParam("file") MultipartFile file) {
		String imagename = imageService.uploadImage(path, file);
		
		BlogDto dto = blogService.BlogById(blogid);
		dto.setImage(imagename);
		
		BlogDto updatedblog = blogService.imageUpload(dto);
		
		return new ResponseEntity<>(updatedblog, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "image/{imagename}")
	public ResponseEntity<Resource> getImage(@PathVariable("imagename") String imagename) {
		InputStream isr = imageService.getImage(path, imagename);
		Resource resource = new InputStreamResource(isr);
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(resource);
		
	}
}
