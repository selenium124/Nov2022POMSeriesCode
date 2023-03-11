package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	//String browserName = prop.getProperty("browser");
	
	/**
	 * this method is initializing the browser o the basis of given browser name
	 * @param brrowserName
	 * @return this returns the driver
	 */
	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		
		System.out.println("browser name is " + browserName);
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(optionsManager.getChromeOptions());
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
		}
		
		else {
			System.out.println("please pass the right browser: " + browserName);
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	/**
	 * this method reading the properties from the config.properfies file
	 * @return
	 */
	
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/Config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return prop;
	}

}
