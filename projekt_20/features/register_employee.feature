Feature: Register employees
	Description: Registering employees
	Actor: Employee

Scenario: A new employee is registered to the system //Boran
	Given a new employee is not registered to the system
	When the employee is added to the system
	Then the employee is registered to the system

Scenario: Get projects that an employee is leading //Boran
	Given an employee with id <1> is registered to the system
	And the employee <1> is assigned as project leader of the project "project 1"
	When system gets list of projects that employee <1> is leading
	Then return list of projects that includes project named "project 1"
	