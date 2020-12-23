package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	private WebDriver browser;
	
	@FindBy(xpath = "/html/body/app-root/app-home/mat-drawer-container/mat-drawer-content/div/app-map/ya-map/div/ymaps/ymaps/ymaps/ymaps[1]")
	private WebElement map;
	
	@FindBy(xpath = "/html/body/app-root/app-home/mat-drawer-container/mat-drawer-content/div/app-toolbar/mat-toolbar/span[2]/button")
	private WebElement moreButton;
	
	@FindBy(xpath = "/html/body/div[2]/div[2]/div/div/div")
	private WebElement menu;
	
	@FindBy(xpath = "/html/body/div[2]/div[2]/div/div/div/button[1]")
	private WebElement loginButton;
	
	@FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/button[2]")
	private WebElement profileButton;
	
	public HomePage(WebDriver browser) {
		super();
		this.browser = browser;
	}
	
	public void moreButtonClick() {
		this.moreButton.click();
	}
	
	public void loginButtonClick() {
		this.loginButton.click();
	}
	
	public void profileButtonClick() {
		this.profileButton.click();
	}
	
	public void ensureMapIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.map));
	}

	public void ensureMenuIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.menu));
	}

}
