package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            FOLDER_TITLE = "org.wikipedia:id/item_title";

    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void addFirstArticleToMyList(String name_of_folder)
    {
        this.waitForElementPresent(By.xpath(OPTIONS_BUTTON), "Cannot find button to open article options", 15);
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),"Cannot find tap to open article options",15);
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),"Cannot find option to add article to reading list",15);
        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY),"Cannot find 'Got it' tip overlay",15);
        this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT),"Cannot find input to set name of article folder",15);
        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT),name_of_folder,"Cannot type text into article folder input",15);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON),"Cannot press OK button",15);
    }

    public void addNextArticlesToMyList()
    {
        this.waitForElementPresent(By.xpath(OPTIONS_BUTTON), "Cannot find button to open article options", 15);
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),"Cannot find tap to open article options",15);
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),"Cannot find option to add article to reading list",15);
        this.waitForElementAndClick(By.id(FOLDER_TITLE),"Cannot find a folder of articles",15);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),"Cannot close article, cannot find X button",15);
    }

    public void titleIsExisted()
    {
        this.waitForElementPresent(By.id(TITLE),"The article doesn't full open",15);
        this.assertElementPresent(By.id(TITLE),"The article doesn't have a title",15);
    }
}
