package runner;

import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Given;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class Stepdefs extends BasePage {
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();

	@Given("^I am on the login page$")
	public void i_am_on_the_login_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		login.AssertOnLogin();
	}

	@Given("^I click on \"([^\"]*)\"$")
	public void i_click_on(String text) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		clickOnFieldWithText(text);
	}

	@Given("^I enter below detail$")
	public void i_enter_below_detail(List<Map<String, String>> table) throws Throwable {
		login.enterMobileNumber(table);
	}

	@Given("^I am on the Home page$")
	public void i_am_on_the_Home_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		home.AssertOnHomePage();
	}
	
	@Given("^I enter text \"([^\"]*)\" in searchBox$")
	public void i_enter_text_in_searchBox(String text) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    home.enterTextinTextBox(text);
	}
}
