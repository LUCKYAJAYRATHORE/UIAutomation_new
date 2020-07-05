package page;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class CartPage extends OpenandCloseBrowser
{
	public String goToCart() throws IOException
	{
	WebDriver driver = Connection.getWebDriver();
	driver.findElement(By.xpath("//b[contains(text(),'Cart')]")).click();
	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileHandler.copy(srcFile, new File(".\\src\\screenShots\\Test.png"));
	String path = System.getProperty("user.dir");
	FileHandler.copy(srcFile, new File(path+"/src/screenShots/Cart.png"));
	String actualResult = driver.findElement(By.xpath("//td[@class='cart_description']")).getText();
	System.out.println(actualResult);
	return actualResult;
	}
}
