package com.example.demo.culturals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.example.demo.TestConstants;
import com.example.demo.common.DeleteConfirmation;
import com.example.demo.common.HomePage;
import com.example.demo.common.ImageInput;
import com.example.demo.user.LoginPage;

public class ValidCulturalTest {

	private WebDriver browser;
	
	private HomePage homePage;
	private LoginPage loginPage;
	private CulturalForm culturalForm;
	private CulturalDetails culturalDetails;
	private CulturalDialog culturalDialog;
	private ImageInput imageInput;
	private DeleteConfirmation deleteConfirmation;

	private static final String SUCCESS = "Offer successfully saved!";
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.culturalForm = PageFactory.initElements(this.browser, CulturalForm.class);
		this.culturalDetails = PageFactory.initElements(this.browser, CulturalDetails.class);
		this.culturalDialog = PageFactory.initElements(this.browser, CulturalDialog.class);
		this.imageInput = PageFactory.initElements(this.browser, ImageInput.class);
		this.deleteConfirmation = PageFactory.initElements(this.browser, DeleteConfirmation.class);
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureFormDisplayed();
		this.loginPage.emailInputFill(TestConstants.ADMIN_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapDisplayed();
		this.homePage.addOfferButtonClick();
		this.culturalForm.ensureFormDisplayed();
	}

	@Test
	public void test() {
		//kreiranje
		String type = "museum";
		String name = "dummy";
		String location = "new";
		this.culturalForm.typeInputFill(type);
		this.culturalForm.nameInputFill(name);
		this.culturalForm.locationInputFill(location);
		this.culturalForm.ensureAutocompleteSuggestionDisplayed();
		this.culturalForm.autocompleteSuggestionClick();
		this.imageInput.uploadFile(TestConstants.TEST_IMAGE);
		this.culturalForm.saveButtonClick();
		
		this.culturalForm.ensureDialogClosed();
		this.homePage.ensureSnackBarDisplayed();
		assertEquals(SUCCESS, this.homePage.snackBarText());
		this.homePage.closeSnackBar();
		this.homePage.ensureBalloonDisplayed();
		assertEquals("dummy is placed here!", this.homePage.balloonText());
		this.homePage.toggleButtonClick();
		this.culturalDetails.ensureDetailsDisplayed();
		assertEquals(this.culturalDetails.nameText(), name);
		assertEquals(this.culturalDetails.typeText(), type);
		assertTrue(this.culturalDetails.locationText().toLowerCase().contains(location.toLowerCase()));
		
		//izmena
		this.culturalDetails.moreButtonClick();
		this.culturalDialog.ensureEditButtonDisplayed();
		this.culturalDialog.editButtonClick();
		this.culturalForm.ensureFormDisplayed();
		type = "gallery";
		name = "gummy";
		location = "washington";
		this.culturalForm.typeInputFill(type);
		this.culturalForm.nameInputFill(name);
		this.culturalForm.locationInputFill(location);
		this.culturalForm.ensureAutocompleteSuggestionDisplayed();
		this.culturalForm.autocompleteSuggestionClick();
		this.imageInput.uploadFile(TestConstants.TEST_IMAGE);
		this.culturalForm.saveButtonClick();
		
		this.culturalForm.ensureDialogClosed();
		this.homePage.ensureSnackBarDisplayed();
		assertEquals(SUCCESS, this.homePage.snackBarText());
		this.homePage.closeSnackBar();
		this.culturalDetails.ensureDetailsDisplayed();
		assertEquals(this.culturalDetails.nameText(), name);
		assertEquals(this.culturalDetails.typeText(), type);
		assertTrue(this.culturalDetails.locationText().toLowerCase().contains(location.toLowerCase()));
		
		//brisanje
		this.culturalDetails.moreButtonClick();
		this.culturalDialog.ensureDeleteButtonDisplayed();
		this.culturalDialog.deleteButtonClick();
		this.deleteConfirmation.ensureDialogDisplayed();
		name = this.culturalDetails.nameText();
		this.deleteConfirmation.confirmButtonClick();
		this.deleteConfirmation.ensureDialogClosed();
		this.culturalDialog.ensureDialogClosed();
		this.homePage.ensureSnackBarDisplayed();
		assertEquals(TestConstants.ITEM_REMOVED_SUCCESS, this.homePage.snackBarText());
		assertNotEquals(this.culturalDetails.nameText(), name);
		this.homePage.closeSnackBar();
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}
	
}