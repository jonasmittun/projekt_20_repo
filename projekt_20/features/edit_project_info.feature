Feature: Edit project info
   Description: The data of a project is edited
   Actor: Project leader

   Scenario: Project leader edits the project name
         Given the system contains a project "project 20"
         And an employee “employee 20” is the project leader for the project "project 20"
         When the employee <20> sets the project name "project 20" to be "new name"
         Then the project name is "new name"

   Scenario: Projects info is edited by employee that is not project leader
         Given the system contains a project "project 1"
         And an employee “employee 2” is not the project leader for the project "project 1"
         When the employee <2> sets the project name "project 1" to be "new name"
         Then the error message "You must be project leader" is given