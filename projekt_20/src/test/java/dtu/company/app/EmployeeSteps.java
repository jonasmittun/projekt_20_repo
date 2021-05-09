package dtu.company.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
	private ArrayList<Activity> activities;
	private ArrayList<String> list;

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
	public void employee_is_the_user(Integer int1) throws Exception {
		try {
			companyApp.setUser(int1);
		} catch (Exception e){
			System.out.print(e);
		}
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
		//assertTrue(halfHours < 10);
		try {
			companyApp.registerDaysWork(employee, halfHours, activity, project);
		} catch (Exception e){
			errorMessage.setErrorMessage(e.getMessage());
		}
	}


	@When("the employee <{int}> removes <{int}> half hours from the week registry and activity <{int}> in project {string}")
	public void the_employee_removes_half_hours_from_the_week_registry_and_activity_in_project(Integer int1, Integer int2, Integer int3, String string) throws Exception {
		try {
			companyApp.removeWeeksWork(int1,int2,int3,string);
		} catch (Exception e){
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("employee <{int}>'s worked hours is updated to <{int}> half hours")
	public void employee_s_worked_hours_is_updated_to_half_hours(Integer int1, Integer int2) {
		assertTrue(companyApp.getEmployee(int1).getWeeksWorkInHalfHours()==int2);
	}

	@Then("activity <{int}> in project {string} worked hours is updated to <{int}> half hours")
	public void activity_in_project_worked_hours_is_updated_to_half_hours(Integer int1, String string, Integer int2) {
		assertTrue(companyApp.getProject(string).getActivityWithID(int1).getWorkedHalfHours() == int2);
	}
	
	@Given("an employee with id <{int}> is registered to the system")
	public void an_employee_with_id_is_registered_to_the_system(Integer int1) {
	    this.employee = new Employee(int1);
	    this.companyApp.addNewEmployee(this.employee);
	    assertTrue(this.companyApp.containsEmployeeWithId(int1));
	}

	@Given("an employee with id <{int}> is registered to an activity in the system with id <{int}>")
	public void an_employee_with_id_is_registered_to_an_activity_in_the_system_with_id(Integer int1, Integer int2) throws Exception {
	    assertTrue(this.employee.getId() == int1);
	    this.activity = new Activity(int2);
	    this.activity.assignEmployee(int1, 0, 0);
	    this.project = new Project("testName");
		this.companyApp.addProject(project);
		int userStore = companyApp.getUser();
		companyApp.setUser(companyApp.getProject("testname").getProjectLeaderID());
	    this.companyApp.addActivity(this.activity, "testName");
	    assertTrue(this.companyApp.getProject("testName").getActivityWithID(int2).containsEmployeeWithID(int1));
		companyApp.setUser(userStore);
	}

	@When("system gets activities for employee with id <{int}>")
	public void system_gets_activities_for_employee_with_id(Integer int1) {
		this.activities = this.companyApp.getActivities();
		ArrayList<Activity> newActivities = new ArrayList<Activity>();
		this.activities.forEach(n -> {
			if(n.containsEmployeeWithID(int1)) {
			newActivities.add(n);
			}}
		);
		this.activities = newActivities;
	}

	@Then("return list of activities that includes activity with id <{int}>")
	public void return_list_of_activities_that_includes_activity_with_id(Integer int2) {
	    assertTrue(this.activities.contains(this.activity) && this.activity.getActivityID() == int2);
	}

	@When("system gets list of projects that employee <{int}> is leading")
	public void system_gets_list_of_projects_that_employee_is_leading(Integer int1) {
		ArrayList<String> list;
		list = this.companyApp.getLeaderProjects(int1);
		list.forEach(n -> System.out.println(n));
	}

	@Then("return list of projects that includes project named {string}")
	public void return_list_of_projects_that_includes_project_named(String string) {
		assertTrue(this.companyApp.getProjects().contains(companyApp.getProject(string)));
	}

	@Given("the employee <{int}> is assigned to a number of activities in project {string}")
	public void the_employee_is_assigned_to_a_number_of_activities_in_project(Integer int1, String string) throws Exception {
		this.companyApp.assignEmployee(int1, 1,string);
		this.companyApp.assignEmployee(int1, 2,string);
		this.companyApp.assignEmployee(int1, 3,string);
		this.companyApp.assignEmployee(int1, 4,string);
		this.companyApp.assignEmployee(int1, 5,string);
	}

	@When("system gets list of activities that employee <{int}> is assigned to")
	public void system_gets_list_of_activities_that_employee_is_assigned_to(Integer int1) {
		this.list = companyApp.getUserActivities(int1);
	}

	@Then("return list of activities that include the number of activities")
	public void return_list_of_activities_that_include_the_number_activities() {
		for (int i = 0; i < 5; i++){
			assertTrue(Integer.parseInt(list.get(i).substring(list.get(i).lastIndexOf(':')+1,list.get(i).length())) == i + 1);
		}
	}
}

