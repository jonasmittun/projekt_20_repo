Feature: Register time-usage
   Description: The employee registers time usage for activities worked for the day
   Actor: Employee

   Scenario: The employee adds hours for the days activities
       Given there exists an employee <2> who is assigned to the activity <1> in project "project 1"
       And employee <2> is the user
       When the employee <2> registers <10> half hours worked on activity <1> in project "project 1" for the day
       Then employee <2>'s worked hours is updated to <10> half hours
       Then activity <1> in project "project 1" worked hours is updated to <10> half hours

  Scenario: The employee removes hours from the weeks registry
    Given there exists an employee <2> who is assigned to the activity <1> in project "project 1"
    And employee <2> is the user
    And the employee <2> registers <10> half hours worked on activity <1> in project "project 1" for the day
    When the employee <2> removes <5> half hours from the week registry and activity <1> in project "project 1"
    Then employee <2>'s worked hours is updated to <5> half hours
    Then activity <1> in project "project 1" worked hours is updated to <5> half hours