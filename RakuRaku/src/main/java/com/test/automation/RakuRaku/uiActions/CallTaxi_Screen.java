package com.test.automation.RakuRaku.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.test.automation.RakuRaku.testBase.TestBase;

public class CallTaxi_Screen extends TestBase {

public static final Logger log=Logger.getLogger(CallTaxi_Screen.class.getName());
	
	WebDriver driver;
	
	
	@FindBy(xpath="//*[@id=\"txtUserId\"]")
    WebElement loginId;
    
    @FindBy(xpath="//*[@id=\"txtPassword\"]")
    WebElement loginPassword;
    
    @FindBy(xpath="//*[@id=\"btnLogIn\"]")
    WebElement configurationButton;

	@FindBy(xpath="//*[@id=\"btnCallTaxi\"]")
    WebElement callTaxiButton;  
	
	 
    
	
	 public CallTaxi_Screen(WebDriver driver) 
	    {
	        PageFactory.initElements(driver, this);
	      
		}
	    
	
	
	public void Witoutentering_bookingdetails( String facilityid, String password) throws Exception {
		
		
		  loginId.clear();
		  loginId.sendKeys(facilityid);
		  log("entered facility id:-"+facilityid+" and object is"+loginId.toString());
		  loginPassword.clear();
		  loginPassword.sendKeys(password);
		  log("entered password:-"+password+" and object is"+loginPassword.toString());
		  Thread.sleep(2000);
		  configurationButton.click();
		  log("Clicked on configuration button is"+configurationButton.toString());
		  Thread.sleep(3000);
		  callTaxiButton.click();
		  log("Clicked on call taxi button is"+callTaxiButton.toString());
		
	}
	
	
	
	
	public void log(String data) {
		log.info(data);
		Reporter.log(data);	
	}
	
	
	
	
	
	
	
	
	
	
}
