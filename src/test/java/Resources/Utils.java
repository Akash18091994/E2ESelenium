package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utils {

    WebDriver driver;
	
	public Utils(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void navigateTo(WebDriver driver, String Main, String SubOption) throws InterruptedException
	{
		this.driver = driver;
		WebDriverWait wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+Main+"']")));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='"+Main+"']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+SubOption+"']")));
		driver.findElement(By.xpath("//a[text()='"+SubOption+"']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='"+SubOption+"'])[2]")));
	}
	
	public void fileUpload(WebDriver driver, String fileName, String formatName, String encoding, String applyOverrides, String comments) throws InterruptedException
	{
		
		WebElement entFile = driver.findElement(By.name("files"));
		Actions action = new Actions(driver);
	    action.moveToElement(entFile).click();
	    entFile.sendKeys(fileName);
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//span[@class='k-icon k-i-arrow-s']")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//li[text()='"+formatName+"']")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("(//span[@class='k-icon k-i-arrow-s'])[2]")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//li[text()='"+encoding+"']")).click();
	    Thread.sleep(3000);
	    if(applyOverrides.equalsIgnoreCase("applyOverridesON"))
	    {
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("window.scrollBy(0,document.body.scrollHeight)");	
	     driver.findElement(By.xpath("//label[text()='Apply Overrides']/following-sibling::input")).click();
	     Thread.sleep(3000);
	    }
	    driver.findElement(By.xpath("//label[text()='Audit Comment']/following-sibling::textarea")).sendKeys(comments);
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//button[text()='Send File']")).click();
	    Thread.sleep(15000);
	    boolean uploadStatus = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[2]/td/div[3]")).isDisplayed();
	    if(uploadStatus == true)
	    {
	    	Assert.assertEquals("ABC", "ABC", "File Upload Success");
	    }
	    else
	    {
	    	Assert.assertEquals("Expected to upload file successfully but failed", " ", "File Upload Failed"); 
	    }
	    
	}
	
	public void setValDate(WebDriver driver, String date) throws InterruptedException
	{
		WebElement valDate = driver.findElement(By.xpath("(//div[@class='ent-application-dates-date']/span/span/input)[1]"));
		valDate.clear();
		Thread.sleep(3000);
		Actions act = new Actions(driver);
		Action seriesOfActions = act.sendKeys(valDate, date).sendKeys(Keys.ENTER).build();
		seriesOfActions.perform();
		Thread.sleep(5000);
	}
	
	public void setFilter(WebDriver driver, String columnName, String operator, String value) throws InterruptedException
	{
		driver.findElement(By.xpath("//th[@data-field='"+columnName+"']/a/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@title='Operator']/span/span[2]/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@class='k-list k-reset']/li[text()='"+operator+"']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@data-bind='value:filters[0].value']")).sendKeys(value);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Filter']")).click();
		Thread.sleep(5000);
	}
	
	public void expandRowAndSelectJob(WebDriver driver, String templateName) throws InterruptedException
	{
		driver.findElement(By.xpath("//table[@data-role='selectable']/tbody/tr/td/a")).click();
		Thread.sleep(5000);
		int rowSize = driver.findElements(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")).size();
		for(int i = 1; i<=rowSize-1 ; i++)
		{
			if(i == 1)
			{
				String jobName = driver.findElement(By.xpath("(//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]/td[3]/span)[2]")).getText();
				if(jobName.equalsIgnoreCase(templateName)) 
				{
					driver.findElement(By.xpath("(//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]/td[3]/span)[2]/parent::td/parent::tr/td/input")).click();
					break;
				}
			}
			else 
			{
			    String jobName = driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr["+i+"]/td[3]/span")).getText();
			    if(jobName.equalsIgnoreCase(templateName)) 
			    {
				  driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr["+i+"]/td[3]/span/parent::td/parent::tr/td/input")).click();
				  break;
			    }
			}
		}
	}
	
	public void runJob(WebDriver driver)
	{
		driver.findElement(By.xpath("//*[text()='Run Selected Jobs']")).click();
	    driver.findElement(By.xpath("//*[text()='Confirm']")).click();
	}
	
	public void verifyJobRunStatus(WebDriver driver, String templateName) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[text()=' Refresh Jobs']")).click();
	    Thread.sleep(20000);
	    driver.findElement(By.xpath("//*[text()=' Refresh Jobs']")).click();
	    int rowSize1 = driver.findElements(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr")).size();
		for(int i = 1; i <= rowSize1-1 ; i++)
		{
			if(i == 1)
			{
				String jobName = driver.findElement(By.xpath("(//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]/td[3]/span)[2]")).getText();
				if(jobName.equalsIgnoreCase(templateName)) 
				{
					String status = driver.findElement(By.xpath("(//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[1]/td[3]/span)[2]/parent::td/parent::tr/td[10]/div/div")).getText();
					if(status.equalsIgnoreCase(" COMPLETED"))
					  {
						  System.out.println("Job run completed in 20 seconds");
						  System.out.println(status);
						  break;
					  }
					  else
					  {
						  System.out.println("Job run not completed in 20 seconds");
						  System.out.println(status);
						  break;
					  }
				}
			}
			else 
			{
			    String jobName = driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr["+i+"]/td[3]/span")).getText();
			    if(jobName.equalsIgnoreCase(templateName)) 
			    {
				  String status = driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr["+i+"]/td[3]/span/parent::td/parent::tr/td[10]/div/div")).getText();
				  if(status.equalsIgnoreCase(" COMPLETED"))
				  {
					  System.out.println("Job run completed in 20 seconds");
					  System.out.println(status);
					  break;
				  }
				  else
				  {
					  System.out.println("Job run not completed in 20 seconds");
					  System.out.println(status);
					  break;
				  }
			    }
			}
		}
	}
	
}
