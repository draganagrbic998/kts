package com.example.demo.service.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CategoryServiceTest.class, CommentServiceTest.class, CulturalOfferService.class, EmailServiceTest.class,
		ImageServiceTest.class, NewsServiceTest.class, TypeServiceTest.class, UserFollowingServiceTest.class,
		UserServiceTest.class })
public class ServiceIntegrationTests {

}
