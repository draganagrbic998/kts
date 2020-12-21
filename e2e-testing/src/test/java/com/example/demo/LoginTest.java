package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.example.demo.constants.Constants;

public class LoginTest {
	
	private WebDriver browser;
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.browser.navigate().to(Constants.LOGIN_PATH);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
	}
	
	@Test
	public void testEmptyForm() throws InterruptedException {
		this.loginPage.loginButtonClick();
		this.loginPage.ensureEmailErrorIsDisplayed();
		this.loginPage.ensurePasswordErrorIsDisplayed();
		assertTrue(this.loginPage.emailInvalidError());
		assertTrue(this.loginPage.passwordEmptyError());
		assertEquals(Constants.LOGIN_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testInvalidEmail() {
		this.loginPage.emailInputFill("dummy");
		this.loginPage.loginButtonClick();
		this.loginPage.ensureEmailErrorIsDisplayed();
		this.loginPage.ensurePasswordErrorIsDisplayed();
		assertTrue(this.loginPage.emailInvalidError());
		assertTrue(this.loginPage.passwordEmptyError());
		assertEquals(Constants.LOGIN_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testNonExistingUser() {
		this.loginPage.emailInputFill("dummy@gmail.com");
		this.loginPage.passwordInputFill("dummy");
		this.loginPage.loginButtonClick();
		this.loginPage.ensureLoginErrorIsDisplayed();
		assertEquals(Constants.LOGIN_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testExistingUserAdmin() {
		this.loginPage.emailInputFill(Constants.ADMIN_EMAIL);
		this.loginPage.passwordInputFill(Constants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapIsDisplayed();
		assertEquals(Constants.HOME_PATH, this.browser.getCurrentUrl());
		//nervira me sto je stavio / na kraj :(
	}
	
	@Test
	public void testExistingUserGuest() {
		this.loginPage.emailInputFill(Constants.GUEST_EMAIL);
		this.loginPage.passwordInputFill(Constants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapIsDisplayed();
		assertEquals(Constants.HOME_PATH, this.browser.getCurrentUrl());
		//nervira me sto je stavio / na kraj :(
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}

}
