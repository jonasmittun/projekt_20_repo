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

public class ProjectSteps {
	private CompanyApp companyApp;
	private Project project;
	private ErrorMessageHolder errorMessage;
	private ProjectHelper helper;
	private Employee employee;
	
	//Constructor for dependency injection
	public ProjectSteps (CompanyApp companyApp, ErrorMessageHolder errorMessage, Project project, Employee employee){
		this.companyApp = companyApp;
		this.errorMessage = errorMessage;
		this.project = project;
		this.employee = employee;
		this.helper = new ProjectHelper(companyApp);
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

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String errorMessage) {
    	assertEquals(errorMessage, this.errorMessage.getErrorMessage());
    }

	@Given("a project exists in the system")
	public void a_project_exists_in_the_system() throws Exception {
		project = helper.getExampleProject();
		the_project_is_added_to_the_system();
		assertTrue(companyApp.containsProjectWithName(project.getProjectName()));
	}
    
}
