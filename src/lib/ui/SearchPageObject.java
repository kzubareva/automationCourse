package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT =  "//*[contains(@text, 'Search…')]",
            SEARCH_LINE = "org.wikipedia:id/search_src_text",
            SEARCH_RESULT_LIST = "//*[@resource-id = 'org.wikipedia:id/search_results_list']",
            SEARCH_RESULT_ITEM_CONTEINER = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']";

    /*TEMPLATES METHOD*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHOD*/


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking init element");
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,"Cannot find and type into search input", 5);
    }

    public void clearSearchLine()
    {
        this.waitForElementAndClear(By.id(SEARCH_LINE),"Cannot find and clear search line",5);
    }

    public void waitForEmptySearchResultList()
    {
        this.waitForElementNotPresent(By.xpath(SEARCH_RESULT_LIST),"Search result is not empty",5);
    }

    public int countArticlesInResultList()
    {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_LIST),"Cannot count articles",10);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ITEM_CONTEINER));
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }
}
