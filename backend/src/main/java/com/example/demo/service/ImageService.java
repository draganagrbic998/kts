package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constants.Constants;
import com.example.demo.repository.ImageRepository;

@Component
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	public String store(MultipartFile data) throws FileNotFoundException, IOException {
		long counter = this.imageRepository.count() + 1;
		String fileName = "image" + counter + "." + data.getOriginalFilename().split("\\.")[1];
		String path = "src" + File.separatorChar
				+ "main" + File.separatorChar
				+ "resources" + File.separatorChar
				+ "static" + File.separatorChar
				+ fileName;
		File file = new File(path);
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(data.getBytes());
		fout.close();
		return Constants.BACKEND_URL + "/" + fileName;
	}

}
