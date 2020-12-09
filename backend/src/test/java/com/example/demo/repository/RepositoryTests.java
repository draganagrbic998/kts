package com.example.demo.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountActivationRepositoryTest.class, AuthorityRepositoryTest.class, CategoryRepositoryTest.class,
		CommentRepositoryTest.class, CulturalOfferRepositoryTest.class, ImageRepositoryTest.class,
		NewsRepositoryTest.class, TypeRepositoryTest.class, UserFollowingRepositoryTest.class,
		UserRepositoryTest.class })
public class RepositoryTests {

}
