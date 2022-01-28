package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GamePage;
import pages.HomePage;
import pages.NewsPage;
import pages.SportPage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.*;

public class DefinitionSteps {

    private static final long DEFAULT_WAITING_TIME = 30;
    private static final String BBC_URL = "https://www.bbc.com/";

    WebDriver driver;
    HomePage homePage;
    NewsPage newsPage;
    SportPage sportPage;
    GamePage gamePage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BBC_URL);
        pageFactoryManager = new PageFactoryManager(driver);

    }

    @After
    public void tearDown() {
        driver.close();
    }

    @And("User is on the {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @And("User clicks on News button on Home page")
    public void moveToNewsPage() {
        homePage.moveToNewsPage();
        newsPage = pageFactoryManager.getNewsPage();
        newsPage.waitForPageLoadComplete(DEFAULT_WAITING_TIME);
        newsPage.implicitWait(DEFAULT_WAITING_TIME);
        newsPage.closeRegisterModalWindow();
    }

    @And("^User checks that headline article title is the following (.+)$")
    public void checkHeadlineArticleTitle(final String expectedTitle) {
        System.out.println(newsPage.getHeadlineArticleTitleText());
        System.out.println(expectedTitle);
        assertEquals(newsPage.getHeadlineArticleTitleText(), expectedTitle);
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }

    @And("User sets required data on Send Us Question form on News page")
    public void setDataOnSendUsQuestion(final DataTable dataTable) {
        newsPage.waitVisibilityOfElement(DEFAULT_WAITING_TIME, newsPage.getSendQuestionFormHeading());
        dataTable.asMaps().forEach(row -> {
            String question = row.get("question");
            String name = row.get("name");
            String email = row.get("email");
            newsPage.moveToSendUsQuestionForm();
            newsPage.fillSendUsQuestionForm(question, name, email);
        });

    }

    @And("^User checks secondary article titles are the following (.+)$")
    public void checkSecondaryArticleTitles(String expectedTitles) {
        List<String> titlesList = Arrays.stream(expectedTitles.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        assertTrue(newsPage.addSecondaryArticlesTitlesListIntoStringList().containsAll(titlesList));
    }

    @And("User moves to the news by Category link on News page")
    public void moveToTheNewsByCategoryLink() {
        newsPage.moveToNewsByCategoryLink();
    }

    @And("^User checks that the title of the first article is the same as (.+)$")
    public void checkTitleOfTheFirstArticle(final String expectedTitleCategory) {
        assertEquals(newsPage.getHeadlineArticleCategory().getText(), expectedTitleCategory);
    }

    @And("User clicks on Coronavirus button on News page")
    public void clicksCoronavirusButton() {
        newsPage.clickCoronavirusMenuItem();
    }

    @And("User clicks on Your Coronavirus Stories button on News page")
    public void clickYourCoronavirusStoriesButton() {
        newsPage.clickYourCoronavirusStoriesMenuItem();
    }

    @And("User clicks on Coronavirus: Send us your questions link on News page")
    public void clickCoronavirusSendUsYourQuestionsLink() {
        newsPage.clickSendUsQuestionLink();
    }

    @And("User {string} on Terms Of Service checkbox on News page")
    public void clickTermsOfServiceCheckbox(final String checkboxClicking) {
        if (checkboxClicking.equals("clicks")) {
            newsPage.clickTermsOfServiceCheckbox();
        }
    }

    @And("User clicks on Submit button on Send Us Question form on News page")
    public void clickSubmitButtonOnSendUsQuestionForm() {
        newsPage.waitVisibilityOfElement(DEFAULT_WAITING_TIME, newsPage.getSendQuestionFormHeading());
        newsPage.clickSubmitButton();
    }

    @And("^User checks that the (.+) appears$")
    public void checkErrorMessages(final String expectedErrors) {
        List<String> errorList = Arrays.stream(expectedErrors.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        assertTrue(newsPage.addErrorMessagesListIntoStringList().containsAll(errorList));
    }

    @And("The Name field content {string} deleted")
    public void checkNameFieldContentIsNotDeleted(final String deletionChecking) {
        if (deletionChecking.equals("is not")) {
            assertFalse(newsPage.getNameFieldContent().isEmpty());
        } else assertTrue(newsPage.getNameFieldContent().isEmpty());
    }

    @And("User clicks on Sport button on Home page")
    public void clickSportButton() {
        homePage.moveToSportPage();
        sportPage = pageFactoryManager.getSportPage();
        sportPage.waitForPageLoadComplete(DEFAULT_WAITING_TIME);
    }

    @And("User selects Football menu item on Sport page")
    public void selectFootballMenuItem() {
        sportPage.clickFootballMenuItem();
    }

    @And("User selects Scores And Fixtures menu item on Sport page")
    public void selectScoresAndFixturesMenuItem() {
        sportPage.clickScoresAndFixturesMenuItem();
    }

    @And("User performs searching by championship {string}")
    public void performSearchingByChampionship(final String championship) {
        sportPage.waitVisibilityOfElement(DEFAULT_WAITING_TIME, sportPage.getChampionshipSearchField());
        sportPage.chooseChampionShip(championship);
    }

    @And("User selects month of championship {string}")
    public void selectMonthOfChampionship(final String championshipDate) {
        sportPage.chooseMonthOfChampionship(championshipDate);
        sportPage.waitClickableOfElement(DEFAULT_WAITING_TIME, sportPage.getShowScorersButton());
    }

    @And("User checks that results of the first available game on Sport page equal {string}")
    public void checkResultsOfTheFirstAvailableGameOnSportPage(final String gameResult) {
        sportPage.moveToGameFromTheList();
        assertEquals(sportPage.getChampionshipGameResult(), gameResult);
    }

    @And("User clicks on the first available game link on Sport page")
    public void clickTheFirstAvailableGameOnSportPage() {
        sportPage.navigateToGameDetailPage();
        gamePage = pageFactoryManager.getGamePage();
        gamePage.waitForPageLoadComplete(DEFAULT_WAITING_TIME);
    }

    @And("User check that results of the first available game on Game page equal {string}")
    public void checkResultsOfTheFirstAvailableGameOnGamePageEqualsToResultsOfTheFirstAvailableGameOnSportPage(final String gameResult) {
        gamePage.waitVisibilityOfElement(DEFAULT_WAITING_TIME, gamePage.getGamePageHeader());
        assertEquals(gamePage.getHomeTeamActual() + " " + gamePage.getHomeTeamScoreActual() + " " + gamePage.getAwayTeamScoreActual() + " " + gamePage.getAwayTeamActual(), gameResult);

    }


    @Then("User check that results of the first available game on Game page equal to results of the first available game on Sport page")
    public void checkThatResultsOfTheFirstAvailableGameOnGamePageEqualToResultsOfTheFirstAvailableGameOnSportPage() {
        sportPage.moveToGameFromTheList();
        String results = sportPage.getChampionshipGameResult();
        sportPage.navigateToGameDetailPage();
        gamePage = pageFactoryManager.getGamePage();
        gamePage.waitForPageLoadComplete(DEFAULT_WAITING_TIME);
        gamePage.waitVisibilityOfElement(DEFAULT_WAITING_TIME, gamePage.getGamePageHeader());
        assertEquals(gamePage.getHomeTeamActual() + " " + gamePage.getHomeTeamScoreActual() + " " + gamePage.getAwayTeamScoreActual() + " " + gamePage.getAwayTeamActual(), results);

    }
}




