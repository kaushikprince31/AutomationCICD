package princesharma.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import princesharma.PageObjects.CartPage;
import princesharma.PageObjects.CheckoutPage;
import princesharma.PageObjects.ConfirmationPage;
import princesharma.PageObjects.OrderPage;
import princesharma.PageObjects.ProductCatalogue;
import princesharma.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups="Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException {
		
		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods="submitOrder")
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingpage.loginApplication("kaushikprince31@gmail.com", "KKKKKkkkkk@9");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));	
	}
	

	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\princesharma\\data\\PurchaseOrder.json");
		// We are using Json file to create HashMap and extracting data from it
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

/*	@DataProvider
	public Object[][] getData()
	{
		//		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "kaushikprince31@gmail.com");
		map.put("password", "KKKKKkkkkk@9");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "prince123@gmail.com");
		map1.put("password", "KKKKKkkkkk@98136");
		map1.put("product", "ADIDAS ORIGINAL");//
		return new Object[][] {{"kaushikprince31@gmail.com", "KKKKKkkkkk@9", "ZARA COAT 3"}, {"prince@gmail.com", "KKKKKkkkkk@90", "ADIDAS ORIGINAL"}};

	}*/

}
