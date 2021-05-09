package dtu.company.app;

public class CompanyHelper {
    private CompanyApp companyHelper;

    public CompanyHelper() throws Exception {
        this.companyHelper = new CompanyApp();
        Employee adminEmployee = new Employee(0);
        companyHelper.addNewEmployee(adminEmployee);

        for (int i = 1; i < 31; i++){
            //Projects are named "project <int>"
            Project project = new Project("project "+i);
            companyHelper.addProject(project);
        }
        //Employee <1> becomes project leader of project "project 1"
        companyHelper.getProject("project 1").setProjectLeaderID(1);

        //Employee <20> becomes project leader of project "project 20"
        companyHelper.getProject("project 20").setProjectLeaderID(20);

        String projectName;
        int userStore = companyHelper.getUser();
        for (int i = 1; i < 31; i++) {
            for (int j = 1; j < 31; j++){
                //Activities are named "activity <int>" and get their index as id
                Activity activity = new Activity("activity "+j,j);
                projectName = "project " + i;

                if (i == 1) {
                    companyHelper.setUser(1);
                } else if (i == 20) {
                    companyHelper.setUser(20);
                } else {
                    companyHelper.setUser(0);
                }
                companyHelper.addActivity(activity, projectName);
            }
        }
        companyHelper.setUser(userStore);

        //Employee <2> has below 20 activities
        companyHelper.getEmployee(2).setNumberOfActivities(0);

        //Employee <3> has between 10 and 20 activites
        companyHelper.getEmployee(3).setNumberOfActivities(10);

        //Employee <4> has 20 or above activities
        companyHelper.getEmployee(4).setNumberOfActivities(20);
    }
    /*//CompanyApp build with 50 employees, 30 projects with 30 activities
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
        companyHelper.getEmployee(2).setNumberOfActivities(0);

        //Employee <3> has between 10 and 20 activities
        companyHelper.getEmployee(3).setNumberOfActivities(10);

        //Employee <4> has 20 or above activities
        companyHelper.getEmployee(4).setNumberOfActivities(20);
    }*/

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
