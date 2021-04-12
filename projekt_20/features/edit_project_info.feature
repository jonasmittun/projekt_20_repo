Feature: Edit project info
   Description: The data of a project is edited
   Actor: Project leader

   Scenario: Project leader edits info
         Given a project “project 1” exists
         And an employee “employee 1” is the project leader
         When the employee edits the project
         Then the project asks for new deadline and expected time usage
         When the employee enters the info
         Then the projects info is the new info

   Scenario: Projects info is edited by employee that is not project leader
         Given a project “project 1” exists
         And an employee “employee 1” is the project leader
         And the employee edits the project
         Then the error message “you must be project leader” is given