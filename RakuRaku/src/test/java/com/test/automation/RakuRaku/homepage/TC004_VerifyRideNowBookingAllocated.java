package com.test.automation.RakuRaku.homepage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.test.automation.RakuRaku.testBase.TestBase;
import com.test.automation.RakuRaku.uiActions.LoginPage;
import com.test.automation.RakuRaku.uiActions.PlaceOrder_RideNow;

public class TC004_VerifyRideNowBookingAllocated extends TestBase{
	
	
public static final Logger log=Logger.getLogger(TC004_VerifyRideNowBookingAllocated.class.getName());
	
	PlaceOrder_RideNow ridenow;
	LoginPage loginpage;
    String customername="Reo";
    String location="Landmark";
    
    
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
	public void rideNow_TaxiAllocated(String facilityid, String password, String runMode) throws Exception
	{
		if(runMode.equalsIgnoreCase("n")) 
		 {
			 throw new SkipException("User marked this no run");
		 }
		 loginpage = new LoginPage(driver);
		 loginpage.loginToApplication(facilityid, password);
		 getScreenShot(password);
		 log("=========>Start rideNow_TaxiAllocated");
		 ridenow = new PlaceOrder_RideNow(driver);
		 ridenow.loginToApplication(customername, location);
		 getScreenShot(location);
		 log("=========>Spinner loading");
		 FluentWait<WebDriver> wait1 = new FluentWait<WebDriver>(driver);
	     /*It should poll webelement after every fifteen seconds*/
	     wait1.pollingEvery(15, TimeUnit.SECONDS);
	     /*Max time for wait- If conditions are not met within this time frame then it will fail the script*/
	     wait1.withTimeout(240, TimeUnit.SECONDS);
	     /* we are creating Function here which accept webdriver and output as WebElement*/
	     WebElement element = wait1.until(new Function<WebDriver, WebElement>() {
	     /*apply method- which accept webdriver as input*/
	     public WebElement apply(WebDriver arg0) 
	      {
	      /*find the element id, xpath*/
	        WebElement ele = arg0.findElement(By.xpath("//*[@id=\"message\"]"));
	        
	        /*Will capture the inner Text and will compare will WebDriver
	         If condition is true then it will return the element and wait will be over*/

	           
	             if (ele.getAttribute("innerHTML").equalsIgnoreCase("ご注文ありがとうございます。")) 
	              {  
	            	 
	            	 getScreenShot("ご注文ありがとうございます。");
	                System.out.println("Please Wait..... Allocating Taxi.." + ele.getAttribute("innerHTML"));

	                  return ele;
	              }

	           /*If condition is not true then it will return null and it will keep checking until condition is not true*/
	                  else 
	              {
	                   System.out.println("Please Wait..... Allocating Taxi.." + ele.getAttribute("innerHTML"));
	                  return null;

	              }
	              }

	         });

	             /*If element is found then it will display the status*/

	                  System.out.println("The order has been accepted..>>>>> " + element.getAttribute("innerHTML")); 
	                  log("=========>Finished rideNow_TaxiAllocated");
	}
	  
	 
	
	

	}
	
	
	
	


