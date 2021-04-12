Feature: Assigning Employee
   Description: The project leader assigns an employee to an activity
   Actor: Project leader

   #Assigning an employee
   Scenario: Project leader assigns an employee to an activity
       Given there is an activity "Activity" assigned to a project "Project"
       When the project leader selects an employee "Employee"
       And the employee "Employee" is currently working on less than <20> projects
       Then the employee "Employee" is assigned to the "Activity"

   #Alternative scenario
   Scenario: The employee is currently working on <20> activities
       Given there is an activity "Activity" assigned to a project "Project"
       When the project leader selects an employee "Employee"
       And the employee "Employee" is currently working on <20> projects
       Then the system provides an error message that the employee is not available for a new activity

   #Unassigning an employee
   Scenario: Project leader unassigns an employee
       Given an employee "Employee" is assigned to a project "Activity"
       When the project leader selects the employee "Employee"
       And the project leader confirms to unassign "Employee" from "Activity"Then the employee "Employee" is no longer assigned to the activity "Activity"

   #Employee is already assigned
   Scenario: Fail scenario when assigning an employee
       Given there is an activity "Activity" assigned to a project "Project"
       When the project leader selects an employee "Employee"
       And the employee "Employee" is already assigned to the selected activity "Activity"
       Then the system provides an error message that the employee "Employee" is already assigned to the selected activity