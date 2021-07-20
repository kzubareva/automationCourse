package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    @Test
    public void testSaveTwoArticlesToMyList()
    {
        //steps for saving of the first article
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("apple");
        SearchPageObject.clickByArticleWithSubstring("Fruit of the apple tree");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Articles about apples";
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        //steps for saving of the second article
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("apple");
        SearchPageObject.clickByArticleWithSubstring("American technology company based in Cupertino, California");
        ArticlePageObject.waitForTitleElement();
        String title_before_deletion = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addNextArticlesToMyList();
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);

        //check that the second article is exist
        int amount_of_articles = MyListPageObject.amountOfLostArticles();
        Assert.assertTrue("The list of saved article is empty",amount_of_articles > 0);

        SearchPageObject.clickByArticleWithSubstring("American technology company based in Cupertino, California");
        String title_after_deletion = ArticlePageObject.getArticleTitle();
        Assert.assertEquals ("The titles" + title_before_deletion + " and " + title_after_deletion + " are different", title_before_deletion, title_after_deletion);
    }

    @Test
    public void testTitleExists()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("apple");
        SearchPageObject.clickByArticleWithSubstring("Fruit of the apple tree");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.titleIsExisted();

    }
}
