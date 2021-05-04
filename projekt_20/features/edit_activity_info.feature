Feature: Create Activity
  Description: The project leader edits activities info
  Actor: Project leader

  Scenario: The project leader edits the name of an activity
    Given a project "project 1" exists with employee <1> as project leader
    And the activity <1> is registered in the project "project 1"
    And employee <1> is the user
    When the activity <1> is edited with new name "Activity 2" in project "project 1"
    Then the activity <1>'s name is "Activity 2" in the "project 1"

  Scenario: An activity is edited by a user that is not the project leader
    Given a project "project 1" exists with employee <1> as project leader
    And the activity <1> is registered in the project "project 1"
    And employee <2> is the user
    #When the activity <1> is edited with new name "Activity 2" in project "project 1"
    #Then the error message "You must be project leader" is given

