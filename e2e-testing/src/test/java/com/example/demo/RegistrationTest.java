package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class RegistrationTest {
	
	private WebDriver browser;
	private RegistrationPage registrationPage;
	
	private static final String SUCCESS = "Your request has been sent! Check your email.";
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.registrationPage = PageFactory.initElements(this.browser, RegistrationPage.class);
		this.browser.navigate().to(TestConstants.REGISTER_PATH);
		this.registrationPage.ensureFormDisplayed();
	}
	
	@Test
	public void testEmptyForm() {
		this.registrationPage.emailInputFill("");
		this.registrationPage.firstNameInputFill("");
		this.registrationPage.lastNameInputFill("");
		this.registrationPage.passwordInputFill("");
		this.registrationPage.registerButtonClick();
		this.registrationPage.ensureEmailErrorDisplayed();
		this.registrationPage.ensureFirstNameErrorDisplayed();
		this.registrationPage.ensureLastNameErrorDisplayed();
		this.registrationPage.ensurePasswordErrorDisplayed();
		assertTrue(this.registrationPage.emptyEmailError());
		assertTrue(this.registrationPage.emptyFirstNameError());
		assertTrue(this.registrationPage.emptyLastNameError());
		assertTrue(this.registrationPage.emptyPasswordError());
		assertEquals(TestConstants.REGISTER_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testBlankForm() {
		this.registrationPage.emailInputFill("  ");
		this.registrationPage.firstNameInputFill("  ");
		this.registrationPage.lastNameInputFill("  ");
		this.registrationPage.passwordInputFill("  ");
		this.registrationPage.registerButtonClick();
		this.registrationPage.ensureEmailErrorDisplayed();
		this.registrationPage.ensureFirstNameErrorDisplayed();
		this.registrationPage.ensureLastNameErrorDisplayed();
		assertTrue(this.registrationPage.emptyEmailError());
		assertTrue(this.registrationPage.emptyFirstNameError());
		assertTrue(this.registrationPage.emptyLastNameError());
		assertTrue(this.registrationPage.emptyPasswordError());
		assertEquals(TestConstants.REGISTER_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testTakenEmail() {
		this.registrationPage.emailInputFill(TestConstants.ADMIN_EMAIL);
		this.registrationPage.registerButtonClick();
		this.registrationPage.ensureEmailErrorDisplayed();
		assertTrue(this.registrationPage.emailTakenError());
		assertEquals(TestConstants.REGISTER_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testWrongPasswordConfirmation() {
		this.registrationPage.passwordInputFill("dummy");
		this.registrationPage.passwordConfirmationInputFill("dummy2");
		this.registrationPage.registerButtonClick();
		this.registrationPage.ensurePasswordConfirmationErrorDisplayed();
		assertTrue(this.registrationPage.confirmationPasswordError());
		assertEquals(TestConstants.REGISTER_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testValid() {
		this.registrationPage.emailInputFill("mmijatovic32@gmail.com");
		this.registrationPage.firstNameInputFill("marko");
		this.registrationPage.lastNameInputFill("mijatovic");
		this.registrationPage.passwordInputFill("marko");
		this.registrationPage.passwordConfirmationInputFill("marko");
		this.registrationPage.registerButtonClick();
		this.registrationPage.ensureSnackBarDisplayed();
		assertEquals(SUCCESS, this.registrationPage.snackBarText());
		assertEquals(TestConstants.REGISTER_PATH, this.browser.getCurrentUrl());
		this.registrationPage.closeSnackBar();
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}
	
	
}
