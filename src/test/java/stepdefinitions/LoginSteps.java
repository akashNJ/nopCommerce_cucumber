package stepdefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import utilities.DataReader;

public class LoginSteps {

	LoginPage lp;
	List<Map<String, String>> data_entry;

	@Given("user should navigated to login page")
	public void user_should_navigated_to_login_page() {
		BaseClass.logger().info("Opened home page");
		HomePage hp=new HomePage(BaseClass.getDriver());
		hp.clickLogin();
	}

	@When("user enter valid username and password")
	public void user_enter_valid_username_and_password_username_password() throws IOException {
		lp=new LoginPage(BaseClass.getDriver());
		Properties p=BaseClass.getProperties();
		lp.setEmail(p.getProperty("userName"));
		lp.setPassword(p.getProperty("password"));
		BaseClass.logger().info("entered username and password");
	}

	@When("user click on login button")
	public void user_click_on_login_button() {
		lp.clickLogin();
	}

	@Then("user should navigated to My Account page")
	public void user_should_navigated_to_my_account_page() {
		MyAccountPage macc=new MyAccountPage(BaseClass.getDriver());
		boolean status=macc.myAccLink();
		Assert.assertEquals(true, status);
	}

	@When("user enter username and password from excel as {string}")
	public void user_enter_username_and_password_from_excel_as(String data) throws IOException {
		data_entry = DataReader.datamap(".//testdata//nopCommerce.xlsx", "sheet1");
		int row=Integer.parseInt(data)-1;
		lp=new LoginPage(BaseClass.getDriver());
		lp.setEmail(data_entry.get(row).get("USERNAME"));
		lp.setPassword(data_entry.get(row).get("PASSWORD"));
	}

	@Then("user should navigated to My Account page if data is valid {string}")
	public void user_should_navigated_to_My_Account_page_if_data_is_valid(String data) {
		int row=Integer.parseInt(data)-1;
		String result=data_entry.get(row).get("RESULT");
		MyAccountPage macc=new MyAccountPage(BaseClass.getDriver());
		boolean status=macc.myAccLink();
		if(result.equals("invalid")) {
			Assert.assertEquals(false, status);
		}
		else if(result.equals("valid")) {
			Assert.assertEquals(true, status);
		}




	}

}
