package dtu.company.app;

import java.util.ArrayList;

public class CompanyHelper {
    private CompanyApp companyHelper;

    public CompanyHelper() throws Exception {
        this.companyHelper = new CompanyApp();
        for (int i = 1; i < 31; i++){
            Project project = new Project("Project"+i);
            for (int j = 1; j < 31; j++){
                Activity activity = new Activity("Activity"+j,j);
                project.addActivity(activity);
            }
            companyHelper.addProject(project);
        }
    }
}
