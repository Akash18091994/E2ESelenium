package APTEnterprise.UIAutomation;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class ZZZ {

	@Test
	public void genericFunctionsTest() throws SAXException, IOException, SQLException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\SelWork\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setProxy(null);
		WebDriver driver = new ChromeDriver(options);			   
		System.out.println(System.getProperty("user.dir"));
		driver.get("https://www.amazon.in/");
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+"ABC"+".png"; 
		FileUtils.copyFile(source, new File(destinationFile));
	}
}
