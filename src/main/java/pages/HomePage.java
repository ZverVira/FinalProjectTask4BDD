package pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class HomePage extends BasePage {

    private static final String NEWS_MENU_ITEM = "//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/news')]";
    private static final String SPORT_MENU_ITEM = "//div[@id = 'orb-nav-links']//a[contains(@href,'https://www.bbc.com/sport')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void moveToNewsPage() {
        driver.findElement(xpath(NEWS_MENU_ITEM)).click();
    }

    public void moveToSportPage() {
        driver.findElement(xpath(SPORT_MENU_ITEM)).click();
    }
}
