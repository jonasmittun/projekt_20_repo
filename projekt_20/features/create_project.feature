Feature: Create project
  Description: A project is created in the system
  Actor: Employee

  Scenario: Employee creates an empty project
    Given an employee wants to create a project named "project 1".
    When employee registers the project
    Then the project is registered in the system