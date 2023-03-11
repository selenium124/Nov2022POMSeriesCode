package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String actualTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACC_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void accPageUrlTest() {
		String actualUrl = accPage.getAccountpageUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.ACC_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void searchLinkExistTest() {
		Assert.assertTrue(accPage.isSearchLinkExist());
	}
	
	@Test
	public void getAccountHeadersListTest() {
		List<String> actualaccountHeadersList = accPage.getAccountHeadersList();
		System.out.println(actualaccountHeadersList);
		int actualLinksCount = actualaccountHeadersList.size();
		Assert.assertEquals(actualLinksCount, AppConstants.ACC_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void getAccountHeadersValueTest() {
		List<String> actualaccountHeadersList = accPage.getAccountHeadersList();
		System.out.println("Actual acc page headers List: " + actualaccountHeadersList);
		System.out.println("Expected acc page headers List: " + AppConstants.EXPECTED_ACC_PAGE_HEADERS_LIST);
		Assert.assertEquals(actualaccountHeadersList, AppConstants.EXPECTED_ACC_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook"},
			{"Apple"},
			{"iMac"},
			{"Samsung"},
			{"Ramya"}
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) { 
		searchPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Apple", "Apple Cinema 30\""},
			{"iMac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"}
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName) { 
		searchPage = accPage.performSearch(searchKey);
		if(searchPage.getSearchProductCount()>0) {
		productInfoPage = searchPage.selectProduct(productName);
		String actualProductHeader = productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actualProductHeader, productName);
		}
	}
	
	
	

}
