package dtu.company.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeSteps {
	
	private Employee employee;
	//private CompanyApp companyApp;
	private CompanyApp companyApp;
	private ErrorMessageHolder errorMessage;
	private Activity activity;
	private Project project;
	
	public EmployeeSteps(ErrorMessageHolder errorMessage, CompanyHelper companyHelper) {
		this.companyApp = companyHelper.getCompanyHelper();
		this.errorMessage = errorMessage;
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

	@Given("the employee <{int}> is currently working on less than <{int}> activities")
	public void the_employee_is_currently_working_on_less_than_activities(Integer int1, Integer int2) {
		assertTrue(companyApp.getEmployee(int1).getNumberOfActivities() < int2);
	}

	@When("the project leader <{int}> assigns the employee <{int}> to the activity <{int}> in project {string}")
	public void the_project_leader_assigns_the_employee_to_the_activity_in_project(Integer int1, Integer int2, Integer int3, String string) {
		try {
			companyApp.assignEmployee(int2, int3, string);
		} catch (Exception e){
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee <{int}> is assigned to the activity <{int}> in project {string}")
	public void the_employee_is_contained_in_the_activity_in_the_project(Integer int1, Integer int2, String string) {
		assertTrue(companyApp.getProject(string).getActivityWithID(int2).containsEmployeeWithID(int1));
	}

//	@Given("the employee <{int}> is currently working on <{int}> activities or more")
//	public void the_employee_is_currently_working_on_activities_or_more(Integer int1, Integer int2) {
//		assertFalse(companyApp.getEmployee(int1).getNumberOfActivities() < int2);
//	}

	@When("the employee <{int}> is assigned as project leader of the project {string}")
	public void the_employee_is_assigned_as_project_leader_of_the_project(Integer int1, String string) {
		this.project = new Project(string);
		project.setProjectLeaderID(int1);
	}
	@Then("the project leader is the employee <{int}>")
	public void the_project_leader_is_the_employee(Integer int1) {
		assertTrue(project.getProjectLeaderID() == int1);
	}

	@Given("employee <{int}> is the user")
	public void employee_is_the_user(Integer int1) {
		companyApp.setUser(int1);
	    assertTrue(companyApp.getUser() == int1);
	}

	@Given("a project {string} exists with employee <{int}> as project leader")
	public void a_project_exists_with_employee_as_project_leader(String string, Integer int1) {
		the_employee_is_assigned_as_project_leader_of_the_project(int1, string);
		the_project_leader_is_the_employee(int1);
	}

	@When("the project leader <{int}> unassigns the employee <{int}> from the activity with id <{int}> in {string}")
	public void the_project_leader_unassigns_the_employee_from_the_activity_with_id_in(Integer int1, Integer int2, Integer int3, String string) {
		companyApp.unassignEmployee(int2,int3,string);
	}

	@Then("the employee with id <{int}> is no longer assigned to activity with id <{int}> in {string}")
	public void the_employee_with_id_is_no_longer_assigned_to_activity_with_id_in(Integer int1, Integer int2, String string) {
		assertFalse(companyApp.getProject(string).getActivityWithID(int2).containsEmployeeWithID(int1));
	}

	@When("the employee <{int}> registers <{int}> half hours worked on activity <{int}> in project {string} for the day")
	public void the_employee_registers_half_hours_worked_for_the_day(Integer employee, Integer halfHours, Integer activity, String project) throws Exception {
		companyApp.registerDaysWork(employee, halfHours, activity, project);
	}

	@Then("employee <{int}>'s worked hours is updated to <{int}> half hours")
	public void employee_s_worked_hours_is_updated_to_half_hours(Integer int1, Integer int2) {
		assertTrue(companyApp.getEmployee(int1).getDaysWorkInHalfHours()==int2);
	}

	@Then("activity <{int}> in project {string} worked hours is updated to <{int}> half hours")
	public void activity_in_project_worked_hours_is_updated_to_half_hours(Integer int1, String string, Integer int2) {
		assertTrue(companyApp.getProject(string).getActivityWithID(int1).getWorkedHalfHours() == int2);
	}

}

