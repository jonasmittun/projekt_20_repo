Feature: Register future activities (etc. vacation)
   Description: The employee registers time for holidays
   Actor: Employee

   Scenario: The employee reserves time for holidays
       Given the employee has no activity in a given time period
       When the employee reserves time in the given time period for holidays
       Then the employee can not get new activities in the given time period

   Scenario: The employee removes time for holidays
       Given the employee has reserved time for holidays in a given time period
       When the employee removes the reserved time for holidays
       Then the employee can get new activities in the given time period