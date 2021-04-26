package dtu.company.app;

import java.util.ArrayList;

public class CompanyApp {
    private ArrayList<Employee> employeeList;
    private ArrayList<Project> projectList;

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

	public void assignEmployee(Integer employeeID, Integer activityID, String projectName){
    	getProject(projectName).getActivityWithID(activityID).assignEmployee(employeeID);
	}



}
