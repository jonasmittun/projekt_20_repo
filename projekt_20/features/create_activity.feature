Feature: Create Activity
   Description: The project leader creates activities for the projects
   Actor: Project leader

   Creating an activity
   Scenario: Project leader creates an activity //Boran
         Given a project "project 1" exists with employee <1> as project leader
         And employee <1> is the user
  		 When an activity is created in project "project 1"
         Then the activity <1> is registered in the project "project 1"

   Scenario: An activity is created by a user that is not the project leader //Asger
         Given a project "project 1" exists with employee <1> as project leader
         And employee <2> is the user
         When an activity is created in project "project 1"
         Then the error message "You must be project leader" is given

