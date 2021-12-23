package com.tbmJavaAutomationFramework.TestCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tbmJavaAutomationFramework.PageObjects.HomePage;
import com.tbmJavaAutomationFramework.PageObjects.LoginPage;
import com.tbmJavaAutomationFramework.Utilities.XLUtils;

public class TC_LoginDDT_02 extends BaseClass
{
	@Test(dataProvider="LoginData",enabled=true)
	public void loginDDT(String user,String pwd) throws IOException
	{
		LoginPage lp=new LoginPage(driver);
		HomePage hp=new HomePage(driver);


		lp.setUsername(user);
		logger.info("User name has been entered");
		lp.setPassword(pwd);
		logger.info("Password has been entered");
		lp.clcButton();	
		logger.info("Submit button is clicked!");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		if(isAlertPresent()==true)
		{
			logger.info("Either username or password is incorrect");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(true);	
		}
		else
		{
			logger.info("successfully logged in");
			Assert.assertTrue(true);
			hp.clickLogout();
			logger.info("Clicked on logout button");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.info("Came back to Login page");		
		}

	}

	//User defined method to check if an alert is present
	public boolean isAlertPresent()
	{
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir") + "/src/test/java/com/tbmJavaAutomationFramework/TestData/LoginData.xlsx";

		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colnum=XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][]=new String[rownum][colnum];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colnum;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;

	}
}
