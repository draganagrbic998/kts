package com.example.demo.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuthControllerTest.class, CategoryControllerTest.class, CommentControllerTest.class,
		CulturalOfferControllerTest.class, NewsControllerTest.class, TypeControllerTest.class, UserControllerTest.class,
		UserFollowingControllerTest.class })
public class ControllerTests {

}
