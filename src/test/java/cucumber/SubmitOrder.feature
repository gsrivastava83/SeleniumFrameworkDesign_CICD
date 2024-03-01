
@PlacingAnOrder
Feature: Placing an order
  Add product in cart and place the order. 

Background: Landing on ecommerce website
Given User is on ecommerce website
  
  @PlaceOrder
  Scenario Outline: Placing an order
    When user login to the ecommerce website with username <name> and password <password>
    Then user adds a product <productName> in cart
    Then user verifies product <productName> in cart
    When user places the order on website
    Then user verifies the confirmation message "THANKYOU FOR THE ORDER." on the website

    Examples: 
      | name  | password | productName  |
      | gunjan@bedbath.com | Bedbath@100 | ZARA COAT 3 |
      
      
      
