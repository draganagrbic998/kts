package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CulturalList {
	
	private WebDriver browser;
	
	@FindBy(xpath = "//*/app-cultural-list/app-paginator/div/app-spacer-container/div/span[3]/button")
	private WebElement nextButton;
	
	@FindBy(xpath = "//*/app-cultural-list/app-paginator/div/app-spacer-container/div/span[1]/button")
	private WebElement previousButton;
	
	@FindBy(xpath = "//*/app-cultural-list/div[1]/mat-expansion-panel/mat-expansion-panel-header")
	private WebElement toggleFilter;
	
	@FindBy(xpath = "//*/app-cultural-list/app-paginator/div/app-spacer-container/div/span[2]")
	private WebElement title;
	
	@FindBy(xpath = "//*/app-home/mat-drawer-container/mat-drawer/div/div/mat-tab-group/mat-tab-header/div[2]/div/div/div[2]")
	private WebElement followingsTab;
	
	public CulturalList(WebDriver browser) {
		super();
		this.browser = browser;
	}

	public void nextButtonClick() {
		this.nextButton.click();
	}

	public void previousButtonClick() {
		this.previousButton.click();
	}
	
	public void toggleFilterClick() {
		this.toggleFilter.click();
	}
	
	public void titleClick() {
		this.title.click();
	}
	
	public void followingsTabClick() {
		this.followingsTab.click();
	}
	
	public void ensurePreviousButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.previousButton));
	}
	
	public void ensureNextButtonDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.nextButton));
	}
	
	public void ensureToggleFilterDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.toggleFilter));
	}
	
	public void ensureFirstPage() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*/app-cultural-list/app-paginator/div/app-spacer-container/div/span[1]/button")));
	}
	
	public void ensureLastPage() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*/app-cultural-list/app-paginator/div/app-spacer-container/div/span[3]/button")));
	}
	
	public void ensureTitleDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.title));
	}
	
	public void ensureFollowingsTabDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.followingsTab));
	}
	
	public int offersCount() {
		return this.browser.findElements(By.tagName("app-cultural-details")).size();
	}
	
	public boolean offerNamesContainParam(String param) {
		return this.browser.findElements(By.xpath("//*/app-cultural-list/app-cultural-details/div/mat-card/mat-card-content/div/div[2]/div[1]/app-bold-text/span"))
				.stream().allMatch(offer -> offer.getText().toLowerCase().contains(param.toLowerCase()));
	}
	
	public boolean offerLocationsContainParam(String param) {
		return this.browser.findElements(By.xpath("//*/app-cultural-list/app-cultural-details[1]/div/mat-card/mat-card-content/div/div[2]/div[3]/span"))
				.stream().allMatch(offer -> offer.getText().toLowerCase().contains(param.toLowerCase()));
	}

	public boolean offerTypesContainParam(String param) {
		return this.browser.findElements(By.xpath("//*/app-cultural-list/app-cultural-details[1]/div/mat-card/mat-card-content/div/div[2]/div[2]/span"))
				.stream().allMatch(offer -> offer.getText().toLowerCase().contains(param.toLowerCase()));
	}
	
}
