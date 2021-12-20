package com.tbmJavaAutomationFramework.TestCases;

import java.io.IOException;

import org.testng.TestNGUtils;
import org.testng.annotations.Test;

import com.tbmJavaAutomationFramework.PageObjects.LoginPage;

import junit.framework.Assert;

public class TC_LoginTest_01 extends BaseClass
{

	@Test(enabled=true)
	public void loginTest() throws IOException
	{
		driver.get(baseURL);
		logger.info("URL is opened");
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(username);
		logger.info("Entered user name");
		lp.setPassword(password);
		logger.info("Entered the password");
		lp.clcButton();
		logger.info("Singin button is clicked");
		
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("The test case has been passed");
		}
		else
		{
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("The tile is not same as expected.please see the screenshot attached");
		}			
		
	}
}
