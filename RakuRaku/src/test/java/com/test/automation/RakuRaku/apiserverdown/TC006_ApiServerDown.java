package com.test.automation.RakuRaku.apiserverdown;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.RakuRaku.testBase.TestBase;
import com.test.automation.RakuRaku.uiActions.LoginPage;

public class TC006_ApiServerDown extends TestBase{
	
public static final Logger log=Logger.getLogger(TC006_ApiServerDown.class.getName());
	
	LoginPage loginpage;


    @DataProvider(name="loginData")
	public String[][] getTestData() 
	{
		String[][] testRecords = getData("TestData.xlsx", "Login");
		return testRecords;
	}
	
	
	@BeforeClass	
	public void setUp() throws IOException 
	{
		 init();
	}


	@Test(dataProvider="loginData")
	public void apiServerDown(String facilityid, String password, String runMode) throws Exception
	{
		 if(runMode.equalsIgnoreCase("n")) 
		 {
			 throw new SkipException("User marked this no run");
		 }
		 log.info("=========>Start apiServerDown");
		 loginpage = new LoginPage(driver);
		 loginpage.loginToApplication(facilityid, password);
		 WebElement msg=driver.findElement(By.xpath("//*[@id=\"mdlErrorMessage\"]"));
	     String text=msg.getText();
	     if (msg.isEnabled() && text.contains("サーバーが応答していません <br>サービスプロバイダにお問い合わせください"))
	          { 
	         System.out.println("The server is not responding...Please contact your service provider");
	          }
	         else
	         {
	         System.out.println("The API server is up");
	          }	
	     log.info("=========>Finished apiServerDown");
	}

}
