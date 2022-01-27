Feature: Smoke
  As a user
  I want to test BBC site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check text of headline
    Given User is on the '<homePage>' page
    When User clicks on News button on Home page
    Then User checks that headline article title is the following <headlineTitle>

    Examples:
      | homePage             | headlineTitle                                      |
      | https://www.bbc.com/ | Pipeline won't open if Russia invades Ukraine - US |

  Scenario Outline: Check text of secondary article titles
    Given User is on the '<homePage>' page
    When User clicks on News button on Home page
    Then User checks secondary article titles are the following <secondaryArticleTitles>

    Examples:
      | homePage             | secondaryArticleTitles                                                                                                                 |
      | https://www.bbc.com/ | Following Ukraine's bomb shelter map, Portraits of last Holocaust survivors unveiled, US Navy officer 'bribed by cash and prostitutes' |

  Scenario Outline: Check title of headline article from Category
    Given User is on the '<homePage>' page
    And User clicks on News button on Home page
    When User moves to the news by Category link on News page
    Then User checks that the title of the first article is the same as <firstArticleTitle>

    Examples:
      | homePage             | firstArticleTitle                                  |
      | https://www.bbc.com/ | Pipeline won't open if Russia invades Ukraine - US |

  Scenario Outline: Checks the error messages on Send Us Question form on News page
    Given User is on the '<homePage>' page
    And User clicks on News button on Home page
    And User clicks on Coronavirus button on News page
    And User clicks on Your Coronavirus Stories button on News page
    And User clicks on Coronavirus: Send us your questions link on News page
    When User sets required data on Send Us Question form on News page
      | question       | name   | email   |
      | <questionText> | <name> | <email> |
    And User '<clicks>' on Terms Of Service checkbox on News page
    And User clicks on Submit button on Send Us Question form on News page
    Then User checks that the <ErrorMessages> appears
    And The Name field content '<is>' deleted

    Examples:
      | homePage             | questionText | email         | clicks         | is     | ErrorMessages                                                                       | name                                                                                                                                                                                                                                                                 |
      | https://www.bbc.com/ | Test Message | test@test.com | clicks         | is not | Name is too long (maximum is 255 characters)                                        | qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq |
      | https://www.bbc.com/ | Test Message | [blank]       | clicks         | is not | Email address can't be blank                                                        | Test Name                                                                                                                                                                                                                                                            |
      | https://www.bbc.com/ | [blank]      | test@test.com | clicks         | is not | can't be blank                                                                      | Test Name                                                                                                                                                                                                                                                            |
      | https://www.bbc.com/ | Test Message | test@test.com | clicks         | is     | Name can't be blank                                                                 | [blank]                                                                                                                                                                                                                                                              |
      | https://www.bbc.com/ | [blank]      | [blank]       | does not click | is     | can't be blank, Name can't be blank, Email address can't be blank, must be accepted | [blank]                                                                                                                                                                                                                                                              |
