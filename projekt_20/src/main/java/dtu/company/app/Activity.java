package dtu.company.app;

import java.util.ArrayList;

public class Activity {
    private String activityName;
    private ArrayList<Integer> assignedEmployees; //Remember to keep separate from employeeList in project class
    private int id;
    
    public Activity(int id) {
        this.id = id;
        this.assignedEmployees = new ArrayList<Integer>();
    }
    
    public Activity(String activityName, int id){
        this.activityName = activityName;
        this.id = id;
        this.assignedEmployees = new ArrayList<Integer>();
    }

    public String getActivityName(){
    	//Det her kommer til at være brokken hvis der ikke er et navn
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

    public void assignEmployee(int id, int activities) throws Exception{
        if (!assignedEmployees.contains(id)) {
            if (activities < 10) {
                this.assignedEmployees.add(id);
            } else if (activities < 20 && activities >= 10) {
                this.assignedEmployees.add(id);
                throw new Exception("Warning: Employee is working a lot");
            } else if (activities >= 20) {
                throw new Exception("Employee is working too much");
            }
        } else {
            throw new Exception("Employee is already assigned");
        }
    }

	public int getEmployeeWithId(int i) {
		return assignedEmployees.get(i);
	}
	
	public ArrayList<Integer> getEmployees(){
		return assignedEmployees;
	}

    public void unassignEmployee(Integer int2) {
        assignedEmployees.remove(assignedEmployees.indexOf(int2));
    }
}
