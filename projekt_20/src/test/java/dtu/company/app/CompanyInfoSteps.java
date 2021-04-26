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
	
	@Given("companyApp exists")
	public void company_app_exists() {
	    this.companyApp = new CompanyApp();
	}

	@Given("project {string} exists within companyApp")
	public void project_exists_within_company_app(String string) throws Exception {
	    this.project = new Project(string);
	    this.companyApp.addProject(this.project);
	}
	
	/*	
	@Given("activity {string} exists within project {string}")
	public void activity_exists_within_project(String string, String string2) throws Exception {
	    this.activity = new Activity(string, 1);
	    this.project = new Project(string2);
	    this.companyApp.addProject(this.project);
	    this.companyApp.getProject(string2).addActivity(activity);
	}
	
	@Given("employee with id {string} exists within activity {string}")
	public void employee_with_id_exists_within_activity(String string, String string2) {
		this.activity = this.project.getActivityWithID(Integer.parseInt(string2));
	    int tempId = this.activity.getEmployeeWithId(Integer.parseInt(string));
	    this.employee = this.companyApp.getEmployee(tempId);
	}

	@When("system asks if project {string} exists within companyApp")
	public void system_asks_if_project_exists_within_company_app(String string) {
	    this.companyApp.containsProjectWithName(string);
	}

	@Then("assert that project {string} exists within companyApp")
	public void assert_that_project_exists_within_company_app(String string) {
		assertTrue(this.companyApp.containsProjectWithName(string));
	}

	@When("system asks which projects exist within companyApp")
	public void system_asks_which_projects_exist_within_company_app() {
		this.projects = this.companyApp.getProjects();
	}

	@Then("return arrayList with projects that exist within companyApp")
	public void return_array_list_with_projects_that_exist_within_company_app() {
	    assertTrue(this.companyApp.getProjects().equals(this.projects));
	}

	@Then("assert that activity {string} exists within project {string}")
	public void assert_that_activity_exists_within_project(String string, String string2) {
	    Project tempProject = this.companyApp.getProject(string2);
	    assertTrue(tempProject.containsActivityWithID(this.activity.getActivityID()));
	}

	@When("system asks which activities exist within project {string}")
	public void system_asks_which_activities_exist_within_project(String string) {
	    Project tempProject = this.companyApp.getProject(string);
		this.activities = tempProject.getActivities();
	}

	@Then("return arrayList with activities that exist within project {string}")
	public void return_array_list_with_activities_that_exist_within_project(String string) {
	    assertTrue(this.companyApp.getProject(string).getActivities().equals(activities));
	}

	@When("system asks which activities exist within companyApp")
	public void system_asks_which_activities_exist_within_company_app() {
	    this.activities = this.companyApp.getActivities();
	}

	@Then("return arrayList with activities that exists within companyApp")
	public void return_array_list_with_activities_that_exists_within_company_app() {
		assertTrue(this.activities.equals(companyApp.getActivities()));
	}

	@Then("assert that employee with id {string} exists within activity {string}")
	public void assert_that_employee_with_id_exists_within_activity(String string, String string2) {
		this.activity = this.project.getActivityWithID(Integer.parseInt(string2));
	    assertTrue(this.activity.containsEmployeeWithID(Integer.parseInt(string)));
	}

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
