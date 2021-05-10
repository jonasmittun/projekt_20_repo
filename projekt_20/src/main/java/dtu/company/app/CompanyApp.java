package dtu.company.app;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompanyApp {
    private ArrayList<Employee> employeeList;
    private ArrayList<Project> projectList;
    private int user;
    private LocalDate date;

    public CompanyApp() {
    	employeeList = new ArrayList<>();
        Employee employee;
        for (int i = 1; i < 51; i++){
            employee = new Employee(i);
            employeeList.add(employee);
        }
        this.date = LocalDate.now();
        this.projectList = new ArrayList<>();
    }

	public boolean containsProjectWithName(String string) {
		for (int i = 0; i < projectList.size(); i++) {
			if (projectList.get(i).getProjectName().equals(string)) {
				return true;
			}
		}
		return false;
	}


	public void addProject(Project project) throws Exception{
		if (containsProjectWithName(project.getProjectName())) {
			throw new Exception("Project already exists");
		} else {
			projectList.add(project);
		}
	}

	public boolean containsEmployeeWithId(int id) {
		for (Employee employee : employeeList) {
			if (employee.getId() == id) {
				return true;
			}
		}
		return false;
	}

	public Project getProject(String string){
		for (int i = 0; i < projectList.size(); i++) {
			if (projectList.get(i).getProjectName().equals(string)) {
				return projectList.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Project> getProjects(){
		return projectList;
	}

	public Employee getEmployee(int id){
    	for (Employee employee : employeeList) {
    		if (employee.getId() == id) {
				return employee;
			}
		}
    	return null;
	}

	public Employee newEmployee(){
    	int id = employeeList.size() + 1;
    	Employee newEmployee = new Employee(id);
    	return newEmployee;
	}

	public void addNewEmployee(Employee newEmployee){
    	employeeList.add(newEmployee);
	}

	public void assignEmployee(Integer employeeID, Integer activityID, String projectName) throws Exception {
		int projectLeaderID = getProject(projectName).getProjectLeaderID();
    	assertUserIsProjectLeader(projectLeaderID);
		int noOfActivities = getEmployee(employeeID).getNumberOfActivities();
    	getProject(projectName).getActivityWithID(activityID).assignEmployee(employeeID,noOfActivities,projectLeaderID);
    	getProject(projectName).addEmployee(getEmployee(employeeID));
		employeeList.get(employeeID).addActivity();
    }

	public ArrayList<Activity> getActivities() {
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		projectList.forEach((n) -> activityList.addAll(n.getActivities()));
		return activityList;
	}

	public ArrayList<Employee> getEmployees(){
		return this.employeeList;
	}

	public Integer getUser() {
		return this.user;
	}

	public boolean assertUserIsProjectLeader(int projectLeaderID) throws Exception {
		if (user == projectLeaderID) {
			return true;
		} else {
			throw new Exception("You must be project leader");
		}
	}

	public void setUser(Integer int1) throws Exception{
    	if (containsEmployeeWithId(int1)) {
			this.user = int1;
		} else {
    		throw new Exception("ID does not not match any employee");
		}
	}


	public void unassignEmployee(Integer int2, Integer int3, String string) {
    	getProject(string).getActivityWithID(int3).unassignEmployee(int2);
    	employeeList.get(int2).removeActivity();
	}
	
	/*public void setProjectName(String projectName, String newProjectName, int ID) throws Exception{
		getProject(projectName).setProjectName(newProjectName, user);
	}*/

	/*public void setActivityName(String projectName, int activityID, int userID, String newActivityName) throws Exception{
    	if(getProject(projectName).getProjectLeaderID()==userID){
    		getProject(projectName).getActivityWithID(activityID).setActivityName(newActivityName, getProject(projectName).getProjectLeaderID());
		}else {
    		throw new Exception("You must be project leader");
		}
	}*/

	public void registerDaysWork(Integer employee, Integer halfHours, Integer activity, String project) throws Exception {
		getEmployee(employee).addDaysWorkInHalfHours(halfHours);
		getProject(project).getActivityWithID(activity).addHalfHoursWorked(halfHours);
    }

    public void removeWeeksWork(int employee, int halfHours, int activityID, String projectName) throws Exception {
		int newHalfHours = getProject(projectName).getActivityWithID(activityID).removeHalfHoursWorked(halfHours);
    	getEmployee(employee).removeWeeksWorkInHalfHours(newHalfHours);

	}

    public ArrayList<String> getUserActivities(int ID){
    	ArrayList<String> list = new ArrayList<String>();
    	for(int i = 0; i < projectList.size(); i++) {
			Project proj = projectList.get(i);
			for(int j = 1; j <= proj.getActivities().size(); j++){
				Activity act = proj.getActivityWithID(j);
				if (act.containsEmployeeWithID(ID)){
					String s = proj.getProjectName() + ":" + act.getActivityName() + ":" + act.getActivityID();
					list.add(s);
				}
			}
		}
    	return list;
	}

	/*public ArrayList<Project> getEmployeeProjects(int ID){
		ArrayList<Project> list = new ArrayList<Project>();
		for(int i = 0; i < projectList.size(); i++){
			//Project project = projectList.get(i);
			for(int j = 1; j <= projectList.get(i).getEmployees().size(); j++){
				if(projectList.get(i).getEmployees().get(i).getId() == ID){
					list.add(projectList.get(i));
				}
			}
		}
		return list;
	}*/

	public ArrayList<String> getLeaderProjects(int ID){
    	ArrayList<String> list = new ArrayList<String>();
    	Project project;
    	for(int i = 0; i < projectList.size(); i++){
    		project = projectList.get(i);
    		if (project.getProjectLeaderID() == ID){
    			list.add(project.getProjectName());
			}
		}
    	return list;
	}

	/*public ArrayList<Project> getProjectLeaderProjects(int ID){
		ArrayList<Project> list = new ArrayList<Project>();
		for(int i = 0; i < projectList.size(); i++){
			for(int j = 1; j <= projectList.get(i).getEmployees().size(); j++){
				if(projectList.get(i).getProjectLeaderID() == ID){
					list.add(projectList.get(i));
				}
			}
		}
    	return list;
	}*/

	/*public Activity getActivity(String projectName, Integer int1) throws Exception {
		if (getProject(projectName).getActivityWithID(int1).getActivityID() == int1) {
			return getProject(projectName).getActivityWithID(int1);
		} else {
			throw new Exception("No such activity found");
		}
	}*/

    //Constructor for sample company

	public void addActivity(Activity activity, String projectName) throws Exception{
		assertUserIsProjectLeader(getProject(projectName).getProjectLeaderID());
		int activityID = activity.getActivityID();
		if (getProject(projectName).containsActivityWithID(activityID)) {
			throw new Exception("Activity must have an original ID");
		} else {
			int projectLeaderID = getProject(projectName).getProjectLeaderID();
			activity.setProjectLeaderID(projectLeaderID);
			getProject(projectName).getActivities().add(activity);
		}
	}
}
