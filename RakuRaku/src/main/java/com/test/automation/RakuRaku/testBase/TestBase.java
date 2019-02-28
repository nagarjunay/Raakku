package com.test.automation.RakuRaku.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.RakuRaku.excelReader.Excel_Reader;

public class TestBase {
	
	public static final Logger log=Logger.getLogger(TestBase.class.getName());

	//public static WebDriver dr;
	public static WebDriver driver;
	Excel_Reader excel;
	//public EventFiringWebDriver driver;
	//public  webEventListener eventListener;
	//Listener lis; 
	public Properties OR = new Properties();
	public static ExtentReports extent;
	public static ExtentTest test;
	File file;
	 
	
	
	/*This method is to select browser and url from the config file*/
	public void init() throws IOException 
	{
		loadData();
		selectBrowser(OR.getProperty("browser"));
		//lis= new Listener(driver);
		getUrl(OR.getProperty("url"));
		String log4jConfPath="log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
	}

	
	/*static will load once on runtime, so can genearate single extent report for every test run*/
	static {
		Calendar calendar =Calendar.getInstance() ;
		SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir")+"/target/surefire-reports/RakuRaku_Test_Report.html", true);
		//extent = new ExtentReports(System.getProperty("user.dir")+"/src/main/java/com/test/automation/RakuRaku/report/RakuRaku_Test_Report_"+formater.format(calendar.getTime())+".html", false);
	}
	
	
	
	/*This method loads the data from the config file from main java folder*/
    public void loadData() throws IOException {
    	
    	file = new File(System.getProperty("user.dir")+"/src/main/java/com/test/automation/RakuRaku/Config/config.properties");
    	FileInputStream f = new FileInputStream(file);
    	OR.load(f);
    }

    
    /*This Method will select the different browsers as mentinoed in config properties file*/
	public void selectBrowser(String browser) 
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			log.info("creating object of"+ browser);
			//dr= new ChromeDriver();
			driver= new ChromeDriver();
			//driver= new EventFiringWebDriver(dr);
			//eventListener= new webEventListener();
			//driver.register(eventListener);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			log.info("creating object of"+ browser);
			//dr= new FirefoxDriver();
			driver= new FirefoxDriver();
			//driver= new EventFiringWebDriver(dr);
			//eventListener= new webEventListener();
			//driver.register(eventListener);
		}
	}
	
	
	/*This method will get url and maximizes browser window*/
	public void getUrl(String url) {
		log.info("navigating to"+ url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	}
	
	
	
	/*This method will get the data from the data package from main java*/
	public String[][] getData(String excelName, String sheetName){
		//TestData.xlsx
		String path= System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automation\\RakuRaku\\data\\"+excelName;
		excel= new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
	}
	
	/*This method is used for wait untill element found
	public void waitForElement(int timeOutInSeconds, WebElement element  ) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));		
	}*/
	
	public void expliciteWait(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	/*This Method is to capture screenshots to on calling getScreenShot method */
	public void getScreenShot(String name)
	{
		Calendar calendar =Calendar.getInstance() ;
		SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyy_hh_mm_ss");
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
		     String reportDirectory= new File(System.getProperty("user.dir")).getAbsolutePath()+"\\src\\main\\java\\com\\test\\automation\\RakuRaku\\screenshot\\";
		     File destFile= new File((String)reportDirectory + name +"-" + formater.format(calendar.getTime())+ ".png");
		     FileUtils.copyFile(srcFile,destFile );
		     Reporter.log("<a href='"+destFile.getAbsolutePath()+ "'><img src='" +destFile.getAbsolutePath()+"' height='100' width='100'/></a>");
		}
		catch(IOException e) {
			e.printStackTrace();
		
		}	
	}
	
    /*This method is used to print logs in TestNG reports*/
	public void log(String data) {
		log.info(data);
		Reporter.log(data);	
	}
	
	/*This Method is used for generate Extent reports with screenshots for Pass/Fail test cases*/
	public void getresult(ITestResult result) {
		
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName()+" test is pass"+result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.PASS, test.addScreenCapture(screen));
		}
		
		if(result.getStatus()==ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName()+" test is skipped and skip reason is:-" +result.getThrowable());
		}
		
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName()+" test is failed"+result.getThrowable());
	        String screen = captureScreen("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		}
		else if(result.getStatus()==ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName()+" test is started");
			
		}
		
	}

	/*This Method is used for to capture screenshots in Extent reports*/
	public String captureScreen(String fileName) {
		if(fileName =="") {
			fileName="Screenshot";	
		}
		File destFile=null;
		Calendar calendar =Calendar.getInstance() ;
		SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyy_hh_mm_ss");
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
		     String reportDirectory= new File(System.getProperty("user.dir")).getAbsolutePath()+"\\src\\main\\java\\com\\test\\automation\\RakuRaku\\screenshot\\";
		     destFile= new File((String)reportDirectory + fileName +"-" + formater.format(calendar.getTime())+ ".png");
		     FileUtils.copyFile(srcFile,destFile );
		     //This will help us to link screen shot in TestNg report
		     Reporter.log("<a href='"+destFile.getAbsolutePath()+ "'><img src='" +destFile.getAbsolutePath()+"' height='100' width='100'/></a>");
		}catch(IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		getresult(result);
	}
	
	
	@BeforeMethod
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+"test is started");	
	}
	
	
	@AfterClass(alwaysRun=true)
	public void endTest() {
		closeBrowser();
	}

	public void closeBrowser() {
		driver.quit();
		log.info("browser closed");
		extent.endTest(test);
		extent.flush();	
	}
	
} 
