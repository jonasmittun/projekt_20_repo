package dtu.company.app;

import java.util.List;

public class CompanyApp {
    private List<Employee> employeeList;
    private List<Project> projectList;

    public CompanyApp() {
        Employee employee;
        for (int i = 1; i < 51; i++){
            employee = new Employee(i);
            employeeList.add(employee);
        }
        this.employeeList = employeeList;
    }
}
