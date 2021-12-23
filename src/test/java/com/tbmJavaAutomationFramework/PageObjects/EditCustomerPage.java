package com.tbmJavaAutomationFramework.PageObjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class EditCustomerPage {
	WebDriver ldriver;

	public EditCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath="//a[text()='Edit Customer']")
	@CacheLookup
	WebElement lnkUpdateCust;
	
	//@FindBy(linkText="Edit Customer")
	//@CacheLookup
	//WebElement lnkUpdateCust;

	@FindBy(how=How.XPATH,using ="//input[@name='cusid']")
	@CacheLookup
	WebElement txtCustId;

	@FindBy(how=How.NAME,using="AccSubmit")
	@CacheLookup
	WebElement btnSubmit;

	@FindBy (how=How.NAME,using="res")
	@CacheLookup
	WebElement btnReset;

	@FindBy(how=How.NAME,using="addr")
	@CacheLookup
	WebElement txtNewAdrs;

	@FindBy(name="telephoneno")
	@CacheLookup
	WebElement txtNewNo;

	@FindBy(how=How.XPATH,using ="//input[@name='sub']")
	@CacheLookup
	WebElement btnCustSubmit;
	
	@FindBy(xpath="//div[@id='dismiss-button']")
	@CacheLookup
	WebElement btnClose;
	
	@FindBy(xpath="//div[text()='Ad']")
	@CacheLookup
	WebElement uiAdd;
	
	public void closeAdd()
	{
		
		if(btnClose.isDisplayed())
		{
			btnClose.click();
		}
		else
		{System.out.println("No add is displayed this time");}
	}
	
	
	public void editCustomer()
	{
		lnkUpdateCust.click();
	}

	public void enterCustId(String custId)
	{
		txtCustId.click();
		txtCustId.sendKeys(custId);
	}

	public void getCustDetails()
	{
		btnSubmit.click();
	}
	public void resetDetails()
	{
		btnReset.click();
	}

	public void enterNewAddress(String Addr) {
		txtNewAdrs.click();
		txtNewAdrs.clear();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtNewAdrs.click();
		txtNewAdrs.sendKeys(Addr);
	}

	public void enterNewMobileNo(String newMobNo) {
		txtNewNo.click();
		txtNewNo.clear();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtNewNo.click();
		txtNewNo.sendKeys(newMobNo);
	}

	public void submitUpdatedCustomer()
	{
		btnCustSubmit.click();
	}

	public String getNewAddress()
	{
		String newAddress=txtNewAdrs.getText();
		return newAddress;
	}
	
	public String getNewNumber()
	{
		String NewNo=txtNewNo.getAttribute("value");
		return NewNo;
	}
	
}
