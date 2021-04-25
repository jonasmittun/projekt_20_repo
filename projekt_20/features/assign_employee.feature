Feature: Assigning Employee
   Description: The project leader assigns an employee to an activity
   Actor: Project leader

   #Assigning an employee
   Scenario: Project leader assigns an employee to an activity
       Given there is an activity called "Activity" with id <1>
       And  the project leader <1> assigns the activity <1> to a project "Project"
       When the project leader employee <1> selects an employee <2>
       And the employee <2> is currently working on less than <20> projects
       And the employee <2> is assigned to the activity <1> in project "Project"
       Then the employee <2> is contained in the activity <1> in the project "Project"

   #Alternative scenario
   #Scenario: The employee is currently working on <20> activities
       #Given there is an activity "Activity" assigned to a project "Project"
       #When the project leader selects an employee "Employee"
       #And the employee "Employee" is currently working on <20> projects
       #Then the system provides an error message that the employee is not available for a new activity

   #Unassigning an employee
   #Scenario: Project leader unassigns an employee
       #Given an employee "Employee" is assigned to a project "Activity"
       #When the project leader selects the employee "Employee"
       #And the project leader confirms to unassign "Employee" from "Activity"Then the employee "Employee" is no longer assigned to the activity "Activity"

   #Employee is already assigned
   #Scenario: Fail scenario when assigning an employee
       #Given there is an activity "Activity" assigned to a project "Project"
       #When the project leader selects an employee "Employee"
       #And the employee "Employee" is already assigned to the selected activity "Activity"
       #Then the system provides an error message that the employee "Employee" is already assigned to the selected activity