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
	
# TODO: Boran lav en feature der tester din getLeaderProjects i companyapp	
#Scenario: Get projects that an employee is leading
#	Given an employee with id <1> is registered to the system
#	And an employee with id <1> is the leader of a project named "project1"
#	When system gets list of projects that employee <1> is leading
#	Then return list of projects that includes project named "project1"
	