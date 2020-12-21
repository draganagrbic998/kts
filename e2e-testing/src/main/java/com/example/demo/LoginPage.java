package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver browser;
		
	@FindBy(xpath = "/html/body/app-root/app-user/app-login-form/app-form-container/div/mat-card/mat-card-content/form/div/mat-form-field[1]/div/div[1]/div/input")
	private WebElement emailInput;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-login-form/app-form-container/div/mat-card/mat-card-content/form/div/mat-form-field[1]/div/div[3]/div/mat-error")
	private WebElement emailError;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-login-form/app-form-container/div/mat-card/mat-card-content/form/div/mat-form-field[2]/div/div[1]/div/input")
	private WebElement passwordInput;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-login-form/app-form-container/div/mat-card/mat-card-content/form/div/mat-form-field[2]/div/div[3]/div/mat-error")
	private WebElement passwordError;
	
	@FindBy(xpath = "/html/body/app-root/app-user/app-login-form/app-form-container/div/mat-card/mat-card-content/form/app-center-container/div/button")
	private WebElement loginButton;
	
	@FindBy(xpath = "/html/body/div[2]/div/div/snack-bar-container/simple-snack-bar/span")
	private WebElement loginError;
	
	public LoginPage(WebDriver browser) {
		super();
		this.browser = browser;
	}
	
	public void emailInputFill(String value) {
		this.emailInput.clear();
		this.emailInput.sendKeys(value);
	}
	
	public void passwordInputFill(String value) {
		this.passwordInput.clear();
		this.passwordInput.sendKeys(value);
	}
	
	public void loginButtonClick() {
		this.loginButton.click();
	}
		
	public boolean emailInvalidError() {
		return this.emailError.isDisplayed() && this.emailError.getText().equals("You must provide valid email!");
	}
	
	public boolean passwordEmptyError() {
		return this.passwordError.isDisplayed() && this.passwordError.getText().equals("You must provide password!");
	}
	
	public void ensureEmailErrorIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.SYNCHRON_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(this.emailError));
	}
	
	public void ensurePasswordErrorIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.SYNCHRON_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(this.passwordError));
	}

	public void ensureLoginErrorIsDisplayed() {
		(new WebDriverWait(this.browser, Constants.SYNCHRON_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(this.loginError));
	}

}
