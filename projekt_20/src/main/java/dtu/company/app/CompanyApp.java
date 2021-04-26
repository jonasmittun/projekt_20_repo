package dtu.company.app;

import java.util.ArrayList;

public class CompanyApp {
    private ArrayList<Employee> employeeList;
    private ArrayList<Project> projectList;
    private int user;

    public CompanyApp() {
    	employeeList = new ArrayList<Employee>();
        Employee employee;
        for (int i = 1; i < 51; i++){
            employee = new Employee(i);
            employeeList.add(employee);
        }
        
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
    	getProject(projectName).getActivityWithID(activityID).assignEmployee(employeeID,employeeList.get(employeeID).getActivities());
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

	public void setUser(Integer int1) {
		this.user = int1;
	}


	public void unassignEmployee(Integer int2, Integer int3, String string) {
    	getProject(string).getActivityWithID(int3).unassignEmployee(int2);
    	employeeList.get(int2).removeActivity();
	}
}
