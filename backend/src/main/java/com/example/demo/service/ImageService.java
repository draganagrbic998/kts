package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constants.Constants;
import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;

@Component
@Transactional(readOnly = true)
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Transactional(readOnly = true)
	public String store(MultipartFile data) throws FileNotFoundException, IOException {
		long counter = this.imageRepository.count() + 1;
		String[] array = data.getOriginalFilename().split("\\.");
		String extension = array[array.length - 1];
		String fileName = "image" + counter + "." + extension;
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
	
	@Transactional(readOnly = false)
	public void save(Image image) {
		this.imageRepository.save(image);
	}

}
