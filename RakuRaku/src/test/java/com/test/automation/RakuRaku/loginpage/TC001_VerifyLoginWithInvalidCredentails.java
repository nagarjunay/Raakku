package com.test.automation.RakuRaku.loginpage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.RakuRaku.testBase.TestBase;
import com.test.automation.RakuRaku.uiActions.LoginPage;


public class TC001_VerifyLoginWithInvalidCredentails extends TestBase{
	
	public static final Logger log=Logger.getLogger(TC001_VerifyLoginWithInvalidCredentails.class.getName());
	
LoginPage loginpage;


@BeforeClass	
public void setUp() throws IOException 
{
	 init();
}



@Test(priority=2)
public void verifyLogin_InvalidID_ValidPassword() throws Exception
{
	 log.info("=========>Start verifyInvalidID_ValidPassword");
	 loginpage = new LoginPage(driver);
	 loginpage.loginToApplication("heloooo", "ift");
     String actal_error= driver.findElement(By.xpath("//*[@id=\"errorMessage\"]/h3")).getText();
     getScreenShot(actal_error);
     String expected_error="パスワードの検証に失敗しました";
     Assert.assertEquals(actal_error, expected_error);
     log.info("=========>Finished verifyInvalidID_ValidPassword");
}

@Test(priority=1)
public void verifyLogin_EmptyID_EmptyPassword() throws Exception
{
	 log.info("=========>Start verifyEmptyID_EmptyPassword");
	 loginpage = new LoginPage(driver);
	 loginpage.loginToApplication("", "");
     String actal_error= driver.findElement(By.xpath("//*[@id=\"errorMessage\"]/h3")).getText();
     String expected_error="この施設のID及びパスワードを入力してください";
     Assert.assertEquals(actal_error, expected_error);
     log.info("=========>Finished verifyEmptyID_EmptyPassword");
}

@Test(priority=3)
public void verifyLogin_EmptyID_ValidPassword() throws Exception
{
	 log.info("=========>Start verifyLogin_EmptyID_ValidPassword");
	 loginpage = new LoginPage(driver);
	 loginpage.loginToApplication("", "ift");
     String actal_error= driver.findElement(By.xpath("//*[@id=\"errorMessage\"]/h3")).getText();
     String expected_error="施設IDを入力してください";
     Assert.assertEquals(actal_error, expected_error);
     log.info("=========>Finished verifyLogin_EmptyID_ValidPassword");
}


@Test(priority=4)
public void verifyLogin_ValidID_EmptyPassword() throws Exception
{
	 log.info("=========>Start verifyLogin_ValidID_EmptyPassword");
	 loginpage = new LoginPage(driver);
	 loginpage.loginToApplication("fp-ifttest", "");
     String actal_error= driver.findElement(By.xpath("//*[@id=\"errorMessage\"]/h3")).getText();
     String expected_error="パスワードを入力してください";
     Assert.assertEquals(actal_error, expected_error);
     log.info("=========>Finished verifyLogin_ValidID_EmptyPassword");
}


@Test(priority=5)
public void verifyLogin_InvalidID_InvalidPassword() throws Exception
{
	 log.info("=========>Start verifyLogin_InvalidID_InvalidPassword");
	 loginpage = new LoginPage(driver);
	 loginpage.loginToApplication("ifttest", "helllo");
     String actal_error= driver.findElement(By.xpath("//*[@id=\"errorMessage\"]/h3")).getText();
     String expected_error="パスワードの検証に失敗しました";
     Assert.assertEquals(actal_error, expected_error);
     log.info("=========>Finished verifyLogin_InvalidID_InvalidPassword");
}

@Test(priority=6)
public void verifyLogin_InvalidID_EmptyPassword() throws Exception
{
	 log.info("=========>Start verifyLogin_InvalidID_EmptyPassword");
	 loginpage = new LoginPage(driver);
	 loginpage.loginToApplication("ifttest", "");
     String actal_error= driver.findElement(By.xpath("//*[@id=\"errorMessage\"]/h3")).getText();
     String expected_error="パスワードを入力してください";
     Assert.assertEquals(actal_error, expected_error);	
     log.info("=========>Finished verifyLogin_InvalidID_EmptyPassword");
}

@Test(priority=7)
public void verifyLogin_EmptyID_InvalidPassword() throws Exception
{
	 log.info("=========>Start verifyLogin_EmptyID_InvalidPassword");
	 loginpage = new LoginPage(driver);
	 loginpage.loginToApplication("", "123");
     String actal_error= driver.findElement(By.xpath("//*[@id=\"errorMessage\"]/h3")).getText();
     String expected_error="施設IDを入力してください";
     Assert.assertEquals(actal_error, expected_error);	
     log.info("=========>Finished verifyLogin_EmptyID_InvalidPassword");
}


}
