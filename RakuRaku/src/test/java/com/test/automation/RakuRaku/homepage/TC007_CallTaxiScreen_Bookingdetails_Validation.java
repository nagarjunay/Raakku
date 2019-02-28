package com.test.automation.RakuRaku.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.RakuRaku.testBase.TestBase;
import com.test.automation.RakuRaku.uiActions.CallTaxi_Screen;

public class TC007_CallTaxiScreen_Bookingdetails_Validation extends TestBase {
	
public static final Logger log=Logger.getLogger(TC007_CallTaxiScreen_Bookingdetails_Validation.class.getName());
	
	CallTaxi_Screen calltaxiscreen;
	

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
	public void callTaxibutton(String facilityid, String password, String runMode) throws Exception
	{   
		 if(runMode.equalsIgnoreCase("n")) 
		 {
			 throw new SkipException("User marked this no run");
		 }
		 log.info("=========>Started login");
		 calltaxiscreen =new CallTaxi_Screen(driver);
		 calltaxiscreen.Witoutentering_bookingdetails(facilityid, password);
		 String actal_error= driver.findElement(By.xpath("//*[@id=\"frmBookingDetails\"]/div/div[1]")).getText();
	     getScreenShot(actal_error);
	     String expected_error="いくつかのエラーがあります。チェックしてください。";
	     Assert.assertEquals(actal_error, expected_error);
	     log.info("=========>Finished ");
		 
		 
		 
		 log("=========>Finished ");
		 

}
}