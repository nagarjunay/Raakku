package com.test.automation.RakuRaku.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;



public class LoginPage {
	
	public static final Logger log=Logger.getLogger(LoginPage.class.getName());
	
	WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"txtUserId\"]")
    WebElement loginId;
    
    @FindBy(xpath="//*[@id=\"txtPassword\"]")
    WebElement loginPassword;
    
    @FindBy(xpath="//*[@id=\"btnLogIn\"]")
    WebElement configurationButton;

    @FindBy(xpath="//*[@id=\"errorMessage\"]/h3")
     WebElement パスワードの検証に失敗しました;  
    
    
    public LoginPage(WebDriver driver) 
    {
        PageFactory.initElements(driver, this);
	}
    
     
  public void loginToApplication(String facilityid, String password) throws InterruptedException
  {
	  loginId.clear();
	  loginId.sendKeys(facilityid);
	  log("entered facility id:-"+facilityid+" and object is"+loginId.toString());
	  loginPassword.clear();
	  loginPassword.sendKeys(password);
	  log("entered password:-"+password+" and object is"+loginPassword.toString());
	  Thread.sleep(2000);
	  configurationButton.click();
	  log("Clicked on configuration button is"+configurationButton.toString());
  }
  
  
 public boolean getLoginSuccess() {
	  
	  try {
		 driver.findElement(By.xpath("//*[@id=\"errorMessage\"]/h3")).isDisplayed();
		 return false;
	  }catch(Exception e) {
		  return true;
	  }
	
	  
  }
  
 public void log(String data) {
	log.info(data);
	Reporter.log(data);	
}
  
  
  }
