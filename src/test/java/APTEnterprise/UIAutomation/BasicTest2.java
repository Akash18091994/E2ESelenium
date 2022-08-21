package APTEnterprise.UIAutomation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import GenericFunctions.genericFunctions;
import POM.logInPage;
import Resources.Utils;
import Resources.base;

public class BasicTest2 extends base{
	
	public WebDriver driver;
    
    @BeforeMethod
    public void config() throws IOException, InterruptedException
    {
    	driver = initialiseBrowser();
    }
     
    @Test
	public void basicSteps21() throws InterruptedException, IOException
	{
		Assert.assertEquals(driver.getTitle(), "FIS Investment Risk Hub22");
		
	}
    
    @Test
    public void basicSteps22() throws IOException, InterruptedException
    {
		Assert.assertEquals(driver.getTitle(), "FIS Investment Risk Hub");
		
    }

    @AfterMethod
	public void closeBrowser()
	{
		driver.quit();
	}
	
}
