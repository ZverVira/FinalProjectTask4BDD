package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class SportPage extends BasePage {

    private static final String FOOTBALL_MENU_ITEM = "//li[contains(@class,'sp-c-sport-navigation__item ')]//a[contains(@data-stat-title,'Football')]";
    private static final String SCORES_FIXTURES_MENU_ITEM = "//li[contains(@class,'sp-c-sport-navigation__item ')]//a[contains(@data-stat-title,'Scores & Fixtures')]";
    private static final String CHAMPIONSHIP_SEARCH_FIELD = "//input[contains(@id,'downshift-0-input')]";
    private static final String CHAMPIONSHIP_MENU_ITEM = "//a[contains(@class,'sp-c-search__result-item')]";
    private static final String CHAMPIONSHIP_MONTH = "//li[@class='sp-c-date-picker-timeline__item']//a[contains(@href,'scores-fixtures/";
    private static final String HOME_TEAM_NAME = "//span[contains(@data-reactid,'-wrapper.0.0.0.0.0.0.1')]";
    private static final String AWAY_TEAM_NAME = "//span[contains(@data-reactid,'-wrapper.0.0.0.2.0.0.1')]";
    private static final String HOME_TEAM_SCORE = "//span[contains(@data-reactid,'-wrapper.0.0.0.0.1.0')]";
    private static final String AWAY_TEAM_SCORE = "//span[contains(@data-reactid,'-wrapper.0.0.0.2.1.0')]";
    private static final String GAME_FROM_LIST = "//a[@class = 'sp-c-fixture__block-link']";
    private static final String SHOW_SCORERS_BUTTON = "//button[@class = 'gel-pica-bold qa-show-scorers-button sp-c-football-scores-button sp-c-football-scores-button--show-scorers']";

    Actions action = new Actions(driver);

    public SportPage(WebDriver driver) {
        super(driver);
    }

    public void clickFootballMenuItem() {
        driver.findElement(xpath(FOOTBALL_MENU_ITEM)).click();
    }

    public void clickScoresAndFixturesMenuItem() {
        driver.findElement(xpath(SCORES_FIXTURES_MENU_ITEM)).click();
    }

    public void chooseChampionShip(String searchingChampionship) {
        Actions action = new Actions(driver);
        WebElement searchField = driver.findElement(xpath(CHAMPIONSHIP_SEARCH_FIELD));
        action.sendKeys(searchField, searchingChampionship).build().perform();
        WebElement championshipMenuItem = driver.findElement(xpath(CHAMPIONSHIP_MENU_ITEM));
        action.moveToElement(championshipMenuItem).click().build().perform();
    }

    public void chooseMonthOfChampionship(String yearMonth) {
        driver.findElement(xpath(addDateParameterToXpath(yearMonth))).click();
    }

    public String addDateParameterToXpath(String date) {
        return CHAMPIONSHIP_MONTH + date + "')]";
    }

    public String getHomeTeamActual() {
        return getHomeTeamList().get(0).getText();
    }

    public String getAwayTeamActual() {
        return getAwayTeamList().get(0).getText();
    }

    public String getHomeTeamScoreActual() {
        return getHomeTeamScoreList().get(0).getText();
    }

    public String getAwayTeamScoreActual() {
        return getAwayTeamScoreList().get(0).getText();
    }

    public WebElement getChampionshipSearchField() {
        return driver.findElement(xpath(CHAMPIONSHIP_SEARCH_FIELD));
    }

    public List<WebElement> getGameList() {
        return driver.findElements(xpath(GAME_FROM_LIST));
    }

    public void moveToGameFromTheList() {
        action.moveToElement(getGameList().get(0));
    }

    public void navigateToGameDetailPage() {
        getGameList().get(0).click();
    }

    public WebElement getShowScorersButton() {
        return driver.findElement(xpath(SHOW_SCORERS_BUTTON));
    }

    public String getChampionshipGameResult() {
        return getHomeTeamActual() + " " + getHomeTeamScoreActual() + " " + getAwayTeamScoreActual() + " " + getAwayTeamActual();
    }

    public List<WebElement> getHomeTeamList() {
        return driver.findElements(xpath(HOME_TEAM_NAME));
    }

    public List<WebElement> getHomeTeamScoreList() {
        return driver.findElements(xpath(HOME_TEAM_SCORE));
    }

    public List<WebElement> getAwayTeamList() {
        return driver.findElements(xpath(AWAY_TEAM_NAME));
    }

    public List<WebElement> getAwayTeamScoreList() {
        return driver.findElements(xpath(AWAY_TEAM_SCORE));
    }
}
