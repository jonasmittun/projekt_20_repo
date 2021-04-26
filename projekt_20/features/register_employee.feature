Feature: Register employees
	Description: Registering employees
	Actor: Employee

Scenario: A new employee is registered to the system
	Given a new employee is not registered to the system
	When the employee is added to the system
	Then the employee is registered to the system
