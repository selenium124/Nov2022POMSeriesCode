package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		
	}
	
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actualUrl = loginPage.getLoginPageURL();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test(priority = 3)
	public void forgotPedLinkExistTest() {
		Assert.assertTrue(loginPage.isforgotPwdLinkEsixt());
	}
	
	@Test(priority = 4)
	public void doLoginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}

}
