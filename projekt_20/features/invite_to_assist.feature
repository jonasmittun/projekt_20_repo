Feature: Invite to assist
   Description: An employee can invite any other employee to assist them with an activity
   Actor: Employee that does the inviting

	# Invite Employee to Activity
	Scenario: employee <1> invites employee <2> to assist with activity <3>
		Given employee <1> exists within activity <3>
		And employee <2> exists within companyApp
		When employee <2> is invited to activity <3> in "testProject"
		Then employee <2> is assigned to activity <3> in "testProject"
		But do not assign employee <2> to activity <3> parent project

	# Invite Employee to Activity where said Employee already exists
	Scenario: employee <1> invites already existing employee <2> to assist with activity <3>
		Given employee <1> exists within activity <3>
		And employee <2> exists within activity <3>
		When employee <2> is invited to activity <3> where it already exists
		Then give the exception "Employee being invited is already assigned to activity!"

### Old features are a bit shit, redoing the invite features

   # Invite NULL Employee to Activity
   #Scenario: Helpless Employee invites NULL Employee to assist with an Activity
       #Given there is an existing Employee
       #And Employee is assigned to Activity
       #When Employee invites NULL Employee to assist with Activity
       #Then return Error "No such Employee exists within the company!"

   # Invite Employee to NULL Activity
   #Scenario: Helpless Employee invites Expert Employee to assist with an NULL Activity
       #Given there is an existing Employee
      # And Employee exists within Company
       #When Employee invites Expert Employee to assist NULL Activity
       #Then return Error "Employee not assigned to Activity OR Activity doesn't exist!"

   # Invite already present Employee to Activity
   #Scenario: Helpless Employee invites Expert Employee to assist with an Activity
       #Given there is an existing Employee
       #And Employee is assigned to Activity
       #And Expert Employee is assigned to Activity
       #When Employee invites Expert Employee to assist with Activity
       #Then return Error "Expert Employee is already present!"