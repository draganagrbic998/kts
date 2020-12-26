package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class SubscribeUnsubscribeTest {

	private WebDriver browser;
	
	private HomePage homePage;
	private LoginPage loginPage;
	private CulturalDetails culturalOfferDetails;
	private CulturalDialog culturalOfferDialog;

	@Before
	public void setUp() {
	  	System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.culturalOfferDetails = PageFactory.initElements(this.browser, CulturalDetails.class);
		this.culturalOfferDialog = PageFactory.initElements(this.browser, CulturalDialog.class);
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureFormDisplayed();
		this.loginPage.emailInputFill(TestConstants.GUEST_EMAIL);
		this.loginPage.passwordInputFill(TestConstants.LOGIN_PASSWORD);
		this.loginPage.loginButtonClick();
		this.homePage.ensureMapDisplayed();
		this.homePage.toggleButtonClick();
		this.culturalOfferDetails.ensureDetailsDisplayed();
		this.culturalOfferDetails.moreButtonClick();
		this.culturalOfferDialog.ensureSubUnsubButtonDisplayed();
	}
	
	@Test
	public void testSubscribeUnsubscribe() {
		this.culturalOfferDialog.subUnsubButtonClick();
		this.culturalOfferDialog.ensureSubUnsubButtonDisplayed();
		assertEquals(TestConstants.UNSUB_BUTTON_TEXT, this.culturalOfferDialog.getSubUnsubButtonText());

		this.culturalOfferDialog.ensureSubUnsubButtonDisplayed();

		this.culturalOfferDialog.subUnsubButtonClick();
		this.culturalOfferDialog.ensureSubUnsubButtonDisplayed();
		assertEquals(TestConstants.SUB_BUTTON_TEXT, this.culturalOfferDialog.getSubUnsubButtonText());
		//mozda bi mogo da dodam i da se proverava da li je u followed listi ali ajmo za sad ovako
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}
	
}
