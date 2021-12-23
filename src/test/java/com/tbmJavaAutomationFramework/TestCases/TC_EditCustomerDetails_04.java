package com.tbmJavaAutomationFramework.TestCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tbmJavaAutomationFramework.PageObjects.AddCustomerPage;
import com.tbmJavaAutomationFramework.PageObjects.EditCustomerPage;
import com.tbmJavaAutomationFramework.PageObjects.HomePage;
import com.tbmJavaAutomationFramework.PageObjects.LoginPage;

public class TC_EditCustomerDetails_04 extends BaseClass{

	public String newAddress = getXMLData("TestData.xml", "TestModuleName", "TC_EditCustomerDetails_04", "custNewAddress");
	public String newMobNo = getXMLData("TestData.xml", "TestModuleName", "TC_EditCustomerDetails_04", "custNewMobile");
	public String custId = getXMLData("TestData.xml", "TestModuleName", "TC_EditCustomerDetails_04", "oldCustId");

	@Test
	public void editCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(username);
		logger.info("Step 1: User name " + username + " has been entered");
		lp.setPassword(password);
		logger.info("Step 2: Password has been entered");
		Thread.sleep(3000);
		lp.clcButton();
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//AddCustomerPage acp = new AddCustomerPage(driver);
		//custId =acp.getCustId();

		EditCustomerPage ecp = new EditCustomerPage(driver);		
		ecp.editCustomer();
		logger.info("Edit customer link is clicked !");
		Thread.sleep(3000);
		ecp.enterCustId(custId);
		logger.info("Entered the customer id:" + custId);
		Thread.sleep(3000);
		ecp.getCustDetails();
		Thread.sleep(3000);
		ecp.resetDetails();
		Thread.sleep(3000);
		ecp.enterNewAddress(newAddress);
		logger.info("Changed the street address to "+newAddress);
		ecp.enterNewMobileNo(newMobNo);
		logger.info("Customer mobile number has been changed to "+newMobNo);
		Thread.sleep(3000);
		ecp.submitUpdatedCustomer();
		logger.info("Clicked the submit button");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		logger.info("Popup is taken care");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		//	hp.clickLogout();
		//	logger.info("Clicked on logout button");
		//	driver.switchTo().alert().accept();
		//	driver.switchTo().defaultContent();
		//	logger.info("Came back to Login page");	
	}

	public boolean isAlertPresent()
	{
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}
	@Test
	public void validateChangedDetails() throws InterruptedException
	{
		
		EditCustomerPage ecp = new EditCustomerPage(driver);
		ecp.editCustomer();
		Thread.sleep(3000);
		ecp.enterCustId(custId);
		logger.info("Entered the customer id:" + custId);
		Thread.sleep(3000);
		ecp.getCustDetails();
		logger.info("Control is on customer details page");


		if(newAddress.matches(ecp.getNewAddress()))
		{
			logger.info("Street address has been changed successfully !");
			logger.info("Current address is: "+ecp.getNewAddress());
		}
		else
		{
			logger.info("Neither of Address or Mobile number is changed");
			logger.info("Current address is: "+ecp.getNewAddress());
			logger.info("Current Phone number is: "+ecp.getNewNumber());
		}
		
		if (newMobNo.matches(ecp.getNewNumber()))
		{
			logger.info("Customer mobile number has been changed successfully !");
			logger.info("Current Phone number is: "+ecp.getNewNumber());
		}
		else
		{
			logger.info("Neither of Address or Mobile number is changed");
			logger.info("Current address is: "+ecp.getNewAddress());
			logger.info("Current Phone number is: "+ecp.getNewNumber());
		}
	}

}
