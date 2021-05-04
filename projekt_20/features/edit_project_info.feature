Feature: Edit project info
   Description: The data of a project is edited
   Actor: Project leader

   Scenario: Project leader edits the project name
         Given the system contains a project "project 20"
         And an employee “employee 20” is the project leader for the project "project 20"
         And employee <20> is the user
         When the project "project 20" is edited with new name "new name"
         Then the project name is "new name"

   Scenario: Projects info is edited by employee that is not project leader
        Given the system contains a project "project 1"
        And an employee “employee 2” is not the project leader for the project "project 1"
        And employee <2> is the user
        When the project "project 1" is edited with new name "new name"
        Then the error message "You must be project leader" is given

   Scenario: projects name is changed to name already in use
        Given the system contains a project "project 20"
        And the system contains a project "project 1"
        And an employee “employee 20” is the project leader for the project "project 20"
        #When the project "project 20" is edited with new name "project 1"
        #Then the error message "Project name must original" is given

   Scenario: Projects deadline is changed
        Given the system contains a project "project 1"
        And an employee “employee 1” is the project leader for the project "project 1"
        And employee <1> is the user
        When the deadline for "project 1" is set to 2022-01-01
        Then the deadline of project "project 1" is 2022-01-01
