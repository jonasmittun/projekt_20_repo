package dtu.company.app;

import dtu.company.app.Activity;
import dtu.company.app.CompanyApp;
import dtu.company.app.Employee;
import dtu.company.app.Project;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SuppressWarnings("deprecation")
public class ProjectSteps {
    private CompanyApp companyApp;
    private Employee employee;
    private Project project;
    private Activity activity;

    private ProjectSteps(CompanyApp companyApp){
        this.companyApp = companyApp;
    }
    
    @Given("a project {string} does not exist in the system")
    public void a_project_does_not_exist_in_the_system(String string) {
    	assertFalse(companyApp.containsProjectWithName(string));
    	throw new io.cucumber.java.PendingException();
    }

    @Given("an employee wants to create a project named {string}.")
    public void an_employee_wants_to_create_a_project_named(String string) {
        project = new Project(string);
        throw new io.cucumber.java.PendingException();
    }
}
