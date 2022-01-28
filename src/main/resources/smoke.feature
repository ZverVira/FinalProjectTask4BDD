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
      | homePage             | secondaryArticleTitles                                                                                                                                                                                   |
      | https://www.bbc.com/ | Ukraine: How big is Russia's military build-up?, N Korea missile tests: What does Kim Jong-un want?, The story behind the picture of this lone survivor, Former SS member speaks of shame over Nazi past |

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

  Scenario Outline: Check that team scores display correctly on Team page and equal to team scores on Sport page
    Given User is on the '<homePage>' page
    And User clicks on Sport button on Home page
    And User selects Football menu item on Sport page
    And User selects Scores And Fixtures menu item on Sport page
    And User performs searching by championship '<championship>'
    And User selects month of championship '<date>'
    When User checks that results of the first available game on Sport page equal '<gameResult>'
    And User clicks on the first available game link on Sport page
    Then User check that results of the first available game on Game page equal '<gameResult>'

    Examples:
      | homePage             | championship          | date    | gameResult                     |
      | https://www.bbc.com/ | Scottish Championship | 2021-12 | Kilmarnock 1 1 Greenock Morton |
      | https://www.bbc.com/ | Champions League      | 2021-10 | Barcelona 1 0 Dynamo Kyiv      |
      | https://www.bbc.com/ | Bayern Munich         | 2021-11 | Dynamo Kyiv 1 2 Bayern Munich  |

  Scenario Outline: Check that results of the first available game on Game page equal to results of the first available game on Sport page
    Given User is on the '<homePage>' page
    When User clicks on Sport button on Home page
    And User selects Football menu item on Sport page
    And User selects Scores And Fixtures menu item on Sport page
    And User performs searching by championship '<championship>'
    And User selects month of championship '<date>'
    Then User check that results of the first available game on Game page equal to results of the first available game on Sport page

    Examples:
      | homePage             | championship          | date    |
      | https://www.bbc.com/ | Scottish Championship | 2021-12 |
      | https://www.bbc.com/ | Champions League      | 2021-10 |
      | https://www.bbc.com/ | Bayern Munich         | 2021-11 |
