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
	
	@FindBy(xpath = "//*/app-cultural-dialog/mat-drawer-container/mat-drawer/div/button")
	private WebElement addCommentButton;

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
	
	public void addCommentButtonClick() {
		this.addCommentButton.click();
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
	
	public void ensureAddCommentButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.addCommentButton));		
	}
	
}
