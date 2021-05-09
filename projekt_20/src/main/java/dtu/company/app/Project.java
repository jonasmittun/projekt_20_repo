package dtu.company.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Project {
    private String projectName;
    private int projectLeaderID;
    private ArrayList<Activity> activityList;
    private ArrayList<Employee> employeeList;
    private LocalDate startDate;
    private LocalDate endDate;
    
    public Project(String projectName){
        this.projectLeaderID = 0;
        this.projectName = projectName;
        this.activityList = new ArrayList<Activity>();
        this.employeeList = new ArrayList<Employee>();
    }

    public Project(String projectName, LocalDate startDate, LocalDate endDate) throws Exception{
        this.projectName = projectName;
        this.activityList = new ArrayList<Activity>();
        this.employeeList = new ArrayList<Employee>();
        if(startDate.isBefore(endDate)){
            this.startDate = startDate;
            this.endDate = endDate;
        }else {
            throw new Exception("Deadline should not be before the starting date!");
        }
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    //Setters
    public void setProjectLeaderID(int id){
        this.projectLeaderID = id;
    }

    public void setProjectName(String projectName, int user) throws Exception{
        assertUserIsProjectLeader(user);
        this.projectName = projectName;
    }

    //Getters
    public int getProjectLeaderID(){
        return projectLeaderID;
    }

	public String getProjectName() {
		return projectName;
	}

    public Activity getActivityWithID(int id){
        for (int i = 0; i < activityList.size(); i++){
            if (activityList.get(i).getActivityID()==id){
                return activityList.get(i);
            }
        }
        return null;
    }

    public ArrayList<Activity> getActivities() {
        return activityList;
    }

    public ArrayList<Employee> getEmployees(){
        return employeeList;
    }

    //Contains
	public Boolean containsActivityWithID(int id){
        for (int i = 0; i < activityList.size(); i++) {
            if (activityList.get(i).getActivityID()==id) {
                return true;
            }
        }
        return false;
    }

	public boolean containsEmployeeWithID(Integer id) {
		for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                return true;
            }
        }
        return false;
	}

    public void setDeadline(Integer int1, Integer int2, Integer int3) throws Exception {
        LocalDate newDeadline = LocalDate.of(int1, int2, int3);
        if (LocalDate.now().compareTo(newDeadline) < 0) {
            this.endDate = LocalDate.of(int1, int2, int3);
            System.out.println("test");
        } else {
            throw new Exception("Deadline must be in the future");
        }
    }

    public LocalDate getDeadline() throws Exception {
        if (endDate != null) {
            return endDate;
        } else {
            throw new Exception("Deadline is not defined");
        }
    }

    public int getIdForNewActivity(String projectName){
        return activityList.size()+1;
    }

    public boolean assertUserIsProjectLeader(int user) throws Exception {
        if (user == projectLeaderID) {
            return true;
        } else {
            throw new Exception("You must be project leader");
        }
    }
}
