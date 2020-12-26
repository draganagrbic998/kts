package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CulturalDialog {

	private WebDriver browser;
		
	@FindBy(xpath = "//*/app-cultural-dialog/mat-drawer-container/mat-drawer-content/div/app-spacer-container/div/span[1]/button[1]")
	private WebElement editButton;
	
	@FindBy(xpath = "//*/app-cultural-dialog/mat-drawer-container/mat-drawer-content/div/app-spacer-container/div/span[1]/button[2]")
	private WebElement deleteButton;
	
	@FindBy(xpath = "//*/app-cultural-dialog/mat-drawer-container/mat-drawer-content/div/app-spacer-container/div/span[2]/button")
	private WebElement toggleDrawer;
	
	@FindBy(xpath = "//*/app-cultural-dialog/mat-drawer-container/mat-drawer/div/mat-tab-group/mat-tab-header/div[2]/div/div/div[2]")
	private WebElement switchToNews;
	
	@FindBy(xpath = "//*/app-cultural-dialog/mat-drawer-container/mat-drawer/div/button")
	private WebElement addCommentButton;

	@FindBy(xpath = "//*/app-cultural-dialog/mat-drawer-container/mat-drawer/div/button")
	private WebElement addNewsButton;
	
	@FindBy(xpath = "//*/app-cultural-dialog/mat-drawer-container/mat-drawer-content/div/app-spacer-container/div/span[1]/button")
	private WebElement subUnsubButton;

	@FindBy(xpath = "//*/app-cultural-dialog/mat-drawer-container/mat-drawer-content/div/app-spacer-container/div/span[1]/button/span[1]")
	private WebElement subUnsubButtonText;
	
	public CulturalDialog(WebDriver browser) {
		super();
		this.browser = browser;
	}
	
	public void editButtonClick() {
		this.editButton.click();
	}
	
	public void deleteButtonClick() {
		this.deleteButton.click();
	}
	
	public void toggleDrawerClick() {
		this.toggleDrawer.click();
	}
	
	public void switchToNewsList() {
		this.switchToNews.click();
	}
	
	public void addCommentButtonClick() {
		this.addCommentButton.click();
	}
	
	public void addNewsButtonClick() {
		this.addNewsButton.click();
	}
	
	public void subUnsubButtonClick() {
		this.subUnsubButton.click();
	}
	
	public void ensureEditButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.editButton));		
	}
	
	public void ensureDeleteButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.deleteButton));		
	}
	
	public void ensureToggleDrawerDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.toggleDrawer));		
	}
	
	public void ensureSwitchToNewsDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.switchToNews));		
	}
	
	public void ensureAddCommentButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.addCommentButton));		
	}
	
	public void ensureAddNewsButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.addNewsButton));		
	}

	public void ensureSubUnsubButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.subUnsubButton));		
	}

	public String getSubUnsubButtonText() {
		return this.subUnsubButtonText.getText();	
	}
}
