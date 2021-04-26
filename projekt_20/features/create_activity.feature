Feature: Create Activity
   Description: The project leader creates activities for the projects
   Actor: Project leader

   Creating an activity
   Scenario: Project leader creates an activity
         Given a project "project 1" exists with employee <1> as project leader
         And employee <1> is the user
         #And there exists an activity <1>
  		 #When the activity is added to the project
         #Then the activity is registered in the project

   #Alteration in activities
   #Scenario: Making an alteration in an existing activity
         #Given a project with an activity and a project leader exists in the system
         #And the project leader is the user
         #When the activity is edited with new name "Activity 1" and deadline <1>
         #Then the activity's name is "Activity 1" and deadline is <1> 
