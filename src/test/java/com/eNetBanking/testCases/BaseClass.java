package com.eNetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.eNetBanking.utilities.ReadConfigu;
import com.eNetBanking.utilities.XLUtils;

public class BaseClass {
	
	ReadConfigu readconfigu=new ReadConfigu();
	
	public String baseURL=readconfigu.getApplicationUrl();
	public String username=readconfigu.getUsername();
	public String password=readconfigu.getPassword();

	public static WebDriver driver;
	
	public static Logger logger;
	@Parameters("browser")
	
	@BeforeClass
	public void setup(String br) throws InterruptedException {
		
		
		
	    logger=Logger.getLogger("eNetBanking_v1");
	    PropertyConfigurator.configure("log4j.properties");
	    
	    if(br.equals("Firefox")) {
	    	System.setProperty("webdriver.gecko.driver", readconfigu.getfirefoxpath());
		    driver=new FirefoxDriver();
			
		}else if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfigu.getchromepath());
		    driver=new ChromeDriver();
		    driver.manage().window().maximize();
		}else {
			System.setProperty("webdriver.ie.driver", readconfigu.getiepath());
		    driver=new InternetExplorerDriver();
		}
	    
	    
        driver.get(baseURL);
        Thread.sleep(1000);
		
		logger.info("URL Opened");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void captureScreenshot(WebDriver driver,String tname) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+ "/Screenshots/" +tname+".png");
		FileUtils.copyFile(src, target);
		System.out.println("Screenshot taken");
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/eNetBanking/testData/LoginData.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String loginData[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				loginData[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return loginData;
	}
	
	public String generateRandomString()
	{
		String randomstring=RandomStringUtils.randomAlphabetic(6);
		return randomstring;
	}
	
	public String generateRandomNumber()
	{
		String randomNumber=RandomStringUtils.randomNumeric(5);
		return randomNumber;
	}
	
	public void scrollDown()
	{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");

	}
	
}
