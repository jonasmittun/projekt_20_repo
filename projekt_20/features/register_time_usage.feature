Feature: Register time-usage
   Description: The employee registers time usage for activities worked for the day
   Actor: Employee

   Scenario: The employee registers time usage for the days activities
       Given the employee has due activities for the day
       And the employee has worked on activities for the day
       When the employee registers <int> hours worked on the activities for the day
       Then the hours worked on the activities for the day get added to activities rapport