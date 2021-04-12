Feature: Create Activity
   Description: The project leader creates activities for the projects
   Actor: Project leader

   #Creating an activity
   Scenario: Project leader creates an activity
         Given there is new a project "Project"
         When the project leader wants to create a new activity "Activity" with "int" working hours with startDate "date_start" and an end date "date_end"
         Then the activity "Activity" is created with <int> working hours and <date_start> to <date_end>
         And the "Activity" is assigned to "Project"

   #Alteration in activities
   Scenario: Making an alteration in an existing activity
         Given there is an an existing activity
         When the project leader wishes to change the <int> working hours for the "Activity" to <new_int> working hours
         Then the "Activity" has been updated with <new_int> working hours and <new_date_start> and <new_date_end>