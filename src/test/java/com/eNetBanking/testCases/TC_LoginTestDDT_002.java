package com.eNetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.ScreenCapture;
import com.eNetBanking.pageObjects.LoginPage;
import com.eNetBanking.utilities.XLUtils;

import junit.framework.Assert;

public class TC_LoginTestDDT_002 extends BaseClass 
{

	@Test(dataProvider = "LoginData")
	public void loginDDT(String uname,String pwd) throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(uname);
		logger.info("Username provided");
		lp.setPassword(pwd);
		logger.info("Password provided");
		lp.btnLogin();
		logger.info("Clicked on submit");
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close the alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login Failed");
			captureScreenshot(driver, "LoginTestDDT");
			
		}
		else
		{
		Assert.assertTrue(true);
		logger.info("Login Passed");
		Thread.sleep(1000);
		scrollDown();
		lp.clickLogout();
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		}
		
	}
	
	public boolean isAlertPresent()  //user defined method created to check alert is present or not
	{
		driver.switchTo().alert();
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	
	
	
}
