package dtu.company.app;

import java.util.ArrayList;

public class Activity {
    private String activityName;
    private ArrayList<Integer> assignedEmployees;
    private int id;

    public Activity(String activityName, int id){
        this.activityName = activityName;
        this.id = id;
        this.assignedEmployees = new ArrayList<Integer>();
    }

    public String getActivityName(){
        return activityName;
    }

    public int getActivityID(){
        return id;
    }

    public Boolean containsEmployeeWithID(int id){
        if (assignedEmployees.contains(id)){
            return true;
        } else {
            return false;
        }
    }

    public void assignEmployee(int id){
        if (!assignedEmployees.contains(id)){
            this.assignedEmployees.add(id);
        } else {
            return;
        }
    }
}
