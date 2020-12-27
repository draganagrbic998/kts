package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class PaginateCategoryTypeTest {
	
	private WebDriver browser;
	
	private HomePage homePage;
	private LoginPage loginPage;
	private CatTypeDetails catTypeDetails;
	private CatTypeList catTypeList;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.catTypeDetails = PageFactory.initElements(this.browser, CatTypeDetails.class);
		this.catTypeList = PageFactory.initElements(this.browser, CatTypeList.class);
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureFormDisplayed();
		this.loginPage.emailInputFill(TestConstants.ADMIN_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapDisplayed();
		this.homePage.moreButtonClick();
		this.homePage.ensureCatsTypesButtonDisplayed();
		this.homePage.catsTypesButtonClick();
	}
	
	@Test
	public void testPaginationCategories() {
		this.homePage.ensureCatsButtonDisplayed();
		this.homePage.categoriesButtonClick();
		this.catTypeList.ensureFirstPage();

		this.catTypeDetails.ensureDetailsDisplayed();
		assertEquals(10, this.catTypeList.catsTypesCount());

		String name = this.catTypeDetails.nameText();
		this.catTypeList.ensureNextButtonDisplayed();
		this.catTypeList.nextButtonClick();
		this.catTypeDetails.ensureDetailsDisplayed();
		assertNotEquals(name, this.catTypeDetails.nameText());
		assertEquals(2, this.catTypeList.catsTypesCount());
		
		this.catTypeList.ensureLastPage();
		
		name = this.catTypeDetails.nameText();
		this.catTypeList.ensurePreviousButtonDisplayed();
		this.catTypeList.previousButtonClick();
		this.catTypeDetails.ensureDetailsDisplayed();
		assertNotEquals(name, this.catTypeDetails.nameText());
		assertEquals(10, this.catTypeList.catsTypesCount());
		
		this.catTypeList.ensureFirstPage();
	}
	
	@Test
	public void testPaginationTypes() {
		this.homePage.ensureTypesButtonDisplayed();
		this.homePage.typesButtonClick();
		this.catTypeList.ensureFirstPage();

		this.catTypeDetails.ensureDetailsDisplayed();
		assertEquals(10, this.catTypeList.catsTypesCount());

		String name = this.catTypeDetails.nameText();
		this.catTypeList.ensureNextButtonDisplayed();
		this.catTypeList.nextButtonClick();
		this.catTypeDetails.ensureDetailsDisplayed();
		assertNotEquals(name, this.catTypeDetails.nameText());
		assertEquals(2, this.catTypeList.catsTypesCount());
		
		this.catTypeList.ensureLastPage();
		
		name = this.catTypeDetails.nameText();
		this.catTypeList.ensurePreviousButtonDisplayed();
		this.catTypeList.previousButtonClick();
		this.catTypeDetails.ensureDetailsDisplayed();
		assertNotEquals(name, this.catTypeDetails.nameText());
		assertEquals(10, this.catTypeList.catsTypesCount());
		
		this.catTypeList.ensureFirstPage();
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}

}
