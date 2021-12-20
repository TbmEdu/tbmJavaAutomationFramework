package com.tbmJavaAutomationFramework.PageObjects;

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

	@FindBy(linkText="Edit Customer")
	@CacheLookup
	WebElement lnkUpdateCust;
	
	@FindBy(how=How.XPATH,using ="//input[@name='cusid']")
	@CacheLookup
	WebElement txtCustId;
	
	@FindBy(how=How.NAME,using="AccSubmit")
	@CacheLookup
	WebElement btnSubmit;
	
	
	@FindBy(how=How.XPATH,using ="//input[@name='sub']")
	@CacheLookup
	WebElement btnCustSubmit;
	
	public void editCustomer()
	{
		lnkUpdateCust.click();
	}
	
	public void enterCustId(String custId)
	{
		txtCustId.sendKeys(custId);
	}
	
	public void getCustDetails()
	{
		btnSubmit.click();
	}
	
	public void submitUpdatedCustomer()
	{
		btnSubmit.click();
	}

}
