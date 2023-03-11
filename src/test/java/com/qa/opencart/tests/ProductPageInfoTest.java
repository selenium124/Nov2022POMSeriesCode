package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"Apple", "Apple Cinema 30\"",6},
			{"iMac","iMac",3},
			{"Samsung","Samsung SyncMaster 941BW",1}
		};
	}
	
	@Test(dataProvider= "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actualImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualImagesCount, imagesCount);
	}
	

}
