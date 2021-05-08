Feature: Register employees
	Description: Registering employees
	Actor: Employee

Scenario: A new employee is registered to the system
	Given a new employee is not registered to the system
	When the employee is added to the system
	Then the employee is registered to the system

Scenario: Get activities for registered employee
	Given an employee with id <1> is registered to the system
	And an employee with id <1> is registered to an activity in the system with id <2>
	When system gets activities for employee with id <1>
	Then return list of activities that includes activity with id <2>