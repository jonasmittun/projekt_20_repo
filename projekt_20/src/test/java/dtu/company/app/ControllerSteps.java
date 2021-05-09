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
	private String output;
	private ByteArrayOutputStream holder;
	
	public ControllerSteps() {
		this.errorMessage = errorMessage;
	}

	@Given("program is not started")
	public void program_is_not_started() {
		Controller.companyApp = null;
	    assertTrue(Controller.companyApp == null);
	}

	@When("someone starts the program")
	public void someone_starts_the_program() {
	    input = "1 \n y \n 9 \n something";
	}

	@Then("display first program screens")
	public void display_first_program_screens() throws Exception {
		holder = new ByteArrayOutputStream();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		PrintStream ps = new PrintStream(holder);
		System.setOut(ps);
		Controller.main(null);
		ps.close();
		System.setIn(System.in);
		System.setOut(System.out);
		output = holder.toString();
		System.out.println("\n test1 \n " + output + "\n test1 \n ");
		assertTrue(output.contains("Software started successfully!"));
		System.exit(0);
	}

	@When("someone inputs valid id <{int}>")
	public void someone_inputs_valid_id(Integer int1) {
		input = "" + int1;
	}

	@When("someone confirms by inputting {string}")
	public void someone_confirms_by_inputting(String string) {
		input += " \n" + string;
	}

	@Then("display main menu screen")
	public void display_main_menu_screen() throws Exception {
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Controller.main(null);
		System.setIn(System.in);
		assertTrue(System.out.toString().contains("Welcome to [CompanyName]'s [SoftwareName],"));
		System.exit(0);
	}

	@When("someone declines by inputting {string}")
	public void someone_declines_by_inputting(String string) {
		input += " \n" + string;
	}

	@Then("display input user ID screen")
	public void display_input_user_id_screen() throws Exception {
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Controller.main(null);
		System.setIn(System.in);
		assertTrue(System.out.toString().contains("Going back..."));
		System.exit(0);
	}

	@When("someone inputs invalid id <{int}>")
	public void someone_inputs_invalid_id(Integer int1) {
		throw new io.cucumber.java.PendingException();
	}

	@Then("display error screen")
	public void display_error_screen() {
		throw new io.cucumber.java.PendingException();
	}

	@Then("display input user ID screen on any input")
	public void display_input_user_id_screen_on_any_input() {
		throw new io.cucumber.java.PendingException();
	}
	
}
