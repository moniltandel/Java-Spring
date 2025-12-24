package com.example.demo.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Override
	public String uploadImage(String path, MultipartFile file) {
		String imageName = System.currentTimeMillis() + "_" +file.getOriginalFilename();
		
		String imagePath = path + imageName;
		
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		try {
			Files.copy(file.getInputStream(), Paths.get(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageName;
	}

	@Override
	public InputStream getImage(String path, String imagename) {
		String imgpath = path + File.separator + imagename;
		
		InputStream stream = null;
		
		try {
			stream = new FileInputStream(imgpath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}

}
