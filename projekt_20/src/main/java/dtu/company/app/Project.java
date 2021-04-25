package dtu.company.app;

import java.util.ArrayList;

public class Project {
    private String projectName;
    private int projectLeaderID;
    private ArrayList<Activity> activityList;
    
    public Project(String projectName){
        this.projectName = projectName;
        this.activityList = new ArrayList<Activity>();
    }

    public void setProjectLeaderID(int id){
        this.projectLeaderID = id;
    }

    public int getProjectLeaderID(){
        return projectLeaderID;
    }

	public String getProjectName() {
		return projectName;
	}

	public Boolean containsActivityWithID(int id){
        for (int i = 0; i < activityList.size(); i++) {
            if (activityList.get(i).getActivityID()==id) {
                return true;
            }
        }
        return false;
    }

    public void addActivity(Activity activity){
        activityList.add(activity);
    }

    public Activity getActivityWithID(int id){
        for (int i = 0; i < activityList.size(); i++){
            if (activityList.get(i).getActivityID()==id){
                return activityList.get(i);
            }
        }
        return null;
    }
}
