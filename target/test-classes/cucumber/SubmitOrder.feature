#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase the Order from Ecommerce Website
  I want to use this template for my feature file

	Background: 
	Given I landed on the Ecommerce page
	
  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add the product <productName> to cart
    And  Checkout <productName> and submit the Order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage 

    Examples: 
      | username 							    | password 				 | productName  		   |
      | kaushikprince31@gmail.com | KKKKKkkkkk@9 	   | ZARA COAT 3     |
      | prince123@gmail.com			  | KKKKKkkkkk@98136 | ADIDAS ORIGINAL |
