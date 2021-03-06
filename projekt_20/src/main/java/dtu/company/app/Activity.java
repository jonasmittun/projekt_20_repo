package dtu.company.app;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Activity {
    private String activityName;
    private ArrayList<Integer> assignedEmployees; //Remember to keep separate from employeeList in project class
    private int id;
    private int workedHalfHours;
    private Project project;
    private CompanyApp companyApp;
    private int projectLeaderID;
    
    public Activity(int id) {
        this.id = id;
        this.assignedEmployees = new ArrayList<Integer>();
        this.workedHalfHours = 0;
        this.projectLeaderID = 0;
    }
    
    public Activity(String activityName, int id){
        this.activityName = activityName;
        this.id = id;
        this.assignedEmployees = new ArrayList<Integer>();
        this.workedHalfHours = 0;
    }

    public String getActivityName(){
        return activityName;
    }

    public int getActivityID(){
        return id;
    }

    public void setActivityName(String newName, int user) throws Exception{ //Boran
        if (user == projectLeaderID) {
            this.activityName = newName;
        } else {
            throw new Exception("You must be project leader");
        }
    }


    public Boolean containsEmployeeWithID(int id){ //Jonas
        if (assignedEmployees.contains(id)){
            return true;
        } else {
            return false;
        }
    }

    public void assignEmployee(int id, int activities, int user) throws Exception{ //Boran
        //Precondition
        //assert user == projectLeaderID && !assignedEmployees.contains(id) && activities < 20: "Precondition";

        if (user != projectLeaderID) {
            throw new Exception("You must be project leader");
        }

        if (!assignedEmployees.contains(id)) {
            if (activities < 20) {
                this.assignedEmployees.add(id);
            } else if (activities >= 20) {
                throw new Exception("Employee is working too much");
            }
        } else {
            throw new Exception("Employee is already assigned");
        }
        //Post-condition
        //assert containsEmployeeWithID(id) : "Post-condition";
    }

	public ArrayList<Integer> getEmployees(){
		return assignedEmployees;
	}

    public void unassignEmployee(Integer int2) {
        assignedEmployees.remove(assignedEmployees.indexOf(int2));
    }
    
    public void inviteEmployee(Employee inviter, Employee invitee) throws Exception { //Jonas
    	//Precondition
        //assert inviter.getId() == currentUserID  && assignedEmployees.contains(inviter.getId()) && !assignedEmployees.contains(invitee.getId());
    	
    	if(containsEmployeeWithID(inviter.getId())) {
    		if(!(containsEmployeeWithID(invitee.getId()))) {
    			assignEmployee(invitee.getId(),invitee.getNumberOfActivities(), projectLeaderID);
    		} else {
    			throw new Exception("Employee being invited is already assigned to activity!");
    		}
    	} else {
    		throw new Exception("Employee trying to invite is not assigned to activity!");
    	}
    	
    	//Post-condition
        //assert containsEmployeeWithID(invitee.getId()) : "Post-condition";
    }

    public void addHalfHoursWorked(Integer halfHours) {
        this.workedHalfHours = workedHalfHours + halfHours;
    }

    public int getWorkedHalfHours(){
        return workedHalfHours;
    }

    public void setProjectLeaderID(int projectLeaderID) {
        this.projectLeaderID = projectLeaderID;
    }

    public int removeHalfHoursWorked(int halfHours) { //Roi
        if ((workedHalfHours - halfHours) > 0) {
            this.workedHalfHours = workedHalfHours - halfHours;
        } else {
            halfHours = workedHalfHours;
            double hours = workedHalfHours/2;
            this.workedHalfHours = 0;
        }
        return halfHours;
    }

    public int getProjectLeaderID() {
        return projectLeaderID;
    }
}
