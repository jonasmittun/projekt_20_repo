Feature: Create project
  Description: A project is created in the system
  Actor: Employee

  Scenario: Employee creates an empty project
      Given an employee wants to create a project named "project 1".
      When employee registers the project
      Then the project is registered in the system

  Scenario: Create project with existing project name
      Given an employee wants to create a project named "project 1"
      And a project named "project 1" already exists
      When employee registers the projects
      Then the error message "Project already exists" is given

  Scenario: Employee is assigned project leader
      Given a project "project 1" exists
      And an employee named "employee 1" exists
      When the employee assigns the employee as project leader of the project
      Then the employee is project leader of the project

  Scenario: Assign employee as project leader on project with existing project leader
      Given a project "project 1" exists with project leader "employee 1"
      And an employee named "employee 2" exists
      When the employee assigns the employee as project leader of the project
      Then the error message "Project leader is already assigned" is given