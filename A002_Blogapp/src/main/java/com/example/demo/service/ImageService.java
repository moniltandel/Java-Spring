package com.example.demo.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	public String uploadImage(String path,MultipartFile file);
	public InputStream getImage(String path, String imagename);
}
