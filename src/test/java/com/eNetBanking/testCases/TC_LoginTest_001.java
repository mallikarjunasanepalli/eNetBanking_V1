package com.eNetBanking.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.eNetBanking.pageObjects.LoginPage;
public class TC_LoginTest_001 extends BaseClass{
	
	@Test
	public void loginTest() throws IOException {
		
		
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(username);
		logger.info("Entered Username");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.btnLogin();
		
		if (driver.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage")) {
			org.testng.Assert.assertTrue(true);
			logger.info("Test Case Passed");
		}else
		{
			captureScreenshot(driver,"loginTest");
			org.testng.Assert.assertTrue(false);
			logger.info("Test Case Failed");
		}

    }

}
