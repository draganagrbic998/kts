package com.example.demo.service.integration;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.service.CommentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {
	
	@Autowired
	private CommentService commentService;

}