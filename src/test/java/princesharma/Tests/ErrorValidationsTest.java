package princesharma.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import princesharma.PageObjects.CartPage;
import princesharma.PageObjects.CheckoutPage;
import princesharma.PageObjects.ConfirmationPage;
import princesharma.PageObjects.LandingPage;
import princesharma.PageObjects.ProductCatalogue;
import princesharma.TestComponents.BaseTest;
import princesharma.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		landingpage.loginApplication("kaushikprince31@gmail.com", "KKKKKkkkkk@90");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("kaushikprince31@gmail.com", "KKKKKkkkkk@9");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 30");
		Assert.assertFalse(match);
		
	}
	


}
