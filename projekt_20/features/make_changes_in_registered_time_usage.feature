Feature: Make changes in registered time usage
   Description: The employee changes registered time usage for
   activities
   Actor: Employee

   Scenario: The employee gets an overview over time usage
       #When the employee asks for an overview over time usage
       #Then the employee gets an overview over time usage

   Scenario: The employee adds to registered time
       #Given the employee has registered time with an activity
       #When the employee adds hours to the registered time of the activity
       #Then the hours get added to the activity

   Scenario: The employee removes registered time
       #Given the employee has registered time with an activity
       #When the employee removes hours from the registered time of the activity
       #Then the hours are removed from the activity

   Scenario: The employee changes registered time without registered time
       #Given the employee no registered time with an activity
       #When the employee changes the hours of the activity
       #Then the error message "No hours registered with the activity" is given