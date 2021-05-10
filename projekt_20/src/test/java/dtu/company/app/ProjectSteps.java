package dtu.company.app;


import dtu.company.app.CompanyApp;
import dtu.company.app.Employee;
import dtu.company.app.Project;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

public class ProjectSteps {
	private CompanyApp companyApp;
	private Project project;
	private ErrorMessageHolder errorMessage;
	private ProjectHelper helper;
	
	//Constructor for dependency injection
	public ProjectSteps (CompanyHelper companyHelper, ErrorMessageHolder errorMessage, ProjectHelper helper){
		this.companyApp = companyHelper.getCompanyHelper();
		this.errorMessage = errorMessage;
		this.helper = helper;
	}
    
    @Given("a project {string} does not exist in the system")
    public void a_project_does_not_exist_in_the_system(String string) {
    	assertFalse(companyApp.containsProjectWithName(string));
    }

    @When("a project named {string} is created")
    public void create_project_named(String string) {
        this.project = new Project(string);
    }

    @When("a project named {string} is created with a startDate {int}\\/{int}\\/{int} and an endDate {int}\\/{int}\\/{int}")
    public void a_project_named_is_created_with_a_start_date_and_an_end_date(String string, Integer int1, Integer int2, Integer int3, Integer int4, Integer int5, Integer int6) throws Exception {
        LocalDate startDate = LocalDate.of(int3,int2,int1);
        LocalDate endDate = LocalDate.of(int6,int5,int4);
	    this.project = new Project(string,startDate,endDate);
    }


    @When("the project is added to the system")
    public void the_project_is_added_to_the_system() throws Exception{
    	try {
    		companyApp.addProject(project);
    	} catch (Exception e){
    		errorMessage.setErrorMessage(e.getMessage());
    	}
    }
    
    @Then("the system contains a project named {string}")
    public void the_system_contains_a_project_named(String string) {
        assertTrue(companyApp.containsProjectWithName(string));
    }

    @Given("the system contains a project {string}")
    public void the_system_contains_a_project(String string) throws Exception {
	    project = new Project(string);
        the_project_is_added_to_the_system();
        assertTrue(companyApp.containsProjectWithName(string));
    }
    
    @Given("a project exists in the system")
    public void a_project_exists_in_the_system() throws Exception {
        project = helper.getExampleProject();
        the_project_is_added_to_the_system();
        assertTrue(companyApp.containsProjectWithName(project.getProjectName()));
    }

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String errorMessage) {
    	assertEquals(errorMessage, this.errorMessage.getErrorMessage());
    }

    @Then("the project name is {string}")
    public void the_project_name_is(String string) {
        assertTrue(companyApp.containsProjectWithName(string));
    }

   /* @When("a project named {string} with starting week <{int}> and finish week <{int}> is created")
    public void a_project_named_with_starting_week_and_finish_week_is_created(String string, Integer int1, Integer int2) throws Exception {
        try {
            this.project = new Project(string, int1, int2);
        } catch (Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }*/


    @When("the deadline for {string} is set to {int}-{int}-{int}")
    public void the_deadline_for_is_set_to(String string, Integer int1, Integer int2, Integer int3) throws Exception {
        try {
            companyApp.getProject(string).setDeadline(int1, int2, int3);
        } catch (Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the deadline of project {string} is {int}-{int}-{int}")
    public void the_deadline_of_project_is(String string, Integer int1, Integer int2, Integer int3) throws Exception {
        LocalDate date = LocalDate.of(int1, int2, int3);
        assertTrue(companyApp.getProject(string).getDeadline().equals(date));
    }


    @When("the project {string} is edited with new name {string}")
    public void the_employee_is_edited_with_new_name(String string, String string2) throws Exception {
        try{
            companyApp.getProject(string).setProjectName(string2, companyApp.getUser());
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    
    @When("a project named {string} is searched for")
    public void a_project_named_is_searched_for(String string) {
        this.project = companyApp.getProject(string);
    }

    @Then("null is returned by system")
    public void null_is_returned_by_system() {
        assertNull(this.project);
    }
    
    @Given("a project {string} does exist in the system")
    public void a_project_does_exist_in_the_system(String string) throws Exception {
        this.project = new Project(string);
        this.companyApp.addProject(this.project);
        assertTrue(this.companyApp.containsProjectWithName(string));
    }

    @Given("a project named {string} does not exist in the system")
    public void a_project_named_does_not_exist_in_the_system(String string2) {
        assertFalse(this.companyApp.containsProjectWithName(string2));
    }

    @When("a project named {string} is renamed to {string}")
    public void a_project_named_is_renamed_to(String string, String string2) throws Exception {
    	this.project = this.companyApp.getProject(string);
        this.project.setProjectName(string2, 0);
    }

    @Then("the system does not contain a project named {string}")
    public void the_system_does_not_contain_a_project_named(String string) {
        assertFalse(this.companyApp.containsProjectWithName(string));
    }

    @When("the deadline for {string} is set to the current date")
    public void the_deadline_for_is_set_to_the_current_date(String string) {
        try {
            LocalDate date = LocalDate.now();
            companyApp.getProject(string).setDeadline(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        } catch (Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project {string} is expired")
    public void the_project_is_expired(String string) {
        assertTrue(companyApp.getProject(string).isExpired());
    }

    @Then("the project {string} is not expired")
    public void the_project_is_not_expired(String string) {
        assertFalse(companyApp.getProject(string).isExpired());
    }

}
