package com.test.automation.RakuRaku.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class PlaceOrder_RideNow {

	
	public static final Logger log=Logger.getLogger(LoginPage.class.getName());
	
	WebDriver driver;
	
	
    @FindBy(xpath="//*[@id=\"txtName\"]")
    WebElement name; 
    
    @FindBy(xpath="//*[@id=\"ddlVehicleQty\"]")
    WebElement clickoncars; 
  
    @FindBy(xpath="//*[@id=\"ddlVehicleQty\"]/option[2]")
    WebElement selectcars; 
   
    @FindBy(id="txtRemarks")
    WebElement remarks; 
    
    @FindBy(id="btnCallTaxi")
    WebElement callTaxiButton; 
    
    @FindBy(id="btnYes")
    WebElement yesButton; 
    
    @FindBy(xpath="//*[@id=\"message\"]")
    WebElement spinner; 
    
    
    
    
    public PlaceOrder_RideNow(WebDriver driver) 
    {
        PageFactory.initElements(driver, this);
      
	}
    
    
    
  public void loginToApplication(String customername, String location ) throws Exception
  {
	 
	  name.sendKeys(customername);
	  log("entered customer name id:-"+customername+" and object is"+name.toString());
	  Thread.sleep(1000);
	  clickoncars.click();
	  log("Clicked on cars dropdown is"+clickoncars.toString());
	  Thread.sleep(1000);
	  selectcars.click();
	  log("Select cars from dropdown is"+selectcars.toString()); 
	  Thread.sleep(1000);
	  remarks.sendKeys(location);
	  log("entered remarks id:-"+location+" and object is"+remarks.toString());
	  Thread.sleep(1000);
	  callTaxiButton.click();
	  log("Clicked on call taxi button is"+callTaxiButton.toString());
	  Thread.sleep(1000);
	  yesButton.click();
	  log("Clicked on yes button is"+yesButton.toString());
	  
  }
  
  public void log(String data) {
		log.info(data);
		Reporter.log(data);	
	}
  
  
  }
	

