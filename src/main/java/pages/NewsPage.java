package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.xpath;

public class NewsPage extends BasePage {

    private static final long DEFAULT_WAITING_TIME = 60;
    private static final String HEADLINE_ARTICLE_TITLE = "//div[contains(@class,'gs-c-promo-body gs-u-display-none gs-u-display-inline-block@m gs-u-mt@xs gs-u-mt0@m gel-1/3@m')]//a[contains(@class,'gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-paragon-bold nw-o-link-split__anchor')]";
    private static final String SECONDARY_ARTICLES_TITLE_LIST = "//h3[@class= 'gs-c-promo-heading__title gel-pica-bold nw-o-link-split__text']";
    private static final String HEADLINE_ARTICLE_TITLE_CATEGORY = "//div[contains(@class,'gs-c-promo-body gs-u-mt@xxs gs-u-mt@m gs-c-promo-body--primary gs-u-mt@xs gs-u-mt@s gs-u-mt@m gs-u-mt@xl gel-1/3@m gel-1/2@xl gel-1/1@xxl')]//a[contains(@class,'gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-paragon-bold gs-u-mt+ nw-o-link-split__anchor')]//h3[@class='gs-c-promo-heading__title gel-paragon-bold gs-u-mt+ nw-o-link-split__text']";
    private static final String CATEGORY_LINK = "//ul[@class='gs-o-list-inline gs-o-list-inline--divided gel-brevier gs-u-mt-']//a[contains(@class,'gs-c-section-link gs-c-section-link--truncate nw-c-section-link nw-o-link nw-o-link--no-visited-state')]";
    private static final String CORONAVIRUS_MENU_ITEM = "//li[@class = 'gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__wide-menuitem-container']//a[@class = 'nw-o-link'][@href = '/news/coronavirus']";
    private static final String YOUR_CORONAVIRUS_STORIES_MENU_ITEM = "//li[@class = 'gs-o-list-ui__item--flush gel-long-primer gs-u-display-block gs-u-float-left nw-c-nav__secondary-menuitem-container']//a[@class = 'nw-o-link'][@href = '/news/have_your_say']";
    private static final String YOUR_QUESTIONS_ANSWERED = "//a[@href= '/news/52143212']";
    private static final String SEND_US_QUESTION_FORM = "//div[@class= 'embed-content-container']";
    private static final String QUESTION_FIELD = "//div[@class = 'long-text-input-container']";
    private static final String NAME_FIELD = "//input[@aria-label= 'Name']";
    private static final String EMAIL_FIELD = "//input[@aria-label= 'Email address']";
    private static final String TERMS_OF_SERVICE = "//input[@type= 'checkbox']";
    private static final String SUBMIT_BUTTON = "//div[@class= 'embed-content-container']//button[@class = 'button']";
    private static final String ERROR_MESSAGE = "//div[@class= 'input-error-message']";
    private static final String SEND_QUESTION_FORM_HEADING = "//h1[@id = 'main-heading']";

    Actions action = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadlineArticleTitleText() {
        return driver.findElement(xpath(HEADLINE_ARTICLE_TITLE)).getText();
    }

    public List<WebElement> getSecondaryArticlesTitlesList() {
        return driver.findElements(xpath(SECONDARY_ARTICLES_TITLE_LIST));
    }

    public List<String> addSecondaryArticlesTitlesListIntoStringList() {
        List<String> secondaryArticlesTitlesListText = new ArrayList<>();
        for (WebElement element : getSecondaryArticlesTitlesList()) {
            secondaryArticlesTitlesListText.add(element.getText());
        }
        return secondaryArticlesTitlesListText;
    }

    public void moveToNewsByCategoryLink() {
        WebElement categoryLink = driver.findElement(xpath(CATEGORY_LINK));
        String categoryLinkText = categoryLink.getAttribute("href");
        driver.navigate().to(categoryLinkText);
    }

    public void clickCoronavirusMenuItem() {
        driver.findElement(xpath(CORONAVIRUS_MENU_ITEM)).click();
    }

    public void clickYourCoronavirusStoriesMenuItem() {
        driver.findElement(xpath(YOUR_CORONAVIRUS_STORIES_MENU_ITEM)).click();
    }

    public WebElement getSendUsQuestionLink() {
        return driver.findElement(xpath(YOUR_QUESTIONS_ANSWERED));
    }

    public void fillQuestionField(String questionText) {
        WebElement questionField = driver.findElement(xpath(QUESTION_FIELD));
        action.sendKeys(questionField, questionText).build().perform();
    }

    public void fillNameField(String nameText) {
        WebElement nameField = driver.findElement(xpath(NAME_FIELD));
        action.sendKeys(nameField, nameText).build().perform();
    }

    public void fillEmailField(String emailText) {
        WebElement emailField = driver.findElement(xpath(EMAIL_FIELD));
        action.sendKeys(emailField, emailText).build().perform();
    }

    public void clickTermsOfServiceCheckbox() {
        driver.findElement(xpath(TERMS_OF_SERVICE)).click();
    }

    public void clickSubmitButton() {
        driver.findElement(xpath(SUBMIT_BUTTON)).click();
    }

    public String getNameFieldContent() {
        return driver.findElement(xpath(NAME_FIELD)).getAttribute("value");
    }

    public List<WebElement> getErrorMessagesList() {
        return driver.findElements(xpath(ERROR_MESSAGE));
    }

    public List<String> addErrorMessagesListIntoStringList() {
        List<String> errorMessagesListText = new ArrayList<>();
        for (WebElement element : getErrorMessagesList()) {
            errorMessagesListText.add(element.getText());
        }
        return errorMessagesListText;
    }

    public WebElement getHeadlineArticleCategory() {
        return driver.findElement(xpath(HEADLINE_ARTICLE_TITLE_CATEGORY));
    }

    public void clickSendUsQuestionLink() {
        js.executeScript("arguments[0].scrollIntoView();", getSendUsQuestionLink());
        implicitWait(DEFAULT_WAITING_TIME);
        getSendUsQuestionLink().click();
    }

    public void moveToSendUsQuestionForm() {
        WebElement sendUsQuestionForm = driver.findElement(xpath(SEND_US_QUESTION_FORM));
        js.executeScript("arguments[0].scrollIntoView();", sendUsQuestionForm);
    }

    public void fillSendUsQuestionForm(String question, String name, String email) {
        fillQuestionField(question);
        fillNameField(name);
        fillEmailField(email);
        clickTermsOfServiceCheckbox();
        clickSubmitButton();
    }

    public WebElement getSendQuestionFormHeading() {
        return driver.findElement(xpath(SEND_QUESTION_FORM_HEADING));
    }
}
