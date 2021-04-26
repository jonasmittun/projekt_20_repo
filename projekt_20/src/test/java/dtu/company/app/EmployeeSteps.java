package dtu.company.app;

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

public class EmployeeSteps {
	
	private Employee employee;
	private CompanyApp companyApp;
	private CompanyApp companyHelper;
	private ErrorMessageHolder errorMessage;
	private Activity activity;
	private Project project;
	
	public EmployeeSteps(CompanyApp companyApp, ErrorMessageHolder errorMessage, CompanyHelper companyHelper) {
		this.companyHelper = companyHelper.getCompanyHelper();
		this.companyApp = companyApp;
		this.errorMessage = errorMessage;
	}
	//
/*
	@Given("there is an activity called {string} with id <{int}>")
	public void there_is_an_activity_called_with_id(String string, Integer id) {
		activity = new Activity(string, id);
	}

	@Given("the project leader <{int}> assigns the activity <{int}> to a project {string}")
	public void the_activity_is_assigned_to_a_project(Integer pl, Integer id, String string) throws Exception {
		project = new Project(string);
		project.addActivity(activity);
		project.setProjectLeaderID(pl);
		companyApp.addProject(project);

		assertTrue(companyApp.getProject(string).containsActivityWithID(id));
	}
	@When("the project leader <{int}> selects an employee <{int}>")
	public void the_project_leader_employee_selects_an_employee_employee(Integer int1, Integer int2) {
		assertTrue(project.getProjectLeaderID()==int1 && companyApp.containsEmployeeWithId(int1));
		assertTrue(project.getProjectLeaderID()!=int2 && companyApp.containsEmployeeWithId(int2));
	}

	@When("the employee <{int}> is currently working on less than <{int}> projects")
	public void the_employee_is_currently_working_on_less_than_projects(Integer int1, Integer int2) {
		assertTrue(companyApp.getEmployee(int1).getActivities() < int2);
	}

	@When("the employee <{int}> is assigned to the activity <{int}> in project {string}")
	public void the_employee_is_assigned_to_the(Integer int1, Integer int2, String string) {
		companyApp.assignEmployee(int1,int2,string);
	}

	@Then("the employee <{int}> is contained in the activity <{int}> in the project {string}")
	public void the_employee_is_contained_in_the_activity_in_the_project(Integer int1, Integer int2, String string) {
		assertTrue(companyApp.getProject(string).getActivityWithID(int2).containsEmployeeWithID(int1));
	}*/
//--------------------
	@Given("there exists an activity {string} with id <{int}> in project {string}")
	public void there_exists_an_activity_with_id_in_project(String string, Integer int1, String string2) {
		assertTrue(companyHelper.getProject(string2).containsActivityWithID(int1));
		assertTrue(companyHelper.getProject(string2).getActivityWithID(int1).getActivityName().equals(string));
	}

	@Given("there exists an employee with id <{int}> which is project leader for project {string}")
	public void there_exists_a_project_leader_with_id(Integer int1, String string) {
		assertTrue(companyHelper.containsEmployeeWithId(int1));
		companyHelper.getProject(string).setProjectLeaderID(int1);
	}

	@Given("a new employee is not registered to the system")
	public void a_new_employee_is_not_registered_to_the_system() {
		employee = companyApp.newEmployee();
		assertFalse(companyApp.containsEmployeeWithId(employee.getId()));
	}

	@When("the employee is added to the system")
	public void the_employee_is_added_to_the_system() {
		employee = companyApp.newEmployee();
		companyApp.addNewEmployee(employee);
	}

	@Then("the employee is registered to the system")
	public void the_employee_is_registered_to_the_system() {
		assertTrue(companyApp.containsEmployeeWithId(employee.getId()));
	}

	@Given("there exists an employee <{int}>")
	public void there_exists_an_employee(Integer int1) {
		assertTrue(companyHelper.containsEmployeeWithId(int1));
	}

	@Given("the employee <{int}> is currently working on less than <{int}> activities")
	public void the_employee_is_currently_working_on_less_than_activities(Integer int1, Integer int2) {
		assertTrue(companyHelper.getEmployee(int1).getActivities() < int2);
	}

	@When("the project leader <{int}> assigns the employee <{int}> to the activity <{int}> in project {string}")
	public void the_project_leader_assigns_the_employee_to_the_activity_in_project(Integer int1, Integer int2, Integer int3, String string) {
		try {
			companyHelper.assignEmployee(int2, int3, string);
		} catch (Exception e){
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee <{int}> is assigned to the activity <{int}> in project {string}")
	public void the_employee_is_contained_in_the_activity_in_the_project(Integer int1, Integer int2, String string) {
		assertTrue(companyHelper.getProject(string).getActivityWithID(int2).containsEmployeeWithID(int1));
	}

	@Given("the employee <{int}> is currently working on <{int}> activities or more")
	public void the_employee_is_currently_working_on_activities_or_more(Integer int1, Integer int2) {
		assertFalse(companyHelper.getEmployee(int1).getActivities() < int2);
	}


}
