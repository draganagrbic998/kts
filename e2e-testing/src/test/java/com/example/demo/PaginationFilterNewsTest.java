package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class PaginationFilterNewsTest {

	private WebDriver browser;

	private HomePage homePage;
	private LoginPage loginPage;
	private CulturalDetails culturalDetails;
	private CulturalDialog culturalDialog;
	private NewsDetails newsDetails;
	private NewsList newsList;
	private FilterFormDate filterForm;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.culturalDetails = PageFactory.initElements(this.browser, CulturalDetails.class);
		this.culturalDialog = PageFactory.initElements(this.browser, CulturalDialog.class);
		this.newsDetails = PageFactory.initElements(this.browser, NewsDetails.class);
		this.newsList = PageFactory.initElements(this.browser, NewsList.class);
		this.filterForm = PageFactory.initElements(this.browser, FilterFormDate.class);
		this.browser.navigate().to(TestConstants.LOGIN_PATH);
		this.loginPage.ensureFormDisplayed();
		this.loginPage.emailInputFill(TestConstants.GUEST_EMAIL);
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
	}

	@Test
	public void testPagination() {
		this.newsList.ensureFirstPage();

		this.newsDetails.ensureTextDisplayed();
		assertEquals(10, this.newsList.newsCount());

		String name = this.newsDetails.getText();
		this.newsList.ensureNextButtonDisplayed();
		this.newsList.nextButtonClick();
		this.newsDetails.ensureTextDisplayed();
		this.newsList.ensureLastPage();
		assertNotEquals(name, this.newsDetails.getText());
		assertEquals(7, this.newsList.newsCount());

		name = this.newsDetails.getText();
		this.newsList.ensurePreviousButtonDisplayed();
		this.newsList.previousButtonClick();
		this.newsDetails.ensureTextDisplayed();
		assertNotEquals(name, this.newsDetails.getText());
		assertEquals(10, this.newsList.newsCount());

		this.newsList.ensureFirstPage();
	}

	@Test
	public void testFilterAll() {
		this.newsList.ensureToggleFilterDisplayed();
		this.newsList.toggleFilterClick();
		this.filterForm.ensureFormDisplayed();
		this.filterForm.startFilterFill("");
		this.filterForm.endFilterFill("");
		this.filterForm.filterButtonClick();
		this.filterForm.ensureFormDisplayed();
		this.newsList.ensureNextButtonDisplayed();
		assertEquals(10, this.newsList.newsCount());
	}
	
	@Test
	public void testFilterMore() {
		this.newsList.ensureToggleFilterDisplayed();
		this.newsList.toggleFilterClick();
		this.filterForm.ensureFormDisplayed();
		this.filterForm.startFilterFill(TestConstants.FILTER_START_DATE);
		this.filterForm.endFilterFill("");
		this.filterForm.filterButtonClick();
		this.filterForm.ensureFormDisplayed();
		assertTrue(this.newsList.newsCount() == 2);
		assertTrue(this.newsList.newsTextContainParam(TestConstants.NEWS_TEXT_MORE));
	}

	@Test
	public void testFilterOne() {
		this.newsList.ensureToggleFilterDisplayed();
		this.newsList.toggleFilterClick();
		this.filterForm.ensureFormDisplayed();
		this.filterForm.startFilterFill(TestConstants.FILTER_START_DATE);
		this.filterForm.endFilterFill(TestConstants.FILTER_END_DATE);
		this.filterForm.filterButtonClick();
		this.filterForm.ensureFormDisplayed();
		assertTrue(this.newsList.newsCount() == 1);
		assertTrue(this.newsList.newsTextContainParam(TestConstants.NEWS_TEXT_ONE));
	}

	@Test
	public void testFilterNone() {
		this.newsList.ensureToggleFilterDisplayed();
		this.newsList.toggleFilterClick();
		this.filterForm.ensureFormDisplayed();
		this.filterForm.startFilterFill(TestConstants.FILTER_NONE_DATE);
		this.filterForm.endFilterFill(TestConstants.FILTER_NONE_DATE);
		this.filterForm.filterButtonClick();
		this.filterForm.ensureFormDisplayed();
		assertEquals(0, this.newsList.newsCount());
	}

	@After
	public void cleanUp() {
		this.browser.quit();
	}

}
