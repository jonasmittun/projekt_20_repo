package dtu.company.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
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
	private String input; 
	
	public ControllerSteps() {
		this.errorMessage = errorMessage;
		this.companyApp = new CompanyApp();
	}
// FUCKED
/*	@Given("program is not started")
	public void program_is_not_started() {
	    assertFalse(Controller.view.test[0]);
	}

	@When("someone starts the program")
	public void someone_starts_the_program() {
	    input = "1 \n y \n 9 \n something";
	}

	@Then("display first program screens")
	public void display_first_program_screens() throws Exception {
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Controller.main(null);
		System.setIn(System.in);
		assertTrue(Controller.view.test[0]);
	}

	@When("someone inputs valid id <{int}>")
	public void someone_inputs_valid_id(Integer int1) {
		input = "" + int1;
	}

	@When("someone confirms by inputting {string}")
	public void someone_confirms_by_inputting(String string) {
		input = input + " \n " + string;
	}

	@Then("display main menu screen")
	public void display_main_menu_screen() throws Exception {
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Controller.main(null);
		System.setIn(System.in);
		assertTrue(Controller.view.test[31]);
	}

	@When("someone declines by inputting {string}")
	public void someone_declines_by_inputting(String string) {
		input = "1 \n n \n 1 \n y \n 9 \n something";
	}

	@Then("display input user ID screen")
	public void display_input_user_id_screen() throws Exception {
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Controller.main(null);
		System.setIn(System.in);
		assertTrue(Controller.view.test[4]);
		System.out.println(Controller.view.test[4]);
	}

	@When("someone inputs invalid id <{int}>")
	public void someone_inputs_invalid_id(Integer int1) {
		input = "9999 \n 1 \n y \n 1 \n y \n 9 \n something";
	}

	@Then("display error screen")
	public void display_error_screen() throws Exception {
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Controller.main(null);
		System.setIn(System.in);
		assertTrue(Controller.view.test[32]);
	}

	@Then("display input user ID screen on any input")
	public void display_input_user_id_screen_on_any_input() throws Exception {
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Controller.main(null);
		System.setIn(System.in);
		assertTrue(Controller.view.test[2]);
	}
*/	
}
