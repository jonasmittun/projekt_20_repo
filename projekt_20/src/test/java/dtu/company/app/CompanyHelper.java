package dtu.company.app;

import java.util.ArrayList;

public class CompanyHelper {
    private CompanyApp companyHelper;

    //CompanyApp build with 50 employees, 30 projects with 30 activities
    public CompanyHelper() throws Exception {
        this.companyHelper = new CompanyApp();
        for (int i = 1; i < 31; i++){
            //Projects are named "project <int>"
            Project project = new Project("project "+i);
            for (int j = 1; j < 31; j++){
                //Activities are named "activity <int>" and get their index as id
                Activity activity = new Activity("activity "+j,j);
                project.addActivity(activity);
            }
            companyHelper.addProject(project);
        }
        //Employee <1> becomes project leader of project "project 1"
        companyHelper.getProject("project 1").setProjectLeaderID(1);

        //Employee <20> becomes project leader of project "project 20"
        companyHelper.getProject("project 20").setProjectLeaderID(20);

        //Employee <2> has below 20 activities
        companyHelper.getEmployee(2).setActivities(0);

        //Employee <3> has between 10 and 20 activites
        companyHelper.getEmployee(3).setActivities(10);

        //Employee <4> has 20 or above activities
        companyHelper.getEmployee(4).setActivities(20);
    }

    public CompanyApp getCompanyHelper(){
        return companyHelper;
    }

    public Boolean containsProjectWithName(String string){
        if (companyHelper.containsProjectWithName(string)){
            return true;
        }
        return false;
    }
}
