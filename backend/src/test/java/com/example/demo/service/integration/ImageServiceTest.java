package com.example.demo.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constants.ImageConstants;
import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveValid() {
		long size = this.imageRepository.count();
		Image i = this.testingImage();
		i = this.imageService.save(i);
		assertEquals(size + 1, this.imageRepository.count());
		assertEquals(ImageConstants.NON_EXISTING_PATH, i.getPath());
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullPath() {
		Image i = this.testingImage();
		i.setPath(null);
		this.imageService.save(i);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyPath() {
		Image i = this.testingImage();
		i.setPath("");
		this.imageService.save(i);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlankPath() {
		Image i = this.testingImage();
		i.setPath("  ");
		this.imageService.save(i);
	}
	
	public Image testingImage() {
		Image image = new Image();
		image.setPath(ImageConstants.NON_EXISTING_PATH);
		return image;
	}
	
}
