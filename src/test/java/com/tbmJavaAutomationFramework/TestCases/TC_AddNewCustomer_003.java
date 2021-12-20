package com.tbmJavaAutomationFramework.TestCases;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tbmJavaAutomationFramework.PageObjects.AddCustomerPage;
import com.tbmJavaAutomationFramework.PageObjects.LoginPage;
import com.tbmJavaAutomationFramework.Utilities.Reporting;

import junit.framework.Assert;

public class TC_AddNewCustomer_003 extends BaseClass {


	public String cname = getXMLData("TestData.xml", "TestModuleName", "TC_AddNewCustomer_003", "customerName");
	public String cgender = getXMLData("TestData.xml", "TestModuleName", "TC_AddNewCustomer_003", "customerGender");
	public String cAddress = getXMLData("TestData.xml", "TestModuleName", "TC_AddNewCustomer_003", "customerAddress");
	public String cCity = getXMLData("TestData.xml", "TestModuleName", "TC_AddNewCustomer_003", "customerCity");
	public String cState = getXMLData("TestData.xml", "TestModuleName", "TC_AddNewCustomer_003", "customerState");
	public String cPIN = getXMLData("TestData.xml", "TestModuleName", "TC_AddNewCustomer_003", "customerPin");
	public String cMob = getXMLData("TestData.xml", "TestModuleName", "TC_AddNewCustomer_003", "customerMobile");
	public String cPwd = getXMLData("TestData.xml", "TestModuleName", "TC_AddNewCustomer_003", "customerPassword");

	@Test(groups= {"demo"})
	public void addNewCustomer() throws InterruptedException, IOException {

		
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		logger.info("Step 1: User name " + username + " has been entered");
		lp.setPassword(password);
		logger.info("Step 2: Password has been entered");
		lp.clcButton();
		Thread.sleep(3000);


		AddCustomerPage acp = new AddCustomerPage(driver);
		acp.addCustomerLink();
		logger.info("Step 3: Add customer link is clicked");
		acp.custName(cname);
		Assert.assertTrue(true);
		acp.custGender(cgender);
		Assert.assertTrue(true);
		acp.custDob("04", "11", "1980");
		Thread.sleep(2000);
		acp.custAddress(cAddress);
		acp.custCity(cCity);
		acp.custState(cState);
		acp.custPin(cPIN);
		acp.custMob(cMob);
		String email = randomString() + "@gmail.com";
		acp.custEmail(email);
		acp.custPwd(cPwd);
		logger.info("Entered all information of the new customer");
		acp.cSubmit();
		Thread.sleep(3000);

		if (isAlertPresent()==true) 
		{
			logger.info("One or more input data is not correct");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			captureScreen(driver, "AddNewCustomer");
			Assert.assertTrue(false);

		} else {
			logger.info("New customer data has been submitted");
		}

		boolean resMsg = driver.getPageSource().contains("Customer Registered Successfully!!!");

		if (resMsg == true) {
			Assert.assertTrue(true);
			String custId =acp.getCustId();
			logger.info("The new customer data has been submitted successfully and Id is:"+custId);
		} else {
			captureScreen(driver, "AddNewCustomer");
			Assert.assertTrue(false);
			logger.info("The new customer data has been NOT been submitted due to some error");
		}


	}

	@Test
	public void writeCustomerId()
	{
		AddCustomerPage acp = new AddCustomerPage(driver);
		String custId=acp.getCustId();
		System.out.println(custId);
	}



	public boolean isAlertPresent() {
		// TODO Auto-generated method stub
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
