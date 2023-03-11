package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By logoutLink = By.linkText("Logout");
	private By accountHeaders = By.tagName("h2");
	private By searchField = By.name("search");
	private By searchIcon = By.xpath("//div[@id = 'search']//button[@type='button']");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccountPageTitle() {
		//String accountPageTitle = driver.getTitle();
		String accountPageTitle = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACC_PAGE_TITLE_VALUE);
		return accountPageTitle;
	}
	
	public String getAccountpageUrl() {
		//String accountPageUrl = driver.getCurrentUrl();
		String accountPageUrl = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACC_PAGE_URL_FRACTION_VALUE);
		return accountPageUrl;
	}
	
	public boolean isLogoutLinkExist() {
		//return driver.findElement(logoutLink).isDisplayed();
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public boolean isSearchLinkExist() {
		//return driver.findElement(searchField).isDisplayed();
		return eleUtil.waitForElementVisible(searchField, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccountHeadersList() {
		//List<WebElement> accountHeadersList = driver.findElements(accountHeaders);
		List<WebElement> accountHeadersList = eleUtil.waitForElementsVisible(accountHeaders, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> headersList = new ArrayList<String>();
		for(WebElement e : accountHeadersList) {
			String text = e.getText();
			headersList.add(text);
		}
		return headersList;
	}
	
	public SearchPage performSearch(String searchKey) {
		if(isSearchLinkExist()) {
			eleUtil.doSendKeys(searchField, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("search field is not presend in the page");
			return null;
		}
		
	}
	
	

}
