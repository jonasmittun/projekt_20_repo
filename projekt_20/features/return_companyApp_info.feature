Feature: Return CompanyApp info
	Description: CompanyApp info must be reported correctly for multiple internal uses, this extends to projects, activities and employees.
	Actor: System

Scenario: Check if project named "project 1" exists
	Given companyApp exists
	And project "project 1" exists within companyApp
	When system asks if project "project 1" exists within companyApp
	Then assert that project "project 1" exists within companyApp
	
Scenario: Check which projects exist within companyApp
	Given companyApp exists
	When system asks which projects exist within companyApp
	Then return arrayList with projects that exist within companyApp

Scenario: Check if activity with id <1> exists within project "project 1"
	Given companyApp exists
	And project "project 1" exists within companyApp
	And activity with id <1> exists within project "project 1"
	Then assert that activity with id <1> exists within project "project 1"

Scenario: Check which activities exist within project "project 1"
	Given companyApp exists
	And project "project 1" exists within companyApp
	When system asks which activities exist within project "project 1"
	Then return arrayList with activities that exist within project "project 1"

Scenario: Check which activities exist within companyApp
	Given companyApp exists
	When system asks which activities exist within companyApp
	Then return arrayList with activities that exists within companyApp
	
Scenario: Check if employee with id <1> exists within activity with id <1>
	Given companyApp exists
	And project "project 1" exists within companyApp
	And activity with id <1> exists within project "project 1"
	And employee with id <1> exists within activity with id <1>
	Then assert that employee with id <1> exists within activity with id <1>
	
Scenario: Check if employee with id <1> exists within project "project 1"
	Given companyApp exists
	And project "project 1" exists within companyApp
	And employee with id <1> exists within project "project 1"
	Then assert that employee with id <1> exists within project "project 1" 

Scenario: Check if employee with id <1> exists within companyApp
	Given companyApp exists
	And employee with id <1> exists within companyApp
	Then assert that employee with id <1> exists within companyApp
	
Scenario: Check which employees exist within activity with id <1>
	Given companyApp exists
	And project "project 1" exists within companyApp
	And activity with id <1> exists within project "project 1"
	When system asks which employees exist within activity with id <1>
	Then return arrayList with employees that exist within activity with id <1>
	
Scenario: Check which employees exist within project "project 1"
	Given companyApp exists
	And project "project 1" exists within companyApp
	When system asks which employees exist within project "project 1"
	Then return arrayList with employees that exist within project "project 1"
	
Scenario: Check which employees exist within companyApp
	Given companyApp exists
	When system asks which employees exist within companyApp
	Then return arrayList with employees that exists within companyApp