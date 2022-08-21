package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import POM.logInPage;

public class base {
	
	public WebDriver driver;

	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\91762\\eclipse-workspace_Selenium\\E2E_SeleniumProject\\src\\test\\java\\Resources\\global.properties");
		
		prop.load(fis);
		
		return prop.getProperty(key);
		
	}
	
	public WebDriver initialiseBrowser() throws IOException, InterruptedException
	{
		String browserName = getGlobalValue("Browser");
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\SelWork\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setProxy(null);
			driver = new ChromeDriver(options);		
		}
		
		if(browserName.equalsIgnoreCase("FireFox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\SelWork\\geckodriver.exe");
			driver = new FirefoxDriver();	
		}
		
		if(browserName.equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\SelWork\\msedgedriver.exe");
			driver = new EdgeDriver();	
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(base.getGlobalValue("URL"));
		/* Commenting out below steps as this are specific for APT Eneterprise
		logInPage lp = new logInPage(driver);
		WebDriverWait wait =new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginName']")));
		lp.getUsername().sendKeys(base.getGlobalValue("Username"));
		lp.getPassword().sendKeys(base.getGlobalValue("Password"));
		lp.getLoginButton().click();*/
		Thread.sleep(10000);
		return driver;
	}
	
	public static String getDBValue(String query, String column) throws SQLException
	{
		String dbURL = "jdbc:mysql://vlmazdeventapp3.fisdev.local:3306/Prd";
        
		String user = "ent_dba";
		
		String password = "ent_dbaPassw0rd";
		
		Connection con = DriverManager.getConnection(dbURL, user, password);
		
		Statement s = con.createStatement();
		
		ResultSet r = null;
		
		r = s.executeQuery(query);
		
		r.next();
		
		String data = r.getString(column);
		
		return data;
	}
	
	public String getScreenShotPath(String testMethodName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testMethodName+".png"; 
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}
	
	public static String getExcelValue(String fileName, int sheetNumber, int row, int column) throws IOException
	{
		File file = new File(fileName);    
		FileInputStream fis = new FileInputStream(file);   
		XSSFWorkbook wb = new XSSFWorkbook(fis);   
		XSSFSheet sheet = wb.getSheetAt(sheetNumber);
		Row roww = sheet.getRow(row);
		String cell = roww.getCell(column).toString();
		return cell;
	}
}


