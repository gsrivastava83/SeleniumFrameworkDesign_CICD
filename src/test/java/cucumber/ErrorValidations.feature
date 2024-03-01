
@ErrorValidation
Feature: Error Validations
  I want to use this template for my feature file
  
  
Background: Landing on ecommerce website
Given User is on ecommerce website

  @LoginErrorValidation
  Scenario Outline: Login Error Validation
     When user login to the ecommerce website with username <name> and password <password>
     Then I verify the error message "Incorrect email or password." displayed
     
    Examples: 
      | name  | password | 
      | gunjan@bedbath.com | Bedbath100 | 
      
      
  @ProductErrorValidation
  Scenario Outline: Product Error Validation
     When user login to the ecommerce website with username <name> and password <password>
     Then user adds a product <productName> in cart
     Then user verifies product <productName> in cart
     
    Examples: 
      | name  | password | productName |
      | gunjan@bedbath.com | Bedbath@100 | IPHONE 13 PRO |    
