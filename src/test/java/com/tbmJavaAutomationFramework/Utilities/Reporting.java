package com.tbmJavaAutomationFramework.Utilities;

//This is listener class used to generate the extent Report

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


//This is listener class used to generate the extent Report

public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;



	public void onStart(ITestContext testcontext)
	{
	
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());//time stamp
		String repName = "Test-Report-"+timeStamp+".html";
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//Report Location
		htmlReporter.loadConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Timir");

		
		htmlReporter.config().setDocumentTitle("TBM Java Automation Testing");//Title of the Report
		htmlReporter.config().setReportName("Functional Test Report");//Report Name
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public void onTestSuccess(ITestResult tr) 
	{
		logger=extent.createTest(tr.getName());//Create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the PASS information
	}
	

	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//Create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));//send the FAILED information
		String screenshotpath=System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
		File f=new File(screenshotpath);

		if(f.exists())
		{
			try 
			{
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//Create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));//send the SKIPPED information
	}
		
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
}
