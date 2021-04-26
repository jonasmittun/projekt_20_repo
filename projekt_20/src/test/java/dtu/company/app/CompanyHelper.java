package dtu.company.app;

import java.util.ArrayList;

public class CompanyHelper {
    private CompanyApp companyHelper;

    public CompanyHelper() throws Exception {
        this.companyHelper = new CompanyApp();
        for (int i = 1; i < 31; i++){
            Project project = new Project("project "+i);
            for (int j = 1; j < 31; j++){
                Activity activity = new Activity("activity "+j,j);
                project.addActivity(activity);
            }
            companyHelper.addProject(project);
        }
        companyHelper.getEmployee(3).setActivities(20);
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
