package dtu.company.app;

import java.util.ArrayList;

import dtu.company.app.CompanyApp;
import dtu.company.app.Employee;
import dtu.company.app.Project;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CompanyInfoSteps {
	
	private Employee employee;
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private CompanyApp companyApp;
	private ErrorMessageHolder errorMessage;
	private Activity activity;
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	private Project project;
	private ArrayList<Project> projects = new ArrayList<Project>();
	
	public CompanyInfoSteps(CompanyApp companyApp, ErrorMessageHolder errorMessage) {
		this.companyApp = companyApp;
		this.errorMessage = errorMessage;
	}
	
	//Check if project named "" exists
	
	@Given("companyApp exists")
	public void company_app_exists() {
	    this.companyApp = new CompanyApp();
	}

	@Given("project {string} exists within companyApp")
	public void project_exists_within_company_app(String string) throws Exception {
	    this.project = new Project(string);
	    this.companyApp.addProject(this.project);
	}
	
	@When("system asks if project {string} exists within companyApp")
	public void system_asks_if_project_exists_within_company_app(String string) {
	    this.companyApp.containsProjectWithName(string);
	}
	
	@Then("assert that project {string} exists within companyApp")
	public void assert_that_project_exists_within_company_app(String string) {
		assertTrue(this.companyApp.containsProjectWithName(string));
	}
	
	//Check which projects exist within companyApp
	
	//@Given("companyApp exists")
	
	@When("system asks which projects exist within companyApp")
	public void system_asks_which_projects_exist_within_company_app() {
		this.projects = this.companyApp.getProjects();
	}
	
	@Then("return arrayList with projects that exist within companyApp")
	public void return_array_list_with_projects_that_exist_within_company_app() {
	    assertTrue(this.companyApp.getProjects().equals(this.projects));
	}
	
	//Check if activity with id "" exists within project ""
	
	//@Given("project {string} exists within companyApp")
	
	@Given("activity with id <{int}> exists within project {string}")
	public void activity_with_id_exists_within_project(Integer int1, String string) throws Exception {
	    this.activity = new Activity(int1);
	    this.companyApp.getProject(string).addActivity(this.activity);
	}
	
	@Then("assert that activity with id <{int}> exists within project {string}")
	public void assert_that_activity_with_id_exists_within_project(Integer int1, String string) {
	    assertTrue(companyApp.getProject(string).getActivityWithID(int1).getActivityID() == int1);
	}
	
	//Check which activities exist within project ""
	
	//@Given("project {string} exists within companyApp")
	
	@When("system asks which activities exist within project {string}")
	public void system_asks_which_activities_exist_within_project(String string) {
		this.activities = this.companyApp.getProject(string).getActivities();
	}
	
	@Then("return arrayList with activities that exist within project {string}")
	public void return_array_list_with_activities_that_exist_within_project(String string) {
	    assertTrue(this.companyApp.getProject(string).getActivities().equals(activities));
	}
	
	//Check which activities exist within companyApp
	
	//@Given("companyApp exists")
	
	@When("system asks which activities exist within companyApp")
	public void system_asks_which_activities_exist_within_company_app() {
	    this.activities = this.companyApp.getActivities();
	}
	
	@Then("return arrayList with activities that exists within companyApp")
	public void return_array_list_with_activities_that_exists_within_company_app() {
		assertTrue(this.activities.equals(this.companyApp.getActivities()));
	}
	
	//Check if employee with id <> exists within activity with id <>
	
	//@Given("project {string} exists within companyApp")
	
	//@Given("activity with id <{int}> exists within project {string}")
	
	@Given("employee with id <{int}> exists within activity with id <{int}>")
	public void employee_with_id_exists_within_activity_with_id(Integer int1, Integer int2) throws Exception {
		this.activity = new Activity(int2);
		this.employee = new Employee(int1);
	    this.activity.assignEmployee(int1, 0);
	}
	
	@Then("assert that employee with id <{int}> exists within activity with id <{int}>")
	public void assert_that_employee_with_id_exists_within_activity_with_id(Integer int1, Integer int2) {
	    assertTrue(this.activity.getActivityID() == int2 && this.activity.containsEmployeeWithID(int1));
	}
	
	//
	
	/*	
	@Given("employee with id {string} exists within project {string}")
	public void employee_with_id_exists_within_project(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("assert that employee with id {string} exists within project {string}")
	public void assert_that_employee_with_id_exists_within_project(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("employee with id {string} exists within companyApp")
	public void employee_with_id_exists_within_company_app(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}*/
}
