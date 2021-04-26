Feature: Assigning Employee
   Description: The project leader assigns an employee to an activity
   Actor: Project leader

   #Assigning an employee
   Scenario: Project leader assigns an employee to an activity

       Given there exists an activity "activity 1" with id <1> in project "project 1"
       And there exists an employee with id <1> which is project leader for project "project 1"
       And there exists an employee <2> who is not assigned to the activity <1> in project "project 1"
       And employee <1> is the user
       And the employee <2> is currently working on less than <20> activities
       When the project leader <1> assigns the employee <2> to the activity <1> in project "project 1"
       Then the employee <2> is assigned to the activity <1> in project "project 1"

   #Alternative scenario
   Scenario: The employee is currently working on <20> activities
     Given there exists an activity "activity 1" with id <1> in project "project 1"
     And there exists an employee with id <1> which is project leader for project "project 1"
     And there exists an employee <3> who is not assigned to the activity <1> in project "project 1"
     And the employee <3> is currently working on <20> activities or more
     When the project leader <1> assigns the employee <3> to the activity <1> in project "project 1"
     Then the error message "Employee is working too much" is given

   #Unassigning an employee
   Scenario: Project leader unassigns an employee
       Given there exists an employee <2>
       And the employee with id <2> is assigned to the activity with id <1> in "project 1"
       And there exists an employee with id <1> which is project leader for project "project 1"
       When the project leader <1> unassigns the employee <2> from the activity with id <1> in "project 1"
       Then the employee with id <2> is no longer assigned to activity with id <1> in "project 1"

   #Employee is already assigned
   Scenario: Fail scenario when assigning an employee
     Given there exists an activity "activity 1" with id <1> in project "project 1"
     And there exists an employee with id <1> which is project leader for project "project 1"
     And there exists an employee <2> who is assigned to the activity <1> in project "project 1"
     And the employee <2> is currently working on less than <20> activities
     When the project leader <1> assigns the employee <2> to the activity <1> in project "project 1"
     Then the error message "Employee is already assigned" is given