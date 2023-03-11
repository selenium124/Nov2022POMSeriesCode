package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.tagName("h1");
	private By probuctImages = By.cssSelector("ul.thumbnails img");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderValue() {
		String productHeaderValue = eleUtil.doElementGetText(productHeader);
		System.out.println("productHeaderValue: " + productHeaderValue);
		return productHeaderValue;
	}
	
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(probuctImages, AppConstants.DEFAULT_LONG_TIME_OUT).size();
		System.out.println("product images count: " + imagesCount);
		return imagesCount;
	}

}
