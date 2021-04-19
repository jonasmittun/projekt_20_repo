package dtu.company.app;

import java.util.ArrayList;

public class Project {
    private String projectName;
    private int projectLeaderID;
    private ArrayList<Activity> activityList;

    public Project(String projectName, int projectLeader){
        this.projectName = projectName;
        this.projectLeaderID = projectLeader;
    }

    public String getProject(){
        return projectName;
    }

    public int getProjectLeader(){
        return projectLeaderID;
    }
}
