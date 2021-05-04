package dtu.company.app;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivitySteps {
    private CompanyApp companyApp;
    private ErrorMessageHolder errorMessage;
    private Activity activity;

    public ActivitySteps(ErrorMessageHolder errorMessage, CompanyHelper companyHelper){
        this.companyApp = companyHelper.getCompanyHelper();
        this.errorMessage = errorMessage;
    }

    @When("an activity is created in project {string}")
    public void an_activity_is_created_with_an_id_number_in_project(String projectName) {
        this.activity = new Activity(companyApp.getIdForNewActivity(projectName));
    }

    @Then("the activity <{int}> is registered in the project {string}")
    public void the_activity_is_registered_in_the_project(Integer int1,String projectName) {
        assertTrue(companyApp.getProject(projectName).containsActivityWithID(int1));
    }

    @Given("there exists an activity {string} with id <{int}> in project {string}")
	public void there_exists_an_activity_with_id_in_project(String string, Integer int1, String string2) {
		assertTrue(companyApp.getProject(string2).containsActivityWithID(int1));
		assertTrue(companyApp.getProject(string2).getActivityWithID(int1).getActivityName().equals(string));
	}

    @When("the activity <{int}> is edited with new name {string} in project {string}")
    public void the_activity_is_edited_with_new_name_in_project(Integer int1, String newActivityName, String projectName) throws Exception {
        try {
            activity = companyApp.getActivity(projectName, int1);
            activity.setActivityName(newActivityName);
            companyApp.updateActivity(projectName, activity);
            //companyApp.setActivityName(projectName,int1,int2,newActivityName);
        }catch (Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity <{int}>'s name is {string} in the {string}")
    public void the_activity_s_name_is_in_the(Integer int1, String string, String string2) {
        assertTrue(companyApp.getProject(string2).getActivityWithID(int1).getActivityName().equals(string));
    }








}
