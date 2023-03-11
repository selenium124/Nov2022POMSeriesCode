package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	
	private ElementUtil eleUtil;
	private WebDriver driver;
	//1. private by locators
	private By emailId = By.id("input-email");
	private By password = By.name("password");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	
	//2. page actions
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	//3. page actions/methods:
	public String getLoginPageTitle() {
		//String title = driver.getTitle();
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("The login page title is: " + title );
		return title;
	}
	
	public String getLoginPageURL() {
		//String loginPageUrl = driver.getCurrentUrl();
		String loginPageUrl = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("The login page url is: " + loginPageUrl );
		return loginPageUrl;
	}
	
	public boolean isforgotPwdLinkEsixt() {
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public AccountsPage doLogin(String un, String pwd) {
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

}
