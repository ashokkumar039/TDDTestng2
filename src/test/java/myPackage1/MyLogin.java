package myPackage1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

public class MyLogin {
	
	public WebDriver driver = null;
	
	public MyLogin() {
		System.out.println("Test Started");
	}
	
	@BeforeTest
	@Parameters("browser")
	public void BeforeTest(String browser) {
		if(browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./BrowserDriver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./BrowserDriver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equals("Edge"))
		{
			System.setProperty("webdriver.edge.driver", "./BrowserDriver/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else
		{
			System.out.println("Failed to setup Browser Driver.");
		}
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	@Parameters({"username", "password"})
	public void Test(String username, String password) {
		driver.get("https://demo.guru99.com/V1/index.php");
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		try {
			driver.switchTo().alert().accept();
		}
		catch(Exception e) {
			
		}
		System.out.println(driver.getTitle());
		driver.navigate().back();
	}
	
	@AfterTest
	public void AfterTest() {
		System.out.println("Test Finished.");
		driver.manage().deleteAllCookies();
		driver.close();
	}
	
	
}
