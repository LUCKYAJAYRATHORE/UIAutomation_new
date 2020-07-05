package test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import page.OpenandCloseBrowser;
import page.CartPage;
import page.HomePage;

public class test 
{
	@BeforeClass(alwaysRun = true)
	public void openBrowser() throws InterruptedException 
	{
		OpenandCloseBrowser openandCloseBrowser = new OpenandCloseBrowser();
		openandCloseBrowser.open();
	}
	@Test
	public void test1() throws Throwable 
	{
        HomePage homePage = new HomePage();
        String expectedResult = homePage.addToCart();
        homePage.closeInfo();
        CartPage cartPage = new CartPage();
       String actualResult =  cartPage.goToCart();
       Assert.assertTrue(((actualResult).contains(expectedResult.trim())) , "Product not added successfully");
    }
	
	@Test
	public void test2() throws Throwable 
	{
        HomePage homePage = new HomePage();
        homePage.validateDiscount();
    }
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException 
	{
		OpenandCloseBrowser openandCloseBrowser = new OpenandCloseBrowser();
		openandCloseBrowser.close();
	}
}