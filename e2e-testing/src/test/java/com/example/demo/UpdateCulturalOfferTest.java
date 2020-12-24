package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class UpdateCulturalOfferTest {
	
	private WebDriver browser;
	
	private HomePage homePage;
	private LoginPage loginPage;
	private CulturalOfferForm culturalOfferForm;
	private CulturalDetails culturalOfferDetails;
	private CulturalOfferDialog culturalOfferDialog;

	@Before
	public void setUp() {
	  	System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.culturalOfferForm = PageFactory.initElements(this.browser, CulturalOfferForm.class);
		this.culturalOfferDetails = PageFactory.initElements(this.browser, CulturalDetails.class);
		this.culturalOfferDialog = PageFactory.initElements(this.browser, CulturalOfferDialog.class);
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureFormDisplayed();
		this.loginPage.emailInputFill(TestConstants.ADMIN_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapDisplayed();
		this.homePage.toggleButtonClick();
		this.culturalOfferDetails.ensureDetailsDisplayed();
		this.culturalOfferDetails.moreButtonClick();
		this.culturalOfferDialog.ensureEditButtonDisplayed();
		this.culturalOfferDialog.editButtonClick();
		this.culturalOfferForm.ensureFormDisplayed();
	}
	
	@Test
	public void testCancel() {
		this.culturalOfferForm.cancelButtonClick();
		this.culturalOfferForm.ensureDialogClosed();
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testEmptyForm() {
		this.culturalOfferForm.typeInputFill("");
		this.culturalOfferForm.nameInputFill("");
		this.culturalOfferForm.locationInputFill("");
		this.culturalOfferForm.saveButtonClick();
		this.culturalOfferForm.ensureTypeErrorDisplayed();
		this.culturalOfferForm.ensureNameErrorDisplayed();
		this.culturalOfferForm.ensureLocationErrorDisplayed();
		assertTrue(this.culturalOfferForm.emptyTypeError());
		assertTrue(this.culturalOfferForm.emptyNameError());
		assertTrue(this.culturalOfferForm.invalidLocationError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testBlankForm() {
		this.culturalOfferForm.typeInputFill("  ");
		this.culturalOfferForm.nameInputFill("  ");
		this.culturalOfferForm.locationInputFill("  ");
		this.culturalOfferForm.saveButtonClick();
		this.culturalOfferForm.ensureTypeErrorDisplayed();
		this.culturalOfferForm.ensureNameErrorDisplayed();
		this.culturalOfferForm.ensureLocationErrorDisplayed();
		assertTrue(this.culturalOfferForm.emptyTypeError());
		assertTrue(this.culturalOfferForm.emptyNameError());
		assertTrue(this.culturalOfferForm.invalidLocationError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testNonExistingType() {
		this.culturalOfferForm.typeInputFill("dummy");
		this.culturalOfferForm.nameInputFill("");
		this.culturalOfferForm.locationInputFill("");
		this.culturalOfferForm.saveButtonClick();
		this.culturalOfferForm.ensureTypeErrorDisplayed();
		this.culturalOfferForm.ensureNameErrorDisplayed();
		this.culturalOfferForm.ensureLocationErrorDisplayed();
		assertTrue(this.culturalOfferForm.nonExistingTypeError());
		assertTrue(this.culturalOfferForm.emptyNameError());
		assertTrue(this.culturalOfferForm.invalidLocationError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testTakenName() {
		this.culturalOfferForm.typeInputFill("");
		this.culturalOfferForm.nameInputFill(TestConstants.TAKEN_OFFER_NAME);
		this.culturalOfferForm.locationInputFill("");
		this.culturalOfferForm.saveButtonClick();
		this.culturalOfferForm.ensureTypeErrorDisplayed();
		this.culturalOfferForm.ensureNameErrorDisplayed();
		this.culturalOfferForm.ensureLocationErrorDisplayed();
		assertTrue(this.culturalOfferForm.emptyTypeError());
		assertTrue(this.culturalOfferForm.takenNameError());
		assertTrue(this.culturalOfferForm.invalidLocationError());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testValid() {
		String type = "gallery";
		String name = "gummy";
		String location = "washington";
		this.culturalOfferForm.typeInputFill(type);
		this.culturalOfferForm.nameInputFill(name);
		this.culturalOfferForm.locationInputFill(location);
		this.culturalOfferForm.ensureAutocompleteSuggestionDisplayed();
		this.culturalOfferForm.autocompleteSuggestionClick();
		this.culturalOfferForm.saveButtonClick();
		this.culturalOfferForm.ensureDialogClosed();
		this.homePage.ensureSnackBarDisplayed();
		assertEquals("Offer successfully saved!", this.homePage.snackBarText());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());

		this.culturalOfferDetails.ensureDetailsDisplayed();
		assertEquals(this.culturalOfferDetails.nameText(), name);
		assertEquals(this.culturalOfferDetails.typeText(), type);
		assertTrue(this.culturalOfferDetails.locationText().toLowerCase().contains(location.toLowerCase()));
		//treba da obrises iz baze to sto si dodala
		//proveri oce ovaj balon uvek raditi
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}
	
}
