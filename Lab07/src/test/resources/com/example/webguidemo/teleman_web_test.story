
Scenario: User inputs correct data
Given user is on Home page
When user inputs data (asd, asd@asd.pl, 123123123, asd, asd, asd) into form
Then form is accepted

Scenario: User inputs incorrect data
Given user is on Home page
When user inputs data (asd, asd, 12123, asd, asd, asd) into form
Then form is rejected
