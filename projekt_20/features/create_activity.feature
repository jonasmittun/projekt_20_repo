Feature: Create Activity
   Description: The project leader creates activities for the projects
   Actor: Project leader

   Creating an activity
   Scenario: Project leader creates an activity
         Given a project "project 1" exists with employee <1> as project leader
         And employee <1> is the user
  		 When an activity is created in project "project 1"
         Then the activity <1> is registered in the project "project 1"


