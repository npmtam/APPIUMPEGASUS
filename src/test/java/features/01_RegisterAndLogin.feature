@RegisterAndLogin
Feature: Register new account and login
  As a new customer
  I want to register and login to the application normally

  Scenario: 01 - Register to Passenger application
    Given I logout if currently logged in
    And I click to phone number text box
    When I select "beta" server and input "uatpax" fleet code
    And I select the phone code by "Vietnam" country
    And I input the phone number
      | phoneNumber |
      | 357888911   |
    And I agree with Term of use and Privacy policy
    And I click to continue button
    And I input sms verify code if have
    And I verify register message contains "MyCar"
    And I input user information to register
      | FirstName | LastName | Email             | ID/IC     | Gender |
      | John      | Wick     | tamqada@gmail.com | 123123123 | Female |
    And I click to save button
    Then I verify the passenger register successfully

  Scenario Outline: 02 - Login to Pax app by the phone number just registered
    Given I logout if currently logged in
    And I click to phone number text box
    When I input the phone number
      | phoneNumber   |
      | <PhoneNumber> |
    And I agree with Term of use and Privacy policy
    And I click to continue button
    And I input sms verify code if have
    Then I verify the passenger login successfully
    Examples: Login with the number just registered and the phone number which used to login app
      | PhoneNumber |
      | 357888911   |
      | 356822833   |
    #1: Phone number that was registered in 1st test case
    #2: Phone number which used to login app
