package com.example.demo.cats;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.demo.Constants;

public class CatTypeDetails {
	
	private WebDriver browser;
	
	@FindBy(xpath = "//*/app-cat-type-list/app-cat-type-details[1]/div/mat-card/mat-card-content/app-spacer-container/div/span[1]")
	private WebElement nameField;	
	
	@FindBy(xpath = "//*/app-cat-type-list/app-cat-type-details[1]/div/mat-card/mat-card-content/app-spacer-container/div/span[2]/button")
	private WebElement deleteButton;
	
	@FindBy(xpath = "//*/app-cat-type-list/app-cat-type-details[10]/div/mat-card/mat-card-content/app-spacer-container/div/span[2]/button")
	private WebElement deleteButtonWithoutType;
	
	public CatTypeDetails(WebDriver browser) {
		super();
		this.browser = browser;
	}
	
	public void deleteButtonClick() {
		this.deleteButton.click();
	}
	
	public void deleteButtonWithoutTypeClick() {
		this.deleteButtonWithoutType.click();
	}
	
	public void ensureDetailsDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.nameField));		
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.deleteButton));		
	}
	
	public String nameText() {
		return this.nameField.getText();
	}
	
}
