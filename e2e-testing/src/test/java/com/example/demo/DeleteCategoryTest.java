package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class DeleteCategoryTest {
	
	private WebDriver browser;
	
	private HomePage homePage;
	private LoginPage loginPage;
	private CatTypeDetails categoryDetails;
	private CatTypeDialog catTypeDialog;
	private DeleteConfirmation deleteConfirmation;

	@Before
	public void setUp() {
	  	System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.categoryDetails = PageFactory.initElements(this.browser, CatTypeDetails.class);
		this.catTypeDialog = PageFactory.initElements(this.browser, CatTypeDialog.class);
		this.deleteConfirmation = PageFactory.initElements(this.browser, DeleteConfirmation.class);
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureFormDisplayed();
		this.loginPage.emailInputFill(TestConstants.ADMIN_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapDisplayed();
		this.homePage.moreButtonClick();
		this.homePage.ensureCatsTypesButtonDisplayed();
		this.homePage.catsTypesButtonClick();
		this.homePage.ensureCatsButtonDisplayed();
		this.homePage.categoriesButtonClick();
		this.catTypeDialog.ensureCategoryTypeListTabDisplayed();
		this.categoryDetails.ensureDetailsDisplayed();
		
	}
	
	@Test
	public void testCancel() {
		this.categoryDetails.deleteButtonClick();
		this.deleteConfirmation.cancelButtonClick();
		this.deleteConfirmation.ensureDialogClosed();
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testConfirmWithCulturalOffer() {
		this.categoryDetails.deleteButtonClick();
		this.deleteConfirmation.confirmButtonClick();
		this.homePage.ensureSnackBarDisplayed();
		assertEquals(TestConstants.ITEM_REMOVED_ERROR, this.homePage.snackBarText());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testConfirmWithoutCulturalOffer() {
		this.categoryDetails.deleteButtonWithoutCulturalOfferClick();
		this.deleteConfirmation.confirmButtonClick();
		this.homePage.ensureSnackBarDisplayed();
		assertEquals(TestConstants.ITEM_REMOVED, this.homePage.snackBarText());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}
	
}
