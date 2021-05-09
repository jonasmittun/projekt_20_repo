Feature: Create project
	Description: A project is created in the system
	Actor: Employee

Scenario: an empty project is created
	Given a project "project lol" does not exist in the system
	When a project named "project lol" is created
	And the project is added to the system
	Then the system contains a project named "project lol"

Scenario: Create project with existing project name
	Given a project exists in the system
	When the project is added to the system
	Then the error message "Project already exists" is given

Scenario: Employee is assigned project leader
	Given a project exists in the system
	And there exists an employee <1>
	When the employee <1> is assigned as project leader of the project "project"
	Then the project leader is the employee <1>

Scenario: an empty project with starting and finish week is added
	#Given a project "project lol" does not exist in the system
	#When a project named "project lol" with starting week <1> and finish week <4> is created
	#And the project is added to the system
	#Then the system contains a project named "project lol" with starting week <1> and finishing week <4>

Scenario: an empty project with a starting week after the finish week
	#Given a project "project lol" does not exist in the system
	#When a project named "project lol" with starting week <4> and finish week <1> is created
	#Then the error message "Starting week must be before finishing week" is given
	
Scenario: Get project that does not exist within system
	Given a project "project lol" does not exist in the system
	When a project named "project lol" is searched for
	Then null is returned by system
	
Scenario: Rename project that exists in system
	Given a project "project lol" does exist in the system
	And a project named "project omegalul" does not exist in the system
	When a project named "project lol" is renamed to "project omegalul"
	Then the system contains a project named "project omegalul"
	But the system does not contain a project named "project lol"