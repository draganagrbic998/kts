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
	
	public HomePage(WebDriver browser) {
		super();
		this.browser = browser;
	}

	public void ensureMapIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.SYNCHRON_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(this.map));
	}

}
