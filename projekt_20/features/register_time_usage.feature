Feature: Register time-usage
   Description: The employee registers time usage for activities worked for the day
   Actor: Employee

   Scenario: The employee adds hours for the days activities
     Given there exists an employee <2> who is assigned to the activity <1> in project "project 1"
     And employee <2> is the user
     When the employee <2> registers <10> half hours worked on activity <1> in project "project 1" for the day
     Then employee <2>'s worked hours is updated to <10> half hours
     Then activity <1> in project "project 1" worked hours is updated to <10> half hours

  Scenario: The employee adds too many hours for the days activities
    Given there exists an employee <2> who is assigned to the activity <1> in project "project 1"
    And employee <2> is the user
    When the employee <2> registers <50> half hours worked on activity <1> in project "project 1" for the day
    Then the error message "Employee can't work more than 24 hours. Try again" is given

  Scenario: The employee removes hours from the weeks registry
    Given there exists an employee <2> who is assigned to the activity <1> in project "project 1"
    And employee <2> is the user
    And the employee <2> registers <10> half hours worked on activity <1> in project "project 1" for the day
    When the employee <2> removes <5> half hours from the week registry and activity <1> in project "project 1"
    Then employee <2>'s worked hours is updated to <5> half hours
    Then activity <1> in project "project 1" worked hours is updated to <5> half hours

  Scenario: The employee removes more hours from the weeks registry than exist
    Given there exists an employee <2> who is assigned to the activity <1> in project "project 1"
    And employee <2> is the user
    And the employee <2> registers <10> half hours worked on activity <1> in project "project 1" for the day
    When the employee <2> removes <15> half hours from the week registry and activity <1> in project "project 1"
    Then employee <2>'s worked hours is updated to <0> half hours
    Then the error message "You have emptied your weeks work" is given

  Scenario: Get activities that an employee is assigned to
    Given an employee with id <2> is registered to the system
    And employee <1> is the user
    And the employee <2> is assigned to a number of activities in project "project 1"
    When system gets list of activities that employee <2> is assigned to
    Then return list of activities that include the number of activities