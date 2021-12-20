package com.tbmJavaAutomationFramework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	
	WebDriver ldriver;

	public AddCustomerPage (WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		}
	
	@FindBy(linkText="New Customer")
	@CacheLookup
	WebElement lnkNewCustomer;
	
	@FindBy(how=How.NAME,using="name")
	@CacheLookup
	WebElement txtName;
	
	@FindBy(how=How.XPATH,using="//input[@value='m']")
	@CacheLookup
	WebElement gMale;
	
	@FindBy(how=How.XPATH,using="//input[@value='f']")
	@CacheLookup
	WebElement fMale;
	
	@FindBy(how=How.XPATH,using = "//*[@id=\"dob\"]")
	@CacheLookup
	WebElement txtDob;
	
	@FindBy(how=How.NAME,using = "addr")
	@CacheLookup
	WebElement txtAddress;
	
	@FindBy(name="city")
	@CacheLookup
	WebElement txtCity;
	
	@FindBy(name="state")
	@CacheLookup
	WebElement txtState;
	
	@FindBy(name="pinno")
	@CacheLookup
	WebElement txtZip;
	
	@FindBy(how=How.NAME,using = "telephoneno")
	@CacheLookup
	WebElement txtMobile;
	
	@FindBy(name="emailid")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how=How.NAME,using = "password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(how=How.NAME,using = "sub")
	@CacheLookup
	WebElement btnSubmit;
	
	@FindBy(xpath ="//p[contains(text(),'Registered Successfully')]")
	@CacheLookup
	WebElement txtMsg;
	
	@FindBy(xpath="//table/tbody/tr[4]/td[2]")
	@CacheLookup
	WebElement lblCustId;
	
	//Create the action method for each web object
	
	public void addCustomerLink()
	{
		lnkNewCustomer.click();
	}
	
	
	public void custName(String cname)
	{
		txtName.sendKeys(cname);
	}
	
	public void custGender(String cgender)
	{
		if (cgender=="male")
		{
			gMale.click();
		}
		else
		{
			fMale.click();
		}

	}
	
	public void custDob(String mm,String dd,String yyyy)
	{
		txtDob.sendKeys(mm);
		txtDob.sendKeys(dd);
		txtDob.sendKeys(yyyy);		
	}
	
	public void custAddress(String caddr)
	{
		txtAddress.sendKeys(caddr);
	}
	
	public void custCity(String ccity)
	{
		txtCity.sendKeys(ccity);
	}
	
	public void custState(String cstate)
	{
		txtState.sendKeys(cstate);
	}
	
	public void custPin(String cpin)
	{
		txtZip.sendKeys(cpin);
	}
	
	public void custMob(String cmob)
	{
		txtMobile.sendKeys(cmob);
	}
	
	public void custEmail(String cemail)
	{
		txtEmail.sendKeys(cemail);
	}
	
	public void custPwd(String cpass)
	{
		txtPassword.sendKeys(cpass);
	}
	
	public void cSubmit()
	{
		btnSubmit.click();
	}
	
	
	public String getCustId() {
		String custId=lblCustId.getText();
		return custId;
	}

	
	
	
	
	
	
	
	
	
	
	

}
