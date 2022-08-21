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

public class BasicTest extends base{
	
    public WebDriver driver;
    
    @BeforeMethod
    public void config() throws IOException, InterruptedException
    {
    	driver = initialiseBrowser();
    }
    
    @Test
	public void basicSteps11() throws InterruptedException, IOException
	{
		Assert.assertEquals(driver.getTitle(), "Online shopping");
		/* Commenting out below steps as this are specific for APT Eneterprise
		Utils u = new Utils(driver);
		u.navigateTo(driver, "Data Load", "Data Upload");
		Thread.sleep(5000);
		String fileName = "C:\\Users\\e5647787\\Downloads\\geckodriver-v0.31.0-win64.zip";
		u.fileUpload(driver, fileName,"APT Text", "ISO-8859-1", "applyOverridesON", "This are comments");
		u.navigateTo(driver, "Job Management", "Job Monitoring");*/
	}
    
    @Test
    public void basicSteps12() throws IOException, InterruptedException
    {
		Assert.assertEquals(driver.getTitle(), "FIS Investment Risk Hub11");
		
    }

	@Test
	public void genericFunctionsTest() throws SAXException, IOException, SQLException
	{
		String file6 = "C:\\Users\\91762\\Downloads\\booksData.xlsx";
		String file7 = "C:\\Users\\91762\\Downloads\\booksData - Copy.xlsx";
		genericFunctions.compareExcel(file6, file7);
		
		String fileName = "C:\\Users\\91762\\Downloads\\booksData.xlsx";
		int sheetNumber = 0;
		int row = 1;
		int column1 = 1;
		System.out.println(base.getExcelValue(fileName, sheetNumber, row, column1));
		
		/* Commenting out below steps because to execute below steps we need CSC, XML files and DB connection respectively
		String file1="C:\\Users\\e5647787\\Downloads\\ActualCSV.csv";
	    String file2="C:\\Users\\e5647787\\Downloads\\ExpectedCSV.csv";
	    String file3="C:\\Users\\e5647787\\Downloads\\Difference.csv";
	    genericFunctions.compareCSV(file1, file2, file3);
		
		String file4="C:\\Users\\e5647787\\Downloads\\EnterpriseWorkspace - Copy.xml";
	    String file5="C:\\Users\\e5647787\\Downloads\\EnterpriseWorkspace.xml";
		genericFunctions.compareXML(file4, file5);
		
		String query = "Select * from Prd.Security order by 1 desc;";
		String column = "SecurityKey";
		String dbValue = base.getDBValue(query, column);
		System.out.println("Security Key is --> " + dbValue);
		*/
		
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
	}
	
	@AfterSuite
	public void sendMail() throws EmailException
	{
		
	}
}
