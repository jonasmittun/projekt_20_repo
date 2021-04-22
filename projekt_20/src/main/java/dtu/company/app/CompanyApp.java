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
        this.employeeList = employeeList;
        
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
}
