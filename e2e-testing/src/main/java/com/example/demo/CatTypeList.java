package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatTypeList {

	private WebDriver browser;
	
	@FindBy(xpath = "//*/app-cat-type-list/app-paginator/div/app-spacer-container/div/span[3]/button")
	private WebElement nextButton;
	
	@FindBy(xpath = "//*/app-cat-type-list/app-paginator/div/app-spacer-container/div/span[1]/button")
	private WebElement previousButton;
	
	public CatTypeList(WebDriver browser) {
		super();
		this.browser = browser;
	}

	public void nextButtonClick() {
		this.nextButton.click();
	}

	public void previousButtonClick() {
		this.previousButton.click();
	}
	
	public void ensurePreviousButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.previousButton));
	}
	
	public void ensureNextButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.nextButton));
	}
	
	public void ensureFirstPage() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*/app-cat-type-list/app-paginator/div/app-spacer-container/div/span[1]/button")));
	}
	
	public void ensureLastPage() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*/app-cat-type-list/app-paginator/div/app-spacer-container/div/span[3]/button")));
	}
	
	public int catsTypesCount() {
		return this.browser.findElements(By.tagName("app-cat-type-details")).size();
	}
	
	public boolean catsTypesNamesContainParam(String param) {
		return this.browser.findElements(By.xpath("//*/app-cat-type-list/app-cat-type-details/div/mat-card/mat-card-content/app-spacer-container/div/span[1]"))
				.stream().anyMatch(category -> category.getText().toLowerCase().contains(param.toLowerCase()));
	}
	
}
