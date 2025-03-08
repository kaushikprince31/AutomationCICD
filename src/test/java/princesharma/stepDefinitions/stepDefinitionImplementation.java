package princesharma.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import princesharma.PageObjects.CartPage;
import princesharma.PageObjects.CheckoutPage;
import princesharma.PageObjects.ConfirmationPage;
import princesharma.PageObjects.LandingPage;
import princesharma.PageObjects.ProductCatalogue;
import princesharma.TestComponents.BaseTest;

public class stepDefinitionImplementation extends BaseTest {
	// We can also generate this whole code through a Chrome Extension named as "Tidy Gherkin"
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on the Ecommerce page")
	public void I_landed_on_the_Ecommerce_page() throws IOException
	{
	landingpage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$") // Here (.+) is deriving the values from the Examples present in feature file
	public void logged_in_with_username_and_password(String username, String password) 
	{
		productCatalogue = landingpage.loginApplication(username, password);
	}
	
	@When("^I add the product (.+) to cart$")
	public void i_add_the_product_to_cart(String productName)
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the Order$")
	public void checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.quit();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String strng1)
	{
		Assert.assertEquals(strng1, landingpage.getErrorMessage());
		driver.quit();
	}
	

}
