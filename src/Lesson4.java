import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Lesson4 {
    private AppiumDriver driver;
    final By searchWikiXPath = By.xpath("//*[contains(@text, 'Search Wikipedia')]");
    final By searchSrcTextId = By.id("org.wikipedia:id/search_src_text");


    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\kseniya.zubareva\\Desktop\\automationCourse\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void saveTwoArticlesToMyList()
    {
        final By moreOptionsXpath = By.xpath("//android.widget.ImageView[@content-desc='More options']");
        final By addToListXpath = By.xpath("//*[@class = 'android.widget.LinearLayout']//*[@text='Add to reading list']");
        String search_text = "apple";
        String first_article = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Fruit of the apple tree']";
        String second_article = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'American technology company based in Cupertino, California']";
        String name_of_folder = "Articles about apple";


        waitForElementAndClick (searchWikiXPath,"Cannot find 'Search Wikipedia' input",5);
        waitForElementAndSendKeys (searchSrcTextId,search_text,"Cannot find search input",5);
        waitForElementPresent (By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']"),"Cannot find something by request " + search_text,10);
        waitForElementAndClick (By.xpath(first_article),"Cannot find the article about apple tree",15);

        String title_before_deletion = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),"text","Cannot take an attribute 'text'",15);

        waitForElementPresent (moreOptionsXpath,"Cannot find button 'More options'",10);
        waitForElementAndClick (moreOptionsXpath,"Cannot click button to open article options",15);
        waitForElementPresent (addToListXpath,"Cannot take a list with options for article",15);
        waitForElementAndClick (addToListXpath,"Cannot find option to add article to reading list",15);
        waitForElementAndClick (By.id("org.wikipedia:id/onboarding_button"),"Cannot find 'Got it' button",15);
        waitForElementAndClear (By.id("org.wikipedia:id/text_input"), "Cannot find input for folder name",15);
        waitForElementAndSendKeys (By.id("org.wikipedia:id/text_input"),name_of_folder,"Cannot type a name of the folder",15);
        waitForElementAndClick (By.xpath("//*[@text = 'OK']"),"Cannot find OK button",15);
        waitForElementAndClick (By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),"Cannot find X button",15);

        waitForElementAndClick (searchWikiXPath,"Cannot find 'Search Wikipedia' input",15);
        waitForElementAndSendKeys (searchSrcTextId,search_text,"Cannot find search input",15);
        waitForElementAndClick (By.xpath(second_article),"Cannot find the article about Apple company",15);
        waitForElementPresent (moreOptionsXpath,"Cannot find button 'More options'",10);
        waitForElementAndClick (moreOptionsXpath,"Cannot click button to open article options",15);
        waitForElementPresent (addToListXpath,"Cannot take a list with options for article",15);
        waitForElementAndClick (addToListXpath,"Cannot find option to add article to reading list",15);
        waitForElementAndClick (By.id("org.wikipedia:id/item_title"),"Cannot find a folder of articles",15);
        waitForElementPresent (By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),"Cannot find X button",15);
        waitForElementAndClick (By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),"Cannot tap X button",15);

        waitForElementPresent (By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),"Cannot find a navigation button to My Lists",15);
        waitForElementAndClick (By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),"Cannot tap a navigation button to My Lists",15);
        waitForElementPresent (By.xpath("//*[@text='" + name_of_folder  + "']"),"Cannot find created folder",15);
        waitForElementAndClick (By.xpath("//*[@text='" + name_of_folder  + "']"),"Cannot tap on created folder",15);

        swipeElementToLeft (By.xpath(second_article),"Cannot find a saved article about Apple company");

        String saved_articles_screen = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        int amount_of_articles = getAmountOfElements(By.xpath(saved_articles_screen));
        Assert.assertTrue("No articles were found",amount_of_articles > 0);

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_description'][@text = 'fruit of the apple tree']"),"Cannot find the article about apple tree in My List", 15);
        String title_after_deletion = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),"text","Cannot take an attribute 'text'",15);
        Assert.assertEquals ("The titles" + title_before_deletion + " and " + title_after_deletion + " are different",title_before_deletion,title_after_deletion);
    }

    @Test
    public void titleExists()
    {
        String search_text = "apple";
        String first_article = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Fruit of the apple tree']";

        waitForElementAndClick(searchWikiXPath,"Cannot find 'Search Wikipedia' input",5);
        waitForElementAndSendKeys(searchSrcTextId, search_text,"Cannot find search input", 5);
        waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']"),"Cannot find something by request " + search_text, 10);
        waitForElementAndClick(By.xpath(first_article),"Cannot find the article about apple tree",15);
        assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"),"The article doesn't have a title",15);
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeout_in_seconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeout_in_seconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeout_in_seconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeout_in_seconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear (By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y +element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release().perform();
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementPresent(By by, String error_message, long timeout_in_seconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeout_in_seconds);
        Assert.assertNotNull(
                "Attribute 'text' is empty",
                 element.getAttribute("text")
        );
    }




}
