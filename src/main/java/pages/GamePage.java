package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.xpath;

public class GamePage extends BasePage {

    private static final String HOME_TEAM_NAME_TEAM_PAGE = "//span[contains(@data-reactid,'0.0.1.0.0.0.0.1')]";
    private static final String AWAY_TEAM_NAME_TEAM_PAGE = "//span[contains(@data-reactid,'0.0.1.0.2.0.0.1')]";
    private static final String HOME_TEAM_SCORE_TEAM_PAGE = "//span[contains(@data-reactid,'0.0.1.0.0.1.0')]";
    private static final String AWAY_TEAM_SCORE_TEAM_PAGE = "//span[contains(@data-reactid,'0.0.1.0.2.1.0')]";
    private static final String GAME_PAGE_HEADER = "//div[contains(@class, 'event-header sp-c-match-overview-header sp-c-match-overview-header--football')]";


    public GamePage(WebDriver driver) {
        super(driver);
    }

    public String getHomeTeamActual() { return driver.findElement(xpath(HOME_TEAM_NAME_TEAM_PAGE)).getText(); }

    public String getAwayTeamActual() {
        return driver.findElement(xpath(AWAY_TEAM_NAME_TEAM_PAGE)).getText();
    }

    public String getHomeTeamScoreActual() {
        return driver.findElement(xpath(HOME_TEAM_SCORE_TEAM_PAGE)).getText();
    }

    public String getAwayTeamScoreActual() {
        return driver.findElement(xpath(AWAY_TEAM_SCORE_TEAM_PAGE)).getText();
    }

    public WebElement getGamePageHeader() {
        return driver.findElement(xpath(GAME_PAGE_HEADER));
    }
}
