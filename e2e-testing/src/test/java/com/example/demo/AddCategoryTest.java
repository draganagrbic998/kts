package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class AddCategoryTest {
	
	private WebDriver browser;
	
	private HomePage homePage;
	private LoginPage loginPage;
	private CatTypeDialog catTypeDialog;
	private CategoryForm categoryForm;
	private CatTypeList catTypeList;
	
	@Before
	public void setUp() {
	  	System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.catTypeDialog = PageFactory.initElements(this.browser, CatTypeDialog.class);
		this.categoryForm = PageFactory.initElements(this.browser, CategoryForm.class);
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
		this.homePage.ensureCatsButtonDisplayed();
		this.homePage.categoriesButtonClick();
		this.catTypeDialog.ensureCategoryTypeFormTabDisplayed();
		this.catTypeDialog.ensureCategoryTypeFormTabDisplayed();
		this.catTypeDialog.newCatTypeTabClick();
		this.categoryForm.ensureFormDisplayed();
	}
	
	@Test
	public void testCancel() {
		this.categoryForm.cancelButtonClick();
		this.categoryForm.ensureDialogClosed();
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}

	@Test
	public void testEmptyName() {
		this.categoryForm.nameInputFill("");
		this.categoryForm.saveButtonClick();
		assertTrue(this.categoryForm.emptyNameError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testBlankName() {
		this.categoryForm.nameInputFill("  ");
		this.categoryForm.saveButtonClick();
		assertTrue(this.categoryForm.emptyNameError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testTakenName() {
		this.categoryForm.nameInputFill(TestConstants.TAKEN_CATEGORY_NAME);
		this.categoryForm.saveButtonClick();
		assertTrue(this.categoryForm.takenNameError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testValid() {
		String name = "new";
		this.categoryForm.nameInputFill(name);
		this.categoryForm.saveButtonClick();
		this.homePage.ensureSnackBarDisplayed();
		assertEquals("Category successfully added!", this.homePage.snackBarText());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
		this.catTypeDialog.ensureCategoryTypeListTabDisplayed();
		this.catTypeDialog.catListTabClick();
		this.catTypeList.ensureFirstPage();
		this.catTypeList.ensureNextButtonDisplayed();
		this.catTypeList.nextButtonClick();
		this.catTypeList.ensureLastPage();
		assertTrue(this.catTypeList.catsTypesNamesContainParam(name));
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}

}
