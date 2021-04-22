package dtu.company.app;


import dtu.company.app.CompanyApp;
import dtu.company.app.Employee;
import dtu.company.app.Project;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectSteps {
	private CompanyApp companyApp;
	private Project project;
	private ErrorMessageHolder errorMessage;
	
	//Contructor for dependency injection
	public ProjectSteps (CompanyApp companyApp, ErrorMessageHolder errorMessage){
		this.companyApp = companyApp;
		this.errorMessage = errorMessage;
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
    	} catch (OperationNotAllowedException e){
    		errorMessage.setErrorMessage(e.getMessage());
    	}
        
    }
    
    @Then("the system contains a project named {string}")
    public void the_system_contains_a_project_named(String string) {
        assertTrue(companyApp.containsProjectWithName(string));
    }
}
