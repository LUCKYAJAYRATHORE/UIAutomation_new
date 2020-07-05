package page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;


public class OpenandCloseBrowser {
	
	protected String windowHandle = null;
	protected String callersWindowHandle = null;
	
	
	public void open() {
		String baseUrl = "http://automationpractice.com/";
        // launch Chrome and direct it to the Base URL
	 Connection.getWebDriver().get(baseUrl);
	 Connection.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 Connection.getWebDriver().manage().window().maximize(); //maximize browser
	 Connection.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	public void close() {
        WebDriver wd = Connection.getWebDriver();
        wd.close();
        if (callersWindowHandle != null) {
            wd.switchTo().window(callersWindowHandle);
        }
	}
	
}
