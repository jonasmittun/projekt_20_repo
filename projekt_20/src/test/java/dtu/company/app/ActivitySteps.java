package dtu.company.app;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;

public class ActivitySteps {
    private CompanyApp companyApp;
    private ErrorMessageHolder errorMessage;

    public ActivitySteps(ErrorMessageHolder errorMessage, CompanyHelper companyHelper){
        this.companyApp = companyHelper.getCompanyHelper();
        this.errorMessage = errorMessage;
    }

    @Given("there exists an activity {string} with id <{int}> in project {string}")
	public void there_exists_an_activity_with_id_in_project(String string, Integer int1, String string2) {
		assertTrue(companyApp.getProject(string2).containsActivityWithID(int1));
		assertTrue(companyApp.getProject(string2).getActivityWithID(int1).getActivityName().equals(string));
	}
}
