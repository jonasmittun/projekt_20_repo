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

public class EmployeeSteps {
	
	private Employee employee;
	private CompanyApp companyApp;
	private ErrorMessageHolder errorMessage;
	
	public EmployeeSteps(CompanyApp companyApp, ErrorMessageHolder errorMessage) {
		this.companyApp = companyApp;
		this.errorMessage = errorMessage;
	}

}
