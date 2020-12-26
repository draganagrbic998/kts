package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatTypeDialog {
	
	private WebDriver browser;

	@FindBy(xpath = "//*/app-cat-type-dialog/mat-tab-group/mat-tab-header/div[2]/div/div/div[1]")
	private WebElement catTypeListTab;
	
	@FindBy(xpath = "//*/app-cat-type-dialog/mat-tab-group/mat-tab-header/div[2]/div/div/div[2]")
	private WebElement newCatTypeTab;
	
	public CatTypeDialog(WebDriver browser) {
		super();
		this.browser = browser;
	}
	
	public void catListTabClick() {
		this.catTypeListTab.click();
	}
	
	public void newCatTypeTabClick() {
		this.newCatTypeTab.click();
	}
	
	public void ensureCategoryTypeFormTabDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.newCatTypeTab));
	}
	
	public void ensureCategoryTypeListTabDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.catTypeListTab));
	}
	
}
