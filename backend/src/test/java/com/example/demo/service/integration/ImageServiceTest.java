package com.example.demo.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constants.Constants;
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
	public void testStoreValid() throws IOException {
		MultipartFile mpf = this.testingMultipartValid();
		String path = this.imageService.store(mpf);
		long size = this.imageRepository.count();
		assertEquals(Constants.BACKEND_URL + "/image" + size + ".jpg", path);
		File f = new File(Constants.STATIC_FOLDER + File.separatorChar + "image" + size + ".jpg");
		f.delete();
	}
	
	@Test
	public void testStoreInvalidNullOrigName() throws IOException {
		MultipartFile mpf = this.testingMultipartInvalidNullOrigName();
		String path = this.imageService.store(mpf);
		assertEquals(null, path);
	}
	
	@Test
	public void testStoreInvalidBlankOrigName() throws IOException {
		MultipartFile mpf = this.testingMultipartInvalidBlankOrigName();
		String path = this.imageService.store(mpf);
		assertEquals(null, path);
	}
	
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
	
	public MultipartFile testingMultipartValid() throws IOException {
		return new MockMultipartFile(ImageConstants.PATH_ONE, "dummyPic.jpg",
	            "image/jpeg",
	            "This is a dummy file content".getBytes(StandardCharsets.UTF_8));
	}
	
	public MultipartFile testingMultipartInvalidBlankOrigName() throws IOException {
		return new MockMultipartFile(ImageConstants.PATH_ONE, "",
	            "image/jpeg",
	            "This is a dummy file content".getBytes(StandardCharsets.UTF_8));
	}
	
	public MultipartFile testingMultipartInvalidNullOrigName() throws IOException {
		return new MockMultipartFile(ImageConstants.PATH_ONE, null,
	            "image/jpeg",
	            "This is a dummy file content".getBytes(StandardCharsets.UTF_8));
	}
}
