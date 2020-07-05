package page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends OpenandCloseBrowser 
{
	public String addToCart()
	{
		WebDriver driver = Connection.getWebDriver();
		driver.findElement(By.xpath("//a[@class='homefeatured']")).click();
        List<WebElement> listPrice = driver.findElements(By.xpath("//ul[@id=\"homefeatured\"]//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//div[@class='right-block']//div[@itemtype=\"http://schema.org/Offer\"]//span[@class='price product-price']"));
       WebElement lowestpriceElement = null;
       float lowestPrice =Float.parseFloat(listPrice.get(0).getText().trim().substring(1));
        System.out.println(listPrice.size());
        int count = 0;
        for(WebElement price : listPrice)
       {
		float Valueprice =Float.parseFloat(price.getText().trim().substring(1));
    	  if( Valueprice <= lowestPrice )
    	   {
    		  lowestpriceElement =  price;
    	   }
    	  System.out.println(lowestpriceElement);
    	  System.out.println(lowestpriceElement.getText());
    	  count = listPrice.indexOf(lowestpriceElement);
    	  System.out.println(count);
       }
        By nameBy = By.xpath(String.format("(//ul[@id='homefeatured']//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//div[@class='right-block']"
     	 		+ "//a[@class=\"product-name\"])[%d]", count+1));
        String expectedResult =  driver.findElement(nameBy).getText();
        System.out.println(expectedResult);
        	 System.out.println(lowestpriceElement.getText());
        	 
        	 Actions actions = new Actions(driver);
        	 actions.moveToElement(lowestpriceElement).perform();
        	 WebDriverWait wait = new WebDriverWait(driver,90);
        	 By addToCartBy = By.xpath(String.format("(//ul[@id='homefeatured']//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//div[@class='right-block']"
         	 		+ "//span[contains(text(), 'Add to cart')])[%d]", count+1));
        	 wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBy));
        	 driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        	 driver.findElement(addToCartBy).click();
        	 return expectedResult;
	}
	
	public void closeInfo()
	{
	WebDriver driver = Connection.getWebDriver();
	 WebDriverWait wait = new WebDriverWait(driver,90);
	 driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(),'Cart')]")));
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cross']")));
	 driver.findElement(By.xpath("//span[@class='cross']")).click();
	}
	
	public void validateDiscount() throws IOException
	{
	WebDriver driver = Connection.getWebDriver();
	driver.findElement(By.xpath("//img[@class='logo img-responsive']")).click();
	List<WebElement> listprice = driver.findElements(By.xpath("//ul[@id='homefeatured']//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//div[@class='right-block']//div[@class=\"content_price\"]"));
    List<String> listDiscountedName = new ArrayList<String>();
    List<Float> listDiscountedPrice = new ArrayList<Float>();
    int newcount = 0;
    for(WebElement price : listprice)
   {
   	 System.out.println(price.getText());
   	 if (price.getText().contains("%"))
   	 	{
   		 By discountedProductnameBy = By.xpath(String.format("(//ul[@id='homefeatured']//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//div[@class='right-block']"
   	     	 		+ "//a[@class=\"product-name\"])[%d]", newcount+1));
   		 System.out.println(driver.findElement(discountedProductnameBy).getText());
   		 listDiscountedName.add(driver.findElement(discountedProductnameBy).getText());
   		 By discountedProductPriceBy = By.xpath(String.format("(//ul[@id='homefeatured']//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//div[@class='right-block']"
   	     	 		+ "//span[@class='price product-price'])[%d]", newcount+1));
   		 System.out.println(driver.findElement(discountedProductPriceBy).getText());
   		 float Discountedprice =Float.parseFloat(driver.findElement(discountedProductPriceBy).getText().trim().substring(1));
   		 Discountedprice = Math.round(Discountedprice*100)/100;
   		 listDiscountedPrice.add(Discountedprice);
   	 } 
   	 newcount++;
   }
    System.out.println("Discounted prodcuts are"+ listDiscountedName);
    System.out.println(listDiscountedPrice);
    List<WebElement> listOriginalPrice = driver.findElements(By.xpath("//ul[@id='homefeatured']//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//div[@class='right-block']//div[@class='content_price']//span[@class='old-price product-price']"));
    List<WebElement> listdiscount = driver.findElements(By.xpath("//ul[@id='homefeatured']//li[contains(@class,'ajax_block_product col-xs-12 col-sm-4 col-md-3')]//div[@class='right-block']//div[@class=\"content_price\"]//span[@class=\"price-percent-reduction\"]"));
    List<Float> listExpectedPrice = new ArrayList<Float>();
    int i = 0;
    for(WebElement price : listOriginalPrice)
   {
   	 float originalPrice = Float.parseFloat(price.getText().trim().substring(1));
   	 System.out.println(price.getText());
   	 System.out.println(originalPrice);
   	 float discount = Float.parseFloat(listdiscount.get(i).getText().split("%")[0].trim());
   	 System.out.println(discount);
   	 float expectedPrice = originalPrice + (originalPrice * discount) /100;
   	 expectedPrice = Math.round(expectedPrice*100)/100;
   	 System.out.println(expectedPrice);
   	 listExpectedPrice.add(expectedPrice);
   	 i++;
   }
    System.out.println(listDiscountedPrice);
    System.out.println(listExpectedPrice);
    Assert.assertTrue(((listExpectedPrice).equals(listDiscountedPrice)));
	}
}
