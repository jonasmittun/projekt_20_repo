Feature: View Employee time-usage
   Description: The Project Leader views used and planned time for employees work.
   Actor: Project Leader

   # View Employee time-usage
   Scenario: Project leader views time-usage for an Employee
       #Given there is an existing Employee
       #When the Project Leader views time-usage for said Employee
       #Then display all time usage registered by Employee
       #And display all planned time usage for said Employee

   # View Activity time-usage
   Scenario: Project leader views time-usage for activity
       #Given there is an existing activity
       #When the Project Leader views time-usage for said activity
       #Then display time usage registered by all assigned employees
       #And display planned time usage for all assigned employees

   # View Project time-usage
   Scenario: Project leader views time-usage for Project
      #Given there is an existing Project
      #When the Project Leader views time-usage for said Project
      #Then display time usage registered by all assigned employees
      #And display planned time usage for all assigned employees

   # View time-usage for Null Object
   Scenario: Project leader views time-usage for Null Object, and fails!
      #Given there is not chosen an Object compatible with viewing time or the Project Leader specifies an undefined Object name
      #When the Project Leader views time-usage for said Object
      #Then display error "Not possible to view time-usage for <Object>"
      #And if applicable, display error type