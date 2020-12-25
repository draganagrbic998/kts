package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class AddCommentTest {

	private WebDriver browser;
	
	private HomePage homePage;
	private LoginPage loginPage;
	private CulturalDetails culturalDetails;
	private CulturalDialog culturalDialog;
	private CommentForm commentForm;
	private CommentDetails commentDetails;
	private ImagesInput imagesInput;
	
	@Before
	public void setUp() {
	  	System.setProperty("webdriver.chrome.driver", TestConstants.CHROME_DRIVER_PATH);
		this.browser = new ChromeDriver();
		this.browser.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.browser, HomePage.class);
		this.loginPage = PageFactory.initElements(this.browser, LoginPage.class);
		this.culturalDetails = PageFactory.initElements(this.browser, CulturalDetails.class);
		this.culturalDialog = PageFactory.initElements(this.browser, CulturalDialog.class);
		this.commentForm = PageFactory.initElements(this.browser, CommentForm.class);
		this.commentDetails = PageFactory.initElements(this.browser, CommentDetails.class);
		this.imagesInput = PageFactory.initElements(this.browser, ImagesInput.class);
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
		this.culturalDialog.ensureAddCommentButtonDisplayed();
		this.culturalDialog.addCommentButtonClick();
		this.commentForm.ensureFormDisplayed();
		
	}
	
	@Test
	public void testCancel() {
		this.commentForm.cancelButtonClick();
		this.commentForm.ensureDialogClosed();
		//mozes dodati proveru da je culturaldialog ostao otvoren
		assertEquals(TestConstants.HOME_PATH, this.browser.getCurrentUrl());
	}
	
	@Test
	public void testEmptyText() {
		this.commentForm.textInputFill("");
		this.commentForm.saveButtonClick();
		this.commentForm.ensureTextErrorDisplayed();
		assertTrue(this.commentForm.emptyTextError());
	}
	
	@Test
	public void testOnlyText() {
		String text = "dummy";
		this.commentForm.textInputFill(text);
		this.commentForm.saveButtonClick();
		this.commentForm.ensureDialogClosed();
		this.commentDetails.ensureNoStarsDisplayed();
		this.commentDetails.ensureButtonsDisplayed();
		this.commentDetails.ensureTextDisplayed();
		this.commentDetails.ensureHasNoImages();
		assertEquals(text, this.commentDetails.getText());
	}
	
	@Test
	public void testOneStar() {
		String text = "gummy1";
		this.commentForm.firstStarClick();
		this.commentForm.textInputFill(text);
		this.commentForm.saveButtonClick();
		this.commentForm.ensureDialogClosed();
		this.commentDetails.ensureOneStarDisplayed();
		this.commentDetails.ensureButtonsDisplayed();
		this.commentDetails.ensureTextDisplayed();
		this.commentDetails.ensureHasNoImages();
		assertEquals(text, this.commentDetails.getText());
	}
	
	@Test
	public void testTwoStars() {
		String text = "gummy2";
		this.commentForm.secondStarClick();
		this.commentForm.textInputFill(text);
		this.commentForm.saveButtonClick();
		this.commentForm.ensureDialogClosed();
		this.commentDetails.ensureTwoStarsDisplayed();
		this.commentDetails.ensureButtonsDisplayed();
		this.commentDetails.ensureTextDisplayed();
		this.commentDetails.ensureHasNoImages();
		assertEquals(text, this.commentDetails.getText());
	}
	
	@Test
	public void testThreeStars() {
		String text = "gummy3";
		this.commentForm.thirdStarClick();
		this.commentForm.textInputFill(text);
		this.commentForm.saveButtonClick();
		this.commentForm.ensureDialogClosed();
		this.commentDetails.ensureThreeStarsDisplayed();
		this.commentDetails.ensureButtonsDisplayed();
		this.commentDetails.ensureTextDisplayed();
		this.commentDetails.ensureHasNoImages();
		assertEquals(text, this.commentDetails.getText());
	}
	
	@Test
	public void testFourStars() {
		String text = "gummy4";
		this.commentForm.fourthStarClick();
		this.commentForm.textInputFill(text);
		this.commentForm.saveButtonClick();
		this.commentForm.ensureDialogClosed();
		this.commentDetails.ensureFourStarsDisplayed();
		this.commentDetails.ensureButtonsDisplayed();
		this.commentDetails.ensureTextDisplayed();
		this.commentDetails.ensureHasNoImages();
		assertEquals(text, this.commentDetails.getText());
	}
	
	@Test
	public void testFiveStars() {
		String text = "gummy5";
		this.commentForm.fifthStarClick();
		this.commentForm.textInputFill(text);
		this.commentForm.saveButtonClick();
		this.commentForm.ensureDialogClosed();
		this.commentDetails.ensureFiveStarsDisplayed();
		this.commentDetails.ensureButtonsDisplayed();
		this.commentDetails.ensureTextDisplayed();
		this.commentDetails.ensureHasNoImages();
		assertEquals(text, this.commentDetails.getText());
	}
	
	@Test
	public void testOneImage() {
		String text = "jummy1";
		this.commentForm.textInputFill(text);
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureOneImageDisplayed();
		assertEquals(1, this.imagesInput.imagesCount());
		
		this.commentForm.saveButtonClick();
		this.commentForm.ensureDialogClosed();
		this.commentDetails.ensureNoStarsDisplayed();
		this.commentDetails.ensureButtonsDisplayed();
		this.commentDetails.ensureTextDisplayed();
		this.commentDetails.ensureHasImages();
		this.commentDetails.carouselToggleClick();
		this.commentDetails.ensureImageDisplayed();
		
		assertEquals(text, this.commentDetails.getText());
	}
	
	@Test
	public void testMoreImages() {
		String text = "jummy2";
		this.commentForm.textInputFill(text);
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureOneImageDisplayed();
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureTwoImagesDisplayed();
		assertEquals(2, this.imagesInput.imagesCount());
		
		this.commentForm.saveButtonClick();
		this.commentForm.ensureDialogClosed();
		this.commentDetails.ensureNoStarsDisplayed();
		this.commentDetails.ensureButtonsDisplayed();
		this.commentDetails.ensureTextDisplayed();
		this.commentDetails.ensureHasImages();
		this.commentDetails.carouselToggleClick();
		this.commentDetails.ensureImageDisplayed();
		
		assertEquals(text, this.commentDetails.getText());
	}
	
	@Test
	public void testDeleteImage() {
		String text = "jummy3";
		this.commentForm.textInputFill(text);
		
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureOneImageDisplayed();
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureTwoImagesDisplayed();
		assertEquals(2, this.imagesInput.imagesCount());
		
		this.imagesInput.deleteFirstImage();
		this.imagesInput.ensureOneImageDisplayed();
		assertEquals(1, this.imagesInput.imagesCount());
		this.imagesInput.uploadFile(TestConstants.TEST_IMAGE);
		this.imagesInput.ensureTwoImagesDisplayed();
		assertEquals(2, this.imagesInput.imagesCount());
		this.imagesInput.deleteFirstImage();
		this.imagesInput.ensureOneImageDisplayed();
		assertEquals(1, this.imagesInput.imagesCount());
		
		
		this.commentForm.saveButtonClick();
		this.commentForm.ensureDialogClosed();
		this.commentDetails.ensureNoStarsDisplayed();
		this.commentDetails.ensureButtonsDisplayed();
		this.commentDetails.ensureTextDisplayed();
		this.commentDetails.ensureHasImages();
		this.commentDetails.carouselToggleClick();
		this.commentDetails.ensureImageDisplayed();
		
		assertEquals(text, this.commentDetails.getText());
	}
	
	@After
	public void cleanUp() {
		this.browser.quit();
	}
	
}
