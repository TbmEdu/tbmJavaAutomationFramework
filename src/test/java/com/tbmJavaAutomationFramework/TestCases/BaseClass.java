package com.tbmJavaAutomationFramework.TestCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tbmJavaAutomationFramework.Utilities.ReadConfig;


public class BaseClass {

	ReadConfig readconfig= new ReadConfig();

	public String baseURL=readconfig.getAppUrl();
	public String username=readconfig.getUserName();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	public static Logger testStep;
	

	@Parameters ("browser")
	@BeforeClass
	public void setUp(String br) 
	{
		logger=Logger.getLogger("tbmJaveAutomationFramework");
		PropertyConfigurator.configure("log4j.properties");

		if (br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getMozilaPath());
			driver=new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver=new InternetExplorerDriver();
		}

		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

	}

	public void LogOut()
	{

	}

	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

	
	/*	================================================================================================================
	Method Name:gcaptureScreen(WebDriver driver,String tname)
	Description: Captures the screens
	Date       : 05/04/2020	
	================================================================================================================== */
	public void captureScreen(WebDriver driver,String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
        TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir") + "/Screenshots/" + tname +"-"+timeStamp+ ".png");

		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomString()
	{
		//String randString = RandomStringUtils.randomAlphabetic(8);
		String randString = RandomStringUtils.randomAlphanumeric(5);
		return randString;
	}
/*	================================================================================================================
	Method Name:getXMLData(String fileName,String moduleName,String tcName,String tagName)
	Description: Returns data from the xml input data file. Data 'file name','Test Module Name','Test Case Name'
	             and 'Tag Name'.
	Date       : 05/04/2020	
	================================================================================================================== */
	public static String getXMLData(String fileName,String moduleName,String tcName,String tagName) {
		
		String testInput = null;
		String filePath = System.getProperty("user.dir")+ "/src/test/java/com/tbmJavaAutomationFramework/TestData/TestData.xml";
		File file = new File(filePath);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
			
		try {
			dBuilder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		
		Document doc = null;
		
		try {
			doc=dBuilder.parse(file);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Root Element: "+doc.getDocumentElement().getNodeName());
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = String.format(moduleName+"/"+tcName+"/"+tagName);
		//System.out.println(expression);
		
		Node node = null;
		
		try {
			node = (Node)xpath.compile(expression).evaluate(doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		testInput=node!=null ? (node.getTextContent()):"cannot read the test case xml file";
		return testInput;
	}
	
	

}
