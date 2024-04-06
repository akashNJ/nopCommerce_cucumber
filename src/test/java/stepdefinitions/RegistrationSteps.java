package stepdefinitions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;

public class RegistrationSteps {

	WebDriver driver;
	AccountRegistrationPage acc;
	@Given("user is navigated to registration page")
	public void user_is_navigated_to_registration_page() {
		driver=BaseClass.getDriver();
		HomePage hp=new HomePage(driver);
		hp.clickRegister();
		BaseClass.logger().info("navigated to registration page");
	}

	@When("user enters registration details")
	public void user_enters_registration_details(DataTable data) throws IOException {
		Map<String, String> details = data.asMap();
		acc=new AccountRegistrationPage(driver);
		acc.setFirstName(details.get("firstName"));
		acc.setLastName(details.get("lastName"));
		String user=BaseClass.randomString()+"@gmail.com";
		acc.setEmail(user);
		String password=BaseClass.randomAlphaNumeric();
		acc.setPassword(password);
		acc.setConfirmPassword(password);
		BaseClass.setProperties(user, password);


	}

	@When("user click on register button")
	public void user_click_on_register_button() {
		acc.clickRegister();
	}

	@Then("registration successful message should be displayed")
	public void registration_successful_message_should_be_displayed() {
		String status=acc.getConfirmationMsg();
		Assert.assertEquals(status, "Your registration completed");
	}

}
