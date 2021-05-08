Feature: Software UI
   Description: UI elements that make the software operable by the end user
   Actor: Controller

Scenario: User starts program
#Given program is not started
When someone starts the program
Then display first program screens

Scenario: User inputs valid id <1> and accepts
#Given program is not started
When someone starts the program
And someone inputs valid id <1>
And someone confirms by inputting "y"
Then display main menu screen

Scenario: User inputs valid id <1> and declines
#Given program is not started
When someone starts the program
And someone inputs valid id <1>
And someone declines by inputting "n"
Then display input user ID screen

Scenario: User inputs invalid id <0>
Given program is not started
When someone starts the program
And someone inputs invalid id <0>
Then display error screen
And display input user ID screen on any input

