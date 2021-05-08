package dtu.company.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ControllerSteps {
	
	private Employee employee;
	private CompanyApp companyApp;
	private ErrorMessageHolder errorMessage;
	private Activity activity;
	private Project project;
	
	public ControllerSteps() {
		this.errorMessage = errorMessage;
	}

	@Given("program is not started")
	public void program_is_not_started() {
	    assertTrue(Controller.companyApp.equals(null));
	}

	@When("someone starts the program")
	public void someone_starts_the_program() throws Exception {
	    Controller.main(null);
	}

	@Then("display first program screens")
	public void display_first_program_screens() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("someone inputs valid id <{int}>")
	public void someone_inputs_valid_id(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("someone confirms by inputting {string}")
	public void someone_confirms_by_inputting(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("display main menu screen")
	public void display_main_menu_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("someone declines by inputting {string}")
	public void someone_declines_by_inputting(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("display input user ID screen")
	public void display_input_user_id_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("someone inputs invalid id <{int}>")
	public void someone_inputs_invalid_id(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("display error screen")
	public void display_error_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("display input user ID screen on any input")
	public void display_input_user_id_screen_on_any_input() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
}
