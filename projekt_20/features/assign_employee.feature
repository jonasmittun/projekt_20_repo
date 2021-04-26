Feature: Assigning Employee
   Description: The project leader assigns an employee to an activity
   Actor: Project leader

   #Assigning an employee
   Scenario: Project leader assigns an employee to an activity
       Given there exists an activity "Activity 1" with id <1> in project "Project 1"
       And there exists an employee with id <1> which is project leader for project "Project 1"
       And employee <1> is the user
       And there exists an employee <2>
       And the employee <2> is currently working on less than <20> activities
       When the project leader <1> assigns the employee <2> to the activity <1> in project "Project 1"
       Then the employee <2> is assigned to the activity <1> in project "Project 1"

   #Alternative scenario
   Scenario: The employee is currently working on <20> activities
     Given there exists an activity "Activity 1" with id <1> in project "Project 1"
     And there exists an employee with id <1> which is project leader for project "Project 1"
     And there exists an employee <3>
     And the employee <3> is currently working on <20> activities or more
     When the project leader <1> assigns the employee <3> to the activity <1> in project "Project 1"
     Then the error message "Employee is working too much" is given

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