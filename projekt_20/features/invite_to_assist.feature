Feature: Invite to assist
   Description: An employee can invite any other employee to assist them with an activity
   Actor: Employee that does the inviting

	# Invite Employee to Activity
	Scenario: employee <1> invites employee <2> to assist with activity <3> //Jonas
		Given employee <1> exists within activity <3>
		And employee <2> exists within companyApp
		When employee <2> is invited to activity <3> in "testProject"
		Then employee <2> is assigned to activity <3> in "testProject"
		But do not assign employee <2> to activity <3> parent project

	# Invite Employee to Activity where said Employee already exists
	Scenario: employee <1> invites already existing employee <2> to assist with activity <3> //Jonas
		Given employee <1> exists within activity <3>
		And employee <2> exists within activity <3>
		When employee <2> is invited to activity <3> where it already exists
		Then give the exception "Employee being invited is already assigned to activity!"
