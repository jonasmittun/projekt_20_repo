package dtu.company.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HelperSteps {
	
	private Employee employee;
	//private CompanyApp companyApp;
	private CompanyApp companyApp;
	private ErrorMessageHolder errorMessage;
	private Activity activity;
	private Project project;
	
	public HelperSteps(ErrorMessageHolder errorMessage, CompanyHelper companyHelper) {
		this.companyApp = companyHelper.getCompanyHelper();
		this.errorMessage = errorMessage;
	}
	
	@Given("there exists an employee with id <{int}> which is project leader for project {string}")
	public void there_exists_a_project_leader_with_id(Integer int1, String string) {
		assertTrue(companyApp.containsEmployeeWithId(int1));
		companyApp.getProject(string).setProjectLeaderID(int1);
	}
	

	@Given("there exists an employee <{int}>")
	public void there_exists_an_employee(Integer int1) {
		assertTrue(companyApp.containsEmployeeWithId(int1));
	}
	
	@Given("there exists an employee <{int}> who is not assigned to the activity <{int}> in project {string}")
	public void there_exists_an_employee_who_is_not_assigned_to_the_project(Integer int1, Integer int2, String string) {
		assertTrue(companyApp.containsEmployeeWithId(int1));
		assertFalse(companyApp.getProject(string).getActivityWithID(int2).containsEmployeeWithID(int1));
	}
	
	@Given("there exists an employee <{int}> who is assigned to the activity <{int}> in project {string}")
	public void there_exists_an_employee_who_is_assigned_to_the_activity_in_project(Integer int1, Integer int2, String string) throws Exception {
		int idHolder = companyApp.getUser();
		companyApp.setUser(companyApp.getProject(string).getProjectLeaderID());		//Owerwrite current user to become project leader
		companyApp.assignEmployee(int1,int2,string);
		assertTrue(companyApp.containsEmployeeWithId(int1));
		assertTrue(companyApp.getProject(string).getActivityWithID(int2).containsEmployeeWithID(int1));
		companyApp.setUser(idHolder);	//set user back to current user
	}

	@Given("the employee <{int}> is currently working on <{int}> activities or more")
	public void the_employee_is_currently_working_on_activities_or_more(Integer int1, Integer int2) {
		assertFalse(companyApp.getEmployee(int1).getNumberOfActivities() < int2);
	}
	
	@Given("the employee with id <{int}> is assigned to the activity with id <{int}> in {string}")
	public void the_employee_with_id_is_assigned_to_the_activity_with_id_in(Integer int1, Integer int2, String string) throws Exception {
		assertTrue(companyApp.getProject(string).getActivityWithID(int2).containsEmployeeWithID(int1));
	}
	
	@Given("an employee “employee {int}” is the project leader for the project {string}")
	public void an_employee_employee_is_the_project_leader_for_the_project(Integer int1, String string) {
		assertTrue(companyApp.getProject(string).getProjectLeaderID()==int1);
	}

	@Given("an employee “employee {int}” is not the project leader for the project {string}")
	public void an_employee_employee_is_not_the_project_leader_for_the_project(Integer int1, String string) {
		assertTrue(companyApp.getProject(string).getProjectLeaderID()!=int1);
	}

	@Given("a project {string} exists in the system")
	public void a_project_exists_in_the_system(String string) {
		assertTrue(companyApp.getProject(string).getProjectName().equals(string));
	}

}
