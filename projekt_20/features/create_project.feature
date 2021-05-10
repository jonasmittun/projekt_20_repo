Feature: Create project
	Description: A project is created in the system
	Actor: Employee

Scenario: an empty project is created //Roi
	Given a project "project lol" does not exist in the system
	When a project named "project lol" is created
	And the project is added to the system
	Then the system contains a project named "project lol"

Scenario: a new project is created with currentDate and endDate //Roi
	Given a project "project dtu" does not exist in the system
	When a project named "project dtu" is created with a startDate 01/01/2020 and an endDate 02/01/2020
	And the project is added to the system
	Then the system contains a project named "project dtu"

Scenario: Create project with existing project name //Boran
	Given a project exists in the system
	When the project is added to the system
	Then the error message "Project already exists" is given

Scenario: Employee is assigned project leader //Boran
	Given a project exists in the system
	And there exists an employee <1>
	When the employee <1> is assigned as project leader of the project "project"
	Then the project leader is the employee <1>
	
Scenario: Get project that does not exist within system //Roi
	Given a project "project lol" does not exist in the system
	When a project named "project lol" is searched for
	Then null is returned by system
	
Scenario: Rename project that exists in system //Roi
	Given a project "project lol" does exist in the system
	And a project named "project omegalul" does not exist in the system
	When a project named "project lol" is renamed to "project omegalul"
	Then the system contains a project named "project omegalul"
	But the system does not contain a project named "project lol"