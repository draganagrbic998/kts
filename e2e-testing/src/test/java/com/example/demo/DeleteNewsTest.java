package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class DeleteNewsTest {

	private WebDriver browser;

	private HomePage homePage;
	private LoginPage loginPage;
	private CulturalDetails culturalDetails;
	private CulturalDialog culturalDialog;
	private NewsForm newsForm;
	private NewsDetails newsDetails;
	private DeleteConfirmation deleteConfirmation;

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
		this.deleteConfirmation = PageFactory.initElements(this.browser, DeleteConfirmation.class);
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

		this.newsForm.textInputFill("delete news");
		this.newsForm.saveButtonClick();
		this.newsForm.ensureDialogClosed();
		this.newsDetails.ensureButtonsDisplayed();
		this.newsDetails.ensureTextDisplayed();
		this.newsDetails.ensureHasNoImages();

		this.newsDetails.deleteButtonClick();
		this.deleteConfirmation.ensureDialogDisplayed();
	}

	@Test
	public void testCancel() {
		this.deleteConfirmation.cancelButtonClick();
		this.deleteConfirmation.ensureDialogClosed();
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}

	@Test
	public void testConfirm() {
		this.deleteConfirmation.confirmButtonClick();
		this.deleteConfirmation.ensureDialogClosed();
		this.homePage.ensureSnackBarDisplayed();
		assertEquals(TestConstants.ITEM_REMOVED, this.homePage.snackBarText());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}

	@After
	public void cleanUp() {
		this.browser.quit();
	}

}
