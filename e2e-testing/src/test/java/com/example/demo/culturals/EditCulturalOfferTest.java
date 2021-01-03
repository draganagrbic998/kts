package com.example.demo.culturals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.example.demo.TestConstants;
import com.example.demo.common.HomePage;
import com.example.demo.common.ImageInput;
import com.example.demo.user.LoginPage;

public class EditCulturalOfferTest {
	
	private WebDriver browser;
	
	private HomePage homePage;
	private LoginPage loginPage;
	private CulturalForm culturalForm;
	private CulturalDetails culturalDetails;
	private CulturalDialog culturalDialog;
	private ImageInput imageInput;

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
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureFormDisplayed();
		this.loginPage.emailInputFill(TestConstants.ADMIN_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapDisplayed();
		this.homePage.toggleButtonClick();
		this.culturalDetails.ensureDetailsDisplayed();
		this.culturalDetails.moreButtonClick();
		this.culturalDialog.ensureEditButtonDisplayed();
		this.culturalDialog.editButtonClick();
		this.culturalForm.ensureFormDisplayed();
	}
	
	@Test
	public void testCancel() {
		this.culturalForm.cancelButtonClick();
		this.culturalForm.ensureDialogClosed();
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testEmptyForm() {
		this.culturalForm.typeInputFill("");
		this.culturalForm.nameInputFill("");
		this.culturalForm.locationInputFill("");
		this.culturalForm.saveButtonClick();
		this.culturalForm.ensureTypeErrorDisplayed();
		this.culturalForm.ensureNameErrorDisplayed();
		this.culturalForm.ensureLocationErrorDisplayed();
		assertTrue(this.culturalForm.emptyTypeError());
		assertTrue(this.culturalForm.emptyNameError());
		assertTrue(this.culturalForm.invalidLocationError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testBlankForm() {
		this.culturalForm.typeInputFill("  ");
		this.culturalForm.nameInputFill("  ");
		this.culturalForm.locationInputFill("  ");
		this.culturalForm.saveButtonClick();
		this.culturalForm.ensureTypeErrorDisplayed();
		this.culturalForm.ensureNameErrorDisplayed();
		this.culturalForm.ensureLocationErrorDisplayed();
		assertTrue(this.culturalForm.emptyTypeError());
		assertTrue(this.culturalForm.emptyNameError());
		assertTrue(this.culturalForm.invalidLocationError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testNonExistingType() {
		this.culturalForm.typeInputFill("dummy");
		this.culturalForm.nameInputFill("");
		this.culturalForm.locationInputFill("");
		this.culturalForm.saveButtonClick();
		this.culturalForm.ensureTypeErrorDisplayed();
		this.culturalForm.ensureNameErrorDisplayed();
		this.culturalForm.ensureLocationErrorDisplayed();
		assertTrue(this.culturalForm.nonExistingTypeError());
		assertTrue(this.culturalForm.emptyNameError());
		assertTrue(this.culturalForm.invalidLocationError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testTakenName() {
		this.culturalForm.typeInputFill("");
		this.culturalForm.nameInputFill(TestConstants.TAKEN_OFFER_NAME);
		this.culturalForm.locationInputFill("");
		this.culturalForm.saveButtonClick();
		this.culturalForm.ensureTypeErrorDisplayed();
		this.culturalForm.ensureNameErrorDisplayed();
		this.culturalForm.ensureLocationErrorDisplayed();
		assertTrue(this.culturalForm.emptyTypeError());
		assertTrue(this.culturalForm.takenNameError());
		assertTrue(this.culturalForm.invalidLocationError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testValid() {
		String type = "gallery";
		String name = "gummy";
		String location = "washington";
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
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());	
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}
	
}
