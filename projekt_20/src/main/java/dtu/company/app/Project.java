package dtu.company.app;

import java.util.ArrayList;

public class Project {
    private String projectName;
    private int projectLeaderID;
    private ArrayList<Activity> activityList;
    
    public Project(String projectName){
        this.projectName = projectName;
    }

    public int getProjectLeader(){
        return projectLeaderID;
    }

	public String getProjectName() {
		return projectName;
	}
}
