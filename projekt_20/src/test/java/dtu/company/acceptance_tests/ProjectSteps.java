package dtu.company.acceptance_tests;

import dtu.company.app.Activity;
import dtu.company.app.CompanyApp;
import dtu.company.app.Employee;
import dtu.company.app.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectSteps {
    private CompanyApp companyApp;
    private Employee employee;
    private Project project;
    private Activity activity;

    private ProjectSteps(CompanyApp companyApp){
        this.companyApp = companyApp;
    }

    @Given("an employee wants to create a project named {string}.")
    public void an_employee_wants_to_create_a_project_named(String string) {
        project = new Project(string);
        throw new io.cucumber.java.PendingException();
    }
}
