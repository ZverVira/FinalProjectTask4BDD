package pages;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage {

    protected WebDriver driver;

    @FindBy(xpath = "//div[contains(@class,'tp-modal')]")
    private WebElement registrationModalWindow;

    @FindBy(xpath = "//button[contains(@class,'tp-close tp-active')]")
    private WebElement closeButton;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void implicitWait(long timeToWait) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeToWait));
    }

    public void waitForPageLoadComplete(long timeToWait) {
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitVisibilityOfElement(long timeToWait, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitClickableOfElement(long timeToWait, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void closeRegisterModalWindow() {
        Actions action = new Actions(driver);
        if (registrationModalWindow.isDisplayed()) {
            action.moveToElement(registrationModalWindow).build().perform();
            closeButton.click();
        }
    }
}
