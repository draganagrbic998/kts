package com.example.demo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
	
	private WebDriver browser;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[1]/div/mat-form-field[1]/div/div[1]/div/input")
	private WebElement emailInput;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[1]/div/mat-form-field[2]/div/div[1]/div/input")
	private WebElement firstNameInput;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[1]/div/mat-form-field[3]/div/div[1]/div/input")
	private WebElement lastNameInput;
		
	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[1]/div/mat-form-field[4]/div/div[1]/div/input")
	private WebElement oldPasswordInput;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[1]/div/mat-form-field[5]/div/div[1]/div/input")
	private WebElement newPasswordInput;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[1]/div/mat-form-field[6]/div/div[1]/div/input")
	private WebElement newPasswordConfirmationInput;
		
	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[1]/div/mat-form-field[1]/div/div[3]/div/mat-error")
	private WebElement emailError;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[1]/div/mat-form-field[2]/div/div[3]/div/mat-error")
	private WebElement firstNameError;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[1]/div/mat-form-field[3]/div/div[3]/div/mat-error")
	private WebElement lastNameError;

	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[1]/div/mat-error")
	private WebElement passwordError;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-profile-form/app-form-container/div/mat-card/mat-card-content/form/div[2]/app-spacer-container/div/span[2]/button")
	private WebElement saveButton;

	@FindBy(xpath = "/html/body/div[3]/div/div/snack-bar-container/simple-snack-bar/span")
	private WebElement saveReponse;

	public ProfilePage(WebDriver browser) {
		super();
		this.browser = browser;
	}
	
	public void saveButtonClick() {
		this.saveButton.click();
	}
		
	public void emailInputFill(String value) {
		this.emailInput.clear();
		if (value.equals("")) {
			this.emailInput.sendKeys("a");
			this.emailInput.sendKeys(Keys.BACK_SPACE);
		}
		else {
			this.emailInput.sendKeys(value);
		}
	}
	
	public void firstNameInputFill(String value) {
		this.firstNameInput.clear();
		if (value.equals("")) {
			this.firstNameInput.sendKeys("a");
			this.firstNameInput.sendKeys(Keys.BACK_SPACE);
		}
		else {
			this.firstNameInput.sendKeys(value);
		}
	}
	
	public void lastNameInputFill(String value) {
		this.lastNameInput.clear();
		if (value.equals("")) {
			this.lastNameInput.sendKeys("a");
			this.lastNameInput.sendKeys(Keys.BACK_SPACE);
		}
		else {
			this.lastNameInput.sendKeys(value);
		}
	}
	
	public void oldPasswordInputFill(String value) {
		this.oldPasswordInput.clear();
		if (value.equals("")) {
			this.oldPasswordInput.sendKeys("a");
			this.oldPasswordInput.sendKeys(Keys.BACK_SPACE);
		}
		else {
			this.oldPasswordInput.sendKeys(value);
		}
	}
	
	public void newPasswordInputFill(String value) {
		this.newPasswordInput.clear();
		if (value.equals("")) {
			this.newPasswordInput.sendKeys("a");
			this.newPasswordInput.sendKeys(Keys.BACK_SPACE);
		}
		else {
			this.newPasswordInput.sendKeys(value);
		}
	}
	
	public void newPasswordConfirmationInputFill(String value) {
		this.newPasswordConfirmationInput.clear();
		if (value.equals("")) {
			this.newPasswordConfirmationInput.sendKeys("a");
			this.newPasswordConfirmationInput.sendKeys(Keys.BACK_SPACE);
		}
		else {
			this.newPasswordConfirmationInput.sendKeys(value);
		}
	}
	
	public boolean emailErrorDisplayed() {
		return this.emailError.isDisplayed() && this.emailError.getText().equals("Valid email is required!");
	}
	
	public boolean emailNotUniqueDisplayed() {
		return this.emailError.isDisplayed() && this.emailError.getText().equals("Email already exists!");
	}
	
	public boolean firstNameErrorDisplayed() {
		return this.firstNameError.isDisplayed() && this.firstNameError.getText().equals("First name is required!");
	}
	
	public boolean lastNameErrorDisplayed() {
		return this.lastNameError.isDisplayed() && this.lastNameError.getText().equals("Last name is required!");
	}
	
	public boolean oldPasswordError() {
		return this.passwordError.isDisplayed() && this.passwordError.getText().equals("You must enter old password!");
	}
	
	public boolean confirmationPasswordError() {
		return this.passwordError.isDisplayed() && this.passwordError.getText().equals("Passwords do not match!");
	}
	
	public boolean saveSuccess() {
		try {
			return this.saveReponse.isDisplayed() && this.saveReponse.getText().equals("Your profile has been updated!");
		}
		catch(org.openqa.selenium.StaleElementReferenceException e) {
			return this.saveReponse.isDisplayed() && this.saveReponse.getText().equals("Your profile has been updated!");
		}
	}
	
	public boolean saveFail() {
		try {
			return this.saveReponse.isDisplayed() && this.saveReponse.getText().equals("An error occured! Try again.");
		}
		catch(org.openqa.selenium.StaleElementReferenceException e) {
			return this.saveReponse.isDisplayed() && this.saveReponse.getText().equals("An error occured! Try again.");
		}
	}
	
	public void ensureEmailErrorIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.emailError));
	}
	
	public void ensureFirstNameErrorIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.firstNameError));
	}
	
	public void ensureLastNameErrorIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.lastNameError));
	}
	
	public void ensurePasswordErrorIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.passwordError));
	}
	
	public void ensureSaveButtonIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.saveButton));
	}
		
	public void ensureSaveResponseIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.TIMEOUT_WAIT)).until(ExpectedConditions.elementToBeClickable(this.saveReponse));
	}
	
}
