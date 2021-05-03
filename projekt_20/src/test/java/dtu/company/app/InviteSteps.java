package dtu.company.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InviteSteps {

	private Employee inviter;
	private Employee invitee;
	private CompanyApp companyApp;
	private ErrorMessageHolder errorMessage;
	private Activity activity;
	private Project project;
	
	public InviteSteps(ErrorMessageHolder errorMessage, CompanyApp companyApp) {
		this.companyApp = companyApp;
		this.errorMessage = errorMessage;
	}
	
	@Given("employee <{int}> exists within activity <{int}>")
	public void employee_exists_within_activity(Integer int1, Integer int2) throws Exception {
	    this.companyApp = new CompanyApp();
	    this.project = new Project("testProject");
	    this.companyApp.addProject(this.project);
	    this.activity = new Activity(int2);
	    this.project.addActivity(this.activity);
	    this.inviter = new Employee(int1);
	    this.companyApp.addNewEmployee(this.inviter);
	    this.project.addEmployee(this.inviter);
	    this.activity.assignEmployee(this.inviter.getId(), 0);
	}

	@Given("employee <{int}> exists within companyApp")
	public void employee_exists_within_company_app(Integer int1) {
	    this.invitee = new Employee(int1);
	    this.companyApp.addNewEmployee(invitee);

	}

	@When("employee <{int}> invites employee <{int}> to assist with activity <{int}>")
	public void employee_invites_employee_to_assist_with_activity(Integer int1, Integer int2, Integer int3) throws Exception {
//	    Der burde måske være noget her men det virker vel uden 
	}

	@Then("assign employee <{int}> to activity <{int}>")
	public void assign_employee_to_activity(Integer int1, Integer int2) throws Exception {
		this.activity.inviteEmployee(this.inviter, this.invitee);
	    assertTrue(this.activity.containsEmployeeWithID(this.invitee.getId()));
	}

	@Then("do not assign employee <{int}> to activity <{int}> parent project")
	public void do_not_assign_employee_to_activity_parent_project(Integer int1, Integer int2) {
	    assertTrue(!this.project.containsEmployeeWithID(this.invitee.getId()));
	}
	
	@When("employee <{int}> is invited to activity <{int}> where it already exists")
	public void employee_is_invited_to_activity_where_it_already_exists(Integer int1, Integer int2) throws Exception{
		this.inviter = new Employee(int1 + 1);
		this.invitee = new Employee(int1);
		this.activity = new Activity(int2);
		this.activity.assignEmployee(this.inviter.getId(), 1);
		this.activity.assignEmployee(this.invitee.getId(), 1);
		try {
			this.activity.inviteEmployee(this.inviter, this.invitee);
		} catch (Exception e) {
			this.errorMessage.setErrorMessage(e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	@Then("give the exception {string}")
	public void give_the_exception(String string) {
		System.out.println(this.errorMessage.getErrorMessage());
	    assertTrue(this.errorMessage.getErrorMessage().toString().equals("Employee being invited is already assigned to activity!"));
	}
}
