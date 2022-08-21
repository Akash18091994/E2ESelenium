package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class logInPage {

	WebDriver driver;

	public logInPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By username = By.xpath("//input[@id='loginName']");
	By password = By.xpath("//input[@type='password']");
	By loginButton = By.xpath("//button[@type='submit']");
	
	public WebElement getUsername()
	{
		return driver.findElement(username);
	}
	
	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	
	public WebElement getLoginButton()
	{
		return driver.findElement(loginButton);
	}
	
}
