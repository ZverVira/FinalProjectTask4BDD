Feature: Smoke
  As a user
  I want to test BBC site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check text of headline
    Given User is on the '<homePage>' page
    When User clicks on News button
    Then User checks that headline article title is the following '<headlineTitle>'

    Examples:
      | homePage             | headlineTitle                                   |
      | https://www.bbc.com/ | UK police to investigate No 10 lockdown parties |


  Scenario Outline: Check text of secondary article titles
    Given User is on the '<homePage>' page
    When User clicks on News button
    Then User checks secondary article titles are the following <secondaryArticleTitles>

    Examples:
      | homePage             | secondaryArticleTitles                                                                                                                                                                                                                                |
      | https://www.bbc.com/ | US could sanction Putin if Russia invades - Biden, French tourist jailed in Iran on spying charges, Thousands of drivers stranded by Athens snowstorm, Bollywood star cleared of obscenity over kiss, Pfizer-BioNTech start trials of new Omicron jab |

  Scenario Outline: Check title of headline article from Category
    Given User is on the '<homePage>' page
    And User clicks on News button
    When User moves to the news by Category link
    Then User checks that the title of the first article is the same as '<firstArticleTitle>'

    Examples:
      | homePage             | firstArticleTitle                            |
      | https://www.bbc.com/ | Police to investigate No 10 lockdown parties |

  Scenario Outline: Check that appropriate error messages appears after clicking on Submit button on Send Us Question form without entering data.
    Given User is on the '<homePage>' page
    And User clicks on News button
    And User clicks on Coronavirus button
    And User clicks on Your Coronavirus Stories button
    And User clicks on Coronavirus: Send us your questions link
    When User clicks on Submit button on Send Us Question form
    Then User checks that the <ErrorMessages> appears

    Examples:
      | homePage             | ErrorMessages                                                                    |
      | https://www.bbc.com/ | can't be blank,Name can't be blank,Email address can't be blank,must be accepted |


  Scenario Outline: Checks the error messages on Send Us Question form
    Given User is on the '<homePage>' page
    And User clicks on News button
    And User clicks on Coronavirus button
    And User clicks on Your Coronavirus Stories button
    And User clicks on Coronavirus: Send us your questions link
    When User sets required data on Send Us Question form
      | question       | name   | email   |
      | <questionText> | <name> | <email> |
    And User clicks on Terms Of Service checkbox
    And User clicks on Submit button on Send Us Question form
    Then User checks that the <ErrorMessages> appears

    Examples:
      | homePage             | questionText | email         | ErrorMessages                                                     | name                                                                                                                                                                                                                                                                 |
      | https://www.bbc.com/ | Test Message | test@test.com | Name is too long (maximum is 255 characters)                      | qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq |
      | https://www.bbc.com/ | Test Message | [blank]       | Email address can't be blank                                      | Test Name                                                                                                                                                                                                                                                            |
      | https://www.bbc.com/ | [blank]      | test@test.com | can't be blank                                                    | Test Name                                                                                                                                                                                                                                                            |
      | https://www.bbc.com/ | Test Message | test@test.com | Name can't be blank                                               | [blank]                                                                                                                                                                                                                                                              |
      | https://www.bbc.com/ | [blank]      | [blank]       | can't be blank, Name can't be blank, Email address can't be blank | [blank]                                                                                                                                                                                                                                                              |

  Scenario Outline: Checks the name field content is not deleted in case of error
    Given User is on the '<homePage>' page
    And User clicks on News button
    And User clicks on Coronavirus button
    And User clicks on Your Coronavirus Stories button
    And User clicks on Coronavirus: Send us your questions link
    When User sets required data on Send Us Question form
      | question       | name   | email   |
      | <questionText> | <name> | <email> |
    And User clicks on Terms Of Service checkbox
    And User clicks on Submit button on Send Us Question form
    Then User checks that the <ErrorMessages> appears
    And The Name field content is not deleted

    Examples:
      | homePage             | questionText | email         | ErrorMessages                                | name                                                                                                                                                                                                                                                                 |
      | https://www.bbc.com/ | Test Message | test@test.com | Name is too long (maximum is 255 characters) | qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq |



