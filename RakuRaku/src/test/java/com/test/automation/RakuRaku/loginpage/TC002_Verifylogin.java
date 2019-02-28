package com.test.automation.RakuRaku.loginpage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.RakuRaku.testBase.TestBase;
import com.test.automation.RakuRaku.uiActions.LoginPage;

public class TC002_Verifylogin extends TestBase{

	public static final Logger log=Logger.getLogger(TC002_Verifylogin.class.getName());
	
	LoginPage loginpage;

	
	
	@DataProvider(name="loginData")
	public String[][] getTestData() 
	{
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}

	
	@BeforeClass	
	public void setUp() throws IOException 
	{
		 init();
		
	}


	@Test(dataProvider="loginData")
	public void verifyLogin(String facilityid, String password, String runMode ) throws Exception
	{ 
		 if(runMode.equalsIgnoreCase("n")) 
		 {
			 throw new SkipException("User marked this no run");
		 }
		 log("=========>Start verify login");
		 loginpage = new LoginPage(driver);
		 loginpage.loginToApplication(facilityid, password);
		 Assert.assertEquals(true, loginpage.getLoginSuccess());
		 getScreenShot("verifyLogin_" +facilityid);	
	     log("=========>Finished verify login");
	}
	
	
	
}
