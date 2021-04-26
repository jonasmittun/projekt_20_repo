Feature: Create project
	Description: A project is created in the system
	Actor: Employee

Scenario: an empty project is created
	Given a project "project 1" does not exist in the system
	When a project named "project 1" is created
	And the project is added to the system
	Then the system contains a project named "project 1"

Scenario: Create project with existing project name
	Given a project exists in the system
	When the project is added to the system
	Then the error message "Project already exists" is given

  Scenario: Employee is assigned project leader
	Given a project exists in the system
	And an employee <1> exists in the system
	When the employee <1> is assigned as project leader of the project "project"
	#Then the project leader is the employee <1>

