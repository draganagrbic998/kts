package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	private WebDriver browser;
	
	@FindBy(xpath = "//*/app-map/ya-map/div/ymaps/ymaps/ymaps/ymaps[1]")
	private WebElement map;
	
	@FindBy(xpath = "//*/app-toolbar/mat-toolbar/span[1]/button[1]")
	private WebElement toggleButton;

	@FindBy(xpath = "//*/app-toolbar/mat-toolbar/span[1]/button[2]")
	private WebElement addOfferButton;
	
	@FindBy(xpath = "//*/app-toolbar/mat-toolbar/span[2]/button")
	private WebElement moreButton;
	
	@FindBy(xpath = "//*[@id=\"mat-menu-panel-1\"]/div/button[2]")
	private WebElement catsTypesButton;
	
	@FindBy(xpath = "//*[@id=\"mat-menu-panel-2\"]/div/button[1]")
	private WebElement categoriesButton;
	
	@FindBy(xpath = "//*[@id=\"mat-menu-panel-2\"]/div/button[2]")
	private WebElement typesButton;
		
	@FindBy(tagName = Constants.SNACKBAR)
	private WebElement snackBar;

	@FindBy(xpath = "//*/app-map/ya-map/div/ymaps/ymaps/ymaps/ymaps[6]/ymaps/ymaps/ymaps/ymaps[1]/ymaps[2]/ymaps/ymaps/div")
	private WebElement balloon;	//proveri ocel ovaj balloon uvek imati ovaj xpath
				
	public HomePage(WebDriver browser) {
		super();
		this.browser = browser;
	}
	
	public void toggleButtonClick() {
		this.toggleButton.click();
	}
		
	public void addOfferButtonClick() {
		this.addOfferButton.click();
	}
	
	public void moreButtonClick() {
		this.moreButton.click();
	}
	
	public void catsTypesButtonClick() {
		this.catsTypesButton.click();
	}
	
	public void categoriesButtonClick() {
		this.categoriesButton.click();
	}
	
	public void typesButtonClick() {
		this.typesButton.click();
	}
	
	public void ensureMapDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.map));
	}
	
	public void ensureSnackBarDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.snackBar));
	}
	
	public void ensureBalloonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.balloon));
	}
	
	public String snackBarText() {
		return this.snackBar.findElement(By.tagName("span")).getText();
	}
	
	public String balloonText() {
		return this.balloon.getText();
	}

	public void ensureCatsTypesButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.catsTypesButton));
	}

	public void ensureCatsButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.categoriesButton));
	}
	
	public void ensureTypesButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.typesButton));
	}

}
