package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("apple");
        int article_count = SearchPageObject.countArticlesInResultList();
        Assert.assertTrue("There are no several articles",article_count > 1);
        SearchPageObject.clearSearchLine();
        SearchPageObject.waitForEmptySearchResultList();
    }
}
