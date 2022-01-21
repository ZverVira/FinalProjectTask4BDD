package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GamePage;
import pages.HomePage;
import pages.NewsPage;
import pages.SportPage;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

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

}
