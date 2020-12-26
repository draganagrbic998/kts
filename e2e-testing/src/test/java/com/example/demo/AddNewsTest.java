package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class AddNewsTest {

	private WebDriver browser;

	private HomePage homePage;
	private LoginPage loginPage;
	private CulturalDetails culturalDetails;
	private CulturalDialog culturalDialog;
	private NewsForm newsForm;
	private NewsDetails newsDetails;
	private ImagesInput imagesInput;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.culturalDetails = PageFactory.initElements(this.browser, CulturalDetails.class);
		this.culturalDialog = PageFactory.initElements(this.browser, CulturalDialog.class);
		this.newsForm = PageFactory.initElements(this.browser, NewsForm.class);
		this.newsDetails = PageFactory.initElements(this.browser, NewsDetails.class);
		this.imagesInput = PageFactory.initElements(this.browser, ImagesInput.class);
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureFormDisplayed();
		this.loginPage.emailInputFill(TestConstants.ADMIN_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapDisplayed();
		this.homePage.toggleButtonClick();
		this.culturalDetails.ensureDetailsDisplayed();
		this.culturalDetails.moreButtonClick();
		this.culturalDialog.ensureToggleDrawerDisplayed();
		this.culturalDialog.toggleDrawerClick();
		this.culturalDialog.ensureSwitchToNewsDisplayed();
		this.culturalDialog.switchToNewsList();
		this.culturalDialog.ensureAddNewsButtonDisplayed();
		this.culturalDialog.addNewsButtonClick();
		this.newsForm.ensureFormDisplayed();
	}

	@Test
	public void testCancel() {
		this.newsForm.cancelButtonClick();
		this.newsForm.ensureDialogClosed();
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}

	@Test
	public void testEmptyText() {
		this.newsForm.textInputFill("");
		this.newsForm.saveButtonClick();
		this.newsForm.ensureTextErrorDisplayed();
		assertTrue(this.newsForm.emptyTextError());
	}

	@Test
	public void testOnlyText() {
		String text = "new news text 1";
		this.newsForm.textInputFill(text);
		this.newsForm.saveButtonClick();
		this.newsForm.ensureDialogClosed();
		this.newsDetails.ensureButtonsDisplayed();
		this.newsDetails.ensureTextDisplayed();
		this.newsDetails.ensureHasNoImages();
		assertEquals(text, this.newsDetails.getText());
	}

	@Test
	public void testOneImage() {
		String text = "new news text 2";
		this.newsForm.textInputFill(text);
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureOneImageDisplayed();
		assertEquals(1, this.imagesInput.imagesCount());

		this.newsForm.saveButtonClick();
		this.newsForm.ensureDialogClosed();
		this.newsDetails.ensureButtonsDisplayed();
		this.newsDetails.ensureTextDisplayed();
		this.newsDetails.ensureHasImages();
		this.newsDetails.ensureImageDisplayed();

		assertEquals(text, this.newsDetails.getText());
	}

	@Test
	public void testMoreImages() {
		String text = "new news text 3";
		this.newsForm.textInputFill(text);
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureOneImageDisplayed();
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureTwoImagesDisplayed();
		assertEquals(2, this.imagesInput.imagesCount());

		this.newsForm.saveButtonClick();
		this.newsForm.ensureDialogClosed();
		this.newsDetails.ensureButtonsDisplayed();
		this.newsDetails.ensureTextDisplayed();
		this.newsDetails.ensureHasImages();
		this.newsDetails.ensureImageDisplayed();

		assertEquals(text, this.newsDetails.getText());
	}

	@Test
	public void testDeleteImage() {
		String text = "new news text 4";
		this.newsForm.textInputFill(text);

		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureOneImageDisplayed();
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureTwoImagesDisplayed();
		assertEquals(2, this.imagesInput.imagesCount());

		this.imagesInput.deleteFirstImage();
		this.imagesInput.ensureOneImageDisplayed();
		assertEquals(1, this.imagesInput.imagesCount());
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureTwoImagesDisplayed();
		assertEquals(2, this.imagesInput.imagesCount());
		this.imagesInput.deleteFirstImage();
		this.imagesInput.ensureOneImageDisplayed();
		assertEquals(1, this.imagesInput.imagesCount());

		this.newsForm.saveButtonClick();
		this.newsForm.ensureDialogClosed();
		this.newsDetails.ensureButtonsDisplayed();
		this.newsDetails.ensureTextDisplayed();
		this.newsDetails.ensureHasImages();
		this.newsDetails.ensureImageDisplayed();

		assertEquals(text, this.newsDetails.getText());
	}

	@After
	public void cleanUp() {
		this.browser.quit();
	}

}
