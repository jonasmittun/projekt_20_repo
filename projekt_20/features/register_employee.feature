Feature: Register employees
	Description: Registering employees
	Actor: Employee

Scenario: A new employee is registered to the system
	Given there is an employee
	And the employee is not already registered to the system
	When the employee is added to the system
	Then the employee is registered to the system
