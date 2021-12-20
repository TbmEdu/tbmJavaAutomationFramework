package com.tbmJavaAutomationFramework.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;
	
	public ReadConfig()
	{
		File src = new File ("./Configurations/config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is:"+ e.getMessage());
			e.printStackTrace();
		
	}

}
	//Reading the value of URL from config.properties file
	public String getAppUrl()
	{
		String url=pro.getProperty("baseURL");
		return url;
	}
	
	//Reading the value of User Name from config.properties file
	public String getUserName()
	{
		String user=pro.getProperty("userName");
		return user;
	}
	
	//Reading the value of Password from config.properties file
	public String getPassword()
	{
		String pwd=pro.getProperty("password");
		return pwd;
	}
	
	public String getChromePath()
	{
		String chromepath=pro.getProperty("chromePath");
		return chromepath;
	}
	
	public String getMozilaPath()
	{
		String mozilaPath=pro.getProperty("gecoPath");
		return mozilaPath;
	}
	
	public String getIEPath()
	{
		String IEPath=pro.getProperty("iePath");
		return IEPath;
	}
	
	
}
