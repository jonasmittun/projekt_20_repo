package dtu.company.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Project {
    private String projectName;
    private int projectLeaderID;
    private ArrayList<Activity> activityList;
    private ArrayList<Employee> employeeList;
    private int startWeek;
    private int endWeek;
    private LocalDate startDate;
    private LocalDate endDate;
    
    public Project(String projectName){
        this.projectName = projectName;
        this.activityList = new ArrayList<Activity>();
        this.employeeList = new ArrayList<Employee>();
    }

    public Project(String projectName, int startWeek, int endWeek) throws Exception {
        this.projectName = projectName;
        this.activityList = new ArrayList<Activity>();
        this.employeeList = new ArrayList<Employee>();
        if (startWeek < endWeek) {
            this.startWeek = startWeek;
            this.endWeek = endWeek;
        } else {
            throw new Exception("Starting week must be before finishing week");
        }
    }

    //Adders
    public void addActivity(Activity activity){
        activityList.add(activity);
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    //Setters
    public void setProjectLeaderID(int id){
        this.projectLeaderID = id;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public void setStartWeek(int week){
        this.startWeek = week;
    }

    public void setEndWeek(int week){
        this.endWeek = week;
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

    public Activity getActivityWithName(String activityName){
        for (int i = 0; i < activityList.size(); i++){
            if(activityList.get(i).getActivityName() == activityName){
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

    public int getStartWeek(){
        return startWeek;
    }

    public int getEndWeek(){
        return endWeek;
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

    public Boolean containsActivityWithName(String activityName){
        for (int i = 0; i < activityList.size(); i++){
            if (activityList.get(i).getActivityName() == activityName){
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

    public void updateActivity(Activity activity) throws Exception{
        if (containsActivityWithID(activity.getActivityID())) {
            for (int i = 0; i < activityList.size(); i++) {
                if (activityList.get(i).getActivityID() == activity.getActivityID()) {
                    activityList.set(i, activity);
                    return;
                }
            }
        } else {
            throw new Exception("Activity does not already exist");
        }
    }
}
