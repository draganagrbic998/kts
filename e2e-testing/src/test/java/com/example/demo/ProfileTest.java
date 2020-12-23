package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class ProfileTest {

	private WebDriver browser;

	private HomePage homePage;
	private LoginPage loginPage;
	private ProfilePage profilePage;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.profilePage = PageFactory.initElements(this.browser, ProfilePage.class);
	}
	
	@Test
	public void testNoUserGuard() {
		this.browser.navigate().to(TestConstants.HOME_PATH);
		this.homePage.ensureMapIsDisplayed();
		this.homePage.moreButtonClick();
		this.homePage.ensureMenuIsDisplayed();
		this.homePage.loginButtonClick();
		this.loginPage.ensureLoginButtonIsDisplayed();
		this.browser.navigate().to(TestConstants.PROFILE_PATH);
		this.loginPage.ensureLoginButtonIsDisplayed();
		assertEquals(TestConstants.LOGIN_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testAdminGuard() {
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureLoginButtonIsDisplayed();
		this.loginPage.emailInputFill(TestConstants.ADMIN_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapIsDisplayed();
		this.browser.navigate().to(TestConstants.PROFILE_PATH);
		this.loginPage.ensureLoginButtonIsDisplayed();
		assertEquals(TestConstants.LOGIN_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testGuestGuard() {
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureLoginButtonIsDisplayed();
		this.loginPage.emailInputFill(TestConstants.GUEST_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapIsDisplayed();
		this.browser.navigate().to(TestConstants.PROFILE_PATH);
		this.profilePage.ensureSaveButtonIsDisplayed();
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testEmptyForm() {
		this.guestLogin();
		this.profilePage.emailInputFill("");
		this.profilePage.firstNameInputFill("");
		this.profilePage.lastNameInputFill("");
		this.profilePage.saveButtonClick();
		this.profilePage.ensureEmailErrorIsDisplayed();
		this.profilePage.ensureFirstNameErrorIsDisplayed();
		this.profilePage.ensureLastNameErrorIsDisplayed();
		assertTrue(this.profilePage.emailErrorDisplayed());
		assertTrue(this.profilePage.firstNameErrorDisplayed());
		assertTrue(this.profilePage.lastNameErrorDisplayed());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testInvalidEmail() {
		this.guestLogin();
		this.profilePage.emailInputFill("dummy");
		this.profilePage.saveButtonClick();
		this.profilePage.ensureEmailErrorIsDisplayed();
		assertTrue(this.profilePage.emailErrorDisplayed());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testTakenEmail() {
		this.guestLogin();
		this.profilePage.emailInputFill(TestConstants.ADMIN_EMAIL);
		this.profilePage.ensureSaveButtonIsDisplayed();
		this.profilePage.saveButtonClick();
		assertTrue(this.profilePage.emailNotUniqueDisplayed());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testNewPasswordNoOldPassword() {
		this.guestLogin();
		this.profilePage.newPasswordInputFill("dummy");
		this.profilePage.saveButtonClick();
		this.profilePage.ensurePasswordErrorIsDisplayed();
		assertTrue(this.profilePage.oldPasswordError());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testConfirmationPasswordNoOldPassword() {
		this.guestLogin();
		this.profilePage.newPasswordConfirmationInputFill("dummy");
		this.profilePage.saveButtonClick();
		this.profilePage.ensurePasswordErrorIsDisplayed();
		assertTrue(this.profilePage.oldPasswordError());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testEmptyConfirmation() {
		this.guestLogin();
		this.profilePage.oldPasswordInputFill("dummy");
		this.profilePage.newPasswordInputFill("dummy");
		this.profilePage.saveButtonClick();
		this.profilePage.ensurePasswordErrorIsDisplayed();
		assertTrue(this.profilePage.confirmationPasswordError());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testEmptyConfirmationInverse() {
		this.guestLogin();
		this.profilePage.oldPasswordInputFill("dummy");
		this.profilePage.newPasswordConfirmationInputFill("dummy");
		this.profilePage.saveButtonClick();
		this.profilePage.ensurePasswordErrorIsDisplayed();
		assertTrue(this.profilePage.confirmationPasswordError());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testWrongConfirmation() {
		this.guestLogin();
		this.profilePage.oldPasswordInputFill("dummy");
		this.profilePage.newPasswordInputFill("dummy");
		this.profilePage.newPasswordConfirmationInputFill("dummy2");
		this.profilePage.saveButtonClick();
		this.profilePage.ensurePasswordErrorIsDisplayed();
		assertTrue(this.profilePage.confirmationPasswordError());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testUpdateData() {
		this.guestLogin();
		this.profilePage.emailInputFill("asd@gmail.com");
		this.profilePage.firstNameInputFill("asd");
		this.profilePage.lastNameInputFill("asd");
		this.profilePage.saveButtonClick();
		this.profilePage.ensureSaveResponseIsDisplayed();
		assertTrue(this.profilePage.saveSuccess());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
		this.profilePage.emailInputFill(TestConstants.GUEST_EMAIL);
		this.profilePage.firstNameInputFill(TestConstants.GUEST_FIRST_NAME);
		this.profilePage.lastNameInputFill(TestConstants.GUEST_LAST_NAME);
		this.profilePage.saveButtonClick();
		this.profilePage.ensureSaveResponseIsDisplayed();
		assertTrue(this.profilePage.saveSuccess());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testUpdatePasswordWrongOldPassword() {
		this.guestLogin();
		this.profilePage.oldPasswordInputFill("dummy");
		this.profilePage.newPasswordInputFill(TestConstants.NEW_PASSWORD);
		this.profilePage.newPasswordConfirmationInputFill(TestConstants.NEW_PASSWORD);
		this.profilePage.saveButtonClick();
		this.profilePage.ensureSaveResponseIsDisplayed();
		assertTrue(this.profilePage.saveFail());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testUpdatePasswordRightOldPassword() {
		this.guestLogin();
		this.profilePage.oldPasswordInputFill(TestConstants.LOGIN_PASSWORD);
		this.profilePage.newPasswordInputFill(TestConstants.NEW_PASSWORD);
		this.profilePage.newPasswordConfirmationInputFill(TestConstants.NEW_PASSWORD);
		this.profilePage.saveButtonClick();
		this.profilePage.ensureSaveResponseIsDisplayed();
		assertTrue(this.profilePage.saveSuccess());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());
		this.profilePage.oldPasswordInputFill(TestConstants.NEW_PASSWORD);
		this.profilePage.newPasswordInputFill(TestConstants.LOGIN_PASSWORD);
		this.profilePage.newPasswordConfirmationInputFill(TestConstants.LOGIN_PASSWORD);
		this.profilePage.saveButtonClick();
		this.profilePage.ensureSaveResponseIsDisplayed();
		assertTrue(this.profilePage.saveSuccess());
		assertEquals(TestConstants.PROFILE_PATH, this.browser.getCurrentUrl());		
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}
	
	private void guestLogin() {
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureLoginButtonIsDisplayed();
		this.loginPage.emailInputFill(TestConstants.GUEST_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapIsDisplayed();
		this.browser.navigate().to(TestConstants.PROFILE_PATH);
		this.profilePage.ensureSaveButtonIsDisplayed();
	}
	
}
