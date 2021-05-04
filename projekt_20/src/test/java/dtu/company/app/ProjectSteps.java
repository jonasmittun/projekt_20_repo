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

import java.time.LocalDate;

public class ProjectSteps {
	private CompanyApp companyApp;
	private Project project;
	private ErrorMessageHolder errorMessage;
	private ProjectHelper helper;
	
	//Constructor for dependency injection
	public ProjectSteps (CompanyApp companyApp, CompanyHelper companyHelper, ErrorMessageHolder errorMessage, ProjectHelper helper){
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
	    project.setProjectName(string);
        assertTrue(companyApp.containsProjectWithName(string));
    }

    @When("a project named {string} with starting week <{int}> and finish week <{int}> is created")
    public void a_project_named_with_starting_week_and_finish_week_is_created(String string, Integer int1, Integer int2) throws Exception {
        try {
            this.project = new Project(string, int1, int2);
        } catch (Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system contains a project named {string} with starting week <{int}> and finishing week <{int}>")
    public void the_system_contains_a_project_named_with_starting_week_and_finishing_week(String string, Integer int1, Integer int2) {
        assertTrue(companyApp.containsProjectWithName(string));
        assertTrue(companyApp.getProject(string).getStartWeek()==int1);
        assertTrue(companyApp.getProject(string).getEndWeek()==int2);
    }

    @When("the deadline for {string} is set to {int}-{int}-{int}")
    public void the_deadline_for_is_set_to(String string, Integer int1, Integer int2, Integer int3) throws Exception {
        try {
            project = companyApp.getProject(string);
            project.setDeadline(int1, int2, int3);
            //Dette virker ikke:
            //companyApp.getProjects(string).set(project);
            //Derfor:
            companyApp.updateProject(project);

        } catch (Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the deadline of project {string} is {int}-{int}-{int}")
    public void the_deadline_of_project_is(String string, Integer int1, Integer int2, Integer int3) throws Exception {
        LocalDate date = LocalDate.of(int1, int2, int3);
        System.out.println(companyApp.getProject(string).getDeadline().toString());
        assertTrue(companyApp.getProject(string).getDeadline().equals(date));
    }


    @When("the project {string} is edited with new name {string}")
    public void the_employee_is_edited_with_new_name(String string, String string2) throws Exception {
        try{
            project = companyApp.getProject(string);
            project.setProjectName(string2);
            companyApp.updateProject(project);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    
}
