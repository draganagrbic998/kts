package com.example.demo.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constants.Constants;
import com.example.demo.constants.ImageConstants;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

	@Autowired
	private ImageService imageService;
	
	@MockBean
	private ImageRepository imageRepository;
	
	@Test
	public void testStoreValid() throws IOException {
		MultipartFile mpf = this.testingMultipartValid();
		Mockito.when(this.imageRepository.count()).thenReturn((long) ImageConstants.IMAGE_NUM);
		String path = this.imageService.store(mpf);
		Mockito.when(this.imageRepository.count()).thenReturn((long) ImageConstants.IMAGE_NUM);
		long size = this.imageRepository.count();
		assertEquals(Constants.BACKEND_URL + "/image" + size + ".jpg", path);
		File f = new File(Constants.STATIC_FOLDER + File.separatorChar + "image" + size + ".jpg");
		f.delete();
	}
	
	@Test
	public void testStoreInvalidNullOrigName() throws IOException {
		MultipartFile mpf = this.testingMultipartInvalidNullOrigName();
		Mockito.when(this.imageRepository.count()).thenReturn((long) ImageConstants.IMAGE_NUM);
		String path = this.imageService.store(mpf);
		assertEquals(null, path);
	}
	
	@Test
	public void testStoreInvalidBlankOrigName() throws IOException {
		MultipartFile mpf = this.testingMultipartInvalidBlankOrigName();
		Mockito.when(this.imageRepository.count()).thenReturn((long) ImageConstants.IMAGE_NUM);
		String path = this.imageService.store(mpf);
		assertEquals(null, path);
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
