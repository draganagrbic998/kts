package com.example.demo.home;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.Screen;

import com.example.demo.TestConstants;
import com.example.demo.common.HomePage;
import com.example.demo.culturals.CulturalDetails;

public class MapTest {
	
	private WebDriver browser;
	
	private HomePage homePage;
	private CulturalDetails culturalDetails;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.culturalDetails = PageFactory.initElements(this.browser, CulturalDetails.class);
		this.browser.navigate().to(TestConstants.HOME_PATH);
		this.homePage.ensureMapDisplayed();
		this.homePage.toggleButtonClick();
		this.culturalDetails.ensureDetailsDisplayed();
		this.culturalDetails.click();
		this.homePage.ensureBalloonDisplayed();
		this.homePage.toggleButtonClick();
		this.homePage.balloonCloseClick();
	}

	@Test
	public void testMap()  {
		Screen screen = new Screen();
		screen.click();
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}
	
}
