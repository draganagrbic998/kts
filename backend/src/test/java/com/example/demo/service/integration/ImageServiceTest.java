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
	public void testAddValid() {
		long size = this.imageRepository.count();
		Image image = this.testingImage();
		image = this.imageService.save(image);
		assertEquals(size + 1, this.imageRepository.count());
		assertEquals(ImageConstants.NON_EXISTING_PATH, image.getPath());
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullPath() {
		Image image = this.testingImage();
		image.setPath(null);
		this.imageService.save(image);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddEmptyPath() {
		Image image = this.testingImage();
		image.setPath("");
		this.imageService.save(image);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddBlankPath() {
		Image image = this.testingImage();
		image.setPath("  ");
		this.imageService.save(image);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateValid() {
		long size = this.imageRepository.count();
		Image image = this.testingImage();
		image.setId(ImageConstants.ID_ONE);
		image = this.imageService.save(image);
		assertEquals(size, this.imageRepository.count());
		assertEquals(ImageConstants.NON_EXISTING_PATH, image.getPath());
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testUpdateNullPath() {
		Image image = this.testingImage();
		image.setId(ImageConstants.ID_ONE);
		image.setPath(null);
		this.imageService.save(image);
		this.imageRepository.count();
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testUpdateEmptyPath() {
		Image image = this.testingImage();
		image.setId(ImageConstants.ID_ONE);
		image.setPath("");
		this.imageService.save(image);
		this.imageRepository.count();
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testUpdateBlankPath() {
		Image image = this.testingImage();
		image.setId(ImageConstants.ID_ONE);
		image.setPath("  ");
		this.imageService.save(image);
		this.imageRepository.count();
	}
	
	private Image testingImage() {
		Image image = new Image();
		image.setPath(ImageConstants.NON_EXISTING_PATH);
		return image;
	}
	
}
