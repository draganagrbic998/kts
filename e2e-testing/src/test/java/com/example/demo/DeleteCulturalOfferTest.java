package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class DeleteCulturalOfferTest {

	private WebDriver browser;
	
	private HomePage homePage;
	private LoginPage loginPage;
	private CulturalDetails culturalOfferDetails;
	private CulturalOfferDialog culturalOfferDialog;
	private DeleteConfirmation deleteConfirmation;

	@Before
	public void setUp() {
	  	System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.culturalOfferDetails = PageFactory.initElements(this.browser, CulturalDetails.class);
		this.culturalOfferDialog = PageFactory.initElements(this.browser, CulturalOfferDialog.class);
		this.deleteConfirmation = PageFactory.initElements(this.browser, DeleteConfirmation.class);
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureFormDisplayed();
		this.loginPage.emailInputFill(TestConstants.ADMIN_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapDisplayed();
		this.homePage.toggleButtonClick();
		this.culturalOfferDetails.ensureDetailsDisplayed();
		this.culturalOfferDetails.moreButtonClick();
		this.culturalOfferDialog.ensureDeleteButtonDisplayed();
		this.culturalOfferDialog.deleteButtonClick();
		this.deleteConfirmation.ensureDialogDisplayed();
	}
	
	@Test
	public void testCancel() {
		this.deleteConfirmation.cancelButtonClick();
		this.deleteConfirmation.ensureDialogClosed();
		//mozes dodati tipa da je ovaj culturalofferdialog otvoren i dalje, al nije mi bas bitno
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testConfirm() {
		String name = this.culturalOfferDetails.nameText();
		this.deleteConfirmation.confirmButtonClick();
		this.deleteConfirmation.ensureDialogClosed();
		this.homePage.ensureSnackBarDisplayed();
		//mozes dodati tipa da je ovaj culturalofferdialog otvoren i dalje, al nije mi bas bitno
		assertEquals(TestConstants.ITEM_REMOVED, this.homePage.snackBarText());
		assertNotEquals(name, this.culturalOfferDetails.nameText());
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}
	
}
