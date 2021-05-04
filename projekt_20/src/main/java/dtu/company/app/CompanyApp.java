package dtu.company.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompanyApp {
    private ArrayList<Employee> employeeList;
    private ArrayList<Project> projectList;
    private int user;
    private LocalDate date;

    public CompanyApp() {
    	employeeList = new ArrayList<Employee>();
        Employee employee;
        for (int i = 1; i < 51; i++){
            employee = new Employee(i);
            employeeList.add(employee);
        }
        this.date = LocalDate.now();
        this.projectList = new ArrayList<Project>();
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
    	return employeeList.get(id);
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
    	getProject(projectName).getActivityWithID(activityID).assignEmployee(employeeID,employeeList.get(employeeID).getNumberOfActivities());
		employeeList.get(employeeID).addActivity();
    }

	public ArrayList<Activity> getActivities() {
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		projectList.forEach((n) -> activityList.addAll(n.getActivities()));
		return activityList;
	}

	public int getIdForNewActivity(String projectName){
		return getProject(projectName).getActivities().size()+1;
	}

	
	public ArrayList<Employee> getEmployees(){
		return this.employeeList;
	}

	public Integer getUser() {
		return this.user;
	}

	public boolean assertUserIsProjectLeader(int projectLeaderId) throws Exception {
		if (user == projectLeaderId) {
			return true;
		} else {
			throw new Exception("You must be project leader");
		}
	}

	public void setUser(Integer int1) {
		this.user = int1;
	}


	public void unassignEmployee(Integer int2, Integer int3, String string) {
    	getProject(string).getActivityWithID(int3).unassignEmployee(int2);
    	employeeList.get(int2).removeActivity();
	}
	public void setProjectName(String projectName, String newProjectName, int ID) throws Exception{
    	if(getProject(projectName).getProjectLeaderID()==ID){
			getProject(projectName).setProjectName(newProjectName);
		}else {
			throw new Exception("You must be project leader");
		}
	}



	public void setActivityName(String projectName, int activityID, int userID, String newActivityName) throws Exception{
    	if(getProject(projectName).getProjectLeaderID()==userID){
    		getProject(projectName).getActivityWithID(activityID).setActivityName(newActivityName);
		}else {
    		throw new Exception("You must be project leader");
		}
	}

	public void registerDaysWork(Integer employee, Integer halfHours, Integer activity, String project) throws Exception {
		getEmployee(employee).addDaysWorkInHalfHours(halfHours);
		getProject(project).getActivityWithID(activity).addHalfHoursWorked(halfHours);
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

    //Updates an existing project with an edited one
    public void updateProject(Project update) throws Exception {
    	assertUserIsProjectLeader(update.getProjectLeaderID());
    	if (containsProjectWithName(update.getProjectName())) {
			for (int i = 0; i < projectList.size(); i++) {
				if (projectList.get(i).getProjectName().equals(update.getProjectName())) {
					projectList.set(i, update);
					return;
				}
			}
		} else {
    		throw new Exception("Project does not already exist");
		}
    }

	public Activity getActivity(String projectName, Integer int1) throws Exception {
		if (getProject(projectName).getActivityWithID(int1).getActivityID() == int1) {
			return getProject(projectName).getActivityWithID(int1);
		} else {
			throw new Exception("No such activity found");
		}
	}

	public void updateActivity(String projectName, Activity activity) throws Exception {
		assertUserIsProjectLeader(getProject(projectName).getProjectLeaderID());
		int activityId = activity.getActivityID();
		if (containsProjectWithName(projectName)) {
			for (int i = 0; i < projectList.size(); i++) {
				Project project = projectList.get(i);
				if (project.getProjectName().equals(projectName) && project.containsActivityWithID(activityId)) {
					project.updateActivity(activity);
					return;
				}
			}
    	} else {
    		throw new Exception("Project could not be updated");
		}
	}

    //Constructor for sample company

	public void setSampleCompany() throws Exception {
		int numberActivities = 0;
		for (int j = 0; j < 10; j++){
			//Projects are named "project <int>"
			Project project = new Project("project "+(j+1));
			numberActivities = numberActivities + 5;
			for(int k = 1; k <= numberActivities; k++) {
				//Activities are named "activity <int>" and get their index as id
				Activity activity = new Activity("activity " + k, k);
				project.addActivity(activity);
				if (numberActivities == 25) {
					numberActivities = 0;
					break;
				}
			}
			addProject(project);
		}

		employeeList.removeAll(employeeList);
		Employee employee;
		for (int i = 1; i < 11; i++){
			employee = new Employee(i);
			employeeList.add(employee);
		}

		int place = 1;
		int holder = 1;
		for (int i = 4; i >= 1; i--) {

			for (int j = 4; j >= holder; j--) {

				for (int k = place; k < place + 5; k++){
					assignEmployee(i,k,"project " + j);
					//System.out.println("Employee:" + i + " Project:" + j + " Activity" + k);
				}
			}
			holder++;
			place = place + 5;
		}
	}
}
