package com.inetBanking.TestCases;

import java.io.File;



import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.Utilities.ReadConfig;


public class BaseClass {
	ReadConfig readconfig=new ReadConfig();
	public String url=readconfig.getApplicationurl();
	public String Username=readconfig.getUsername();
	public String Password=readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
	System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
	driver=new ChromeDriver();
	logger=Logger.getLogger("ebanking");
	PropertyConfigurator.configure("log4j.properties");
	if(br.equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver=new ChromeDriver();
	}
	else if(br.equals("firefox"))
	{
		System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
		driver=new ChromeDriver();
	}
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);	
	driver.get(url);
	}
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	public void captureScreen(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/Screenshot/" +tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	
	
	
	
	
	
	
	
	
	

}
