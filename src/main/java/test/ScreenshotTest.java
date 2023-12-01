
package test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.SearchResultsPage;
import utils.ReportPortalListener;
import utils.RestUtils;
import utils.Vacancy;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

@Listeners({ReportPortalListener.class})
public class ScreenshotTest extends Base {

    SearchPage searchPage = new SearchPage();
    SearchResultsPage searchResultsPage = new SearchResultsPage();

    @Test(description = "Поиск на странице Google")
    public void testShouldFailed() {
        open("https://www.google.com.ua/?hl=uk");
        searchPage.searchOnGoogle("selenide");
        searchResultsPage.checkResultsContains("bla");
    }

    @Test(description = "Depends", dependsOnMethods = {"testShouldFailed"})
    public void dependTest() {
        open("https://www.google.com.ua/?hl=uk");
        searchPage.searchOnGoogle("selenide");
        searchResultsPage.checkResultsContains("bla");
    }

    @Test(description = "Поиск на странице Google")
    public void testLogRest() {
        List<Vacancy> vacancies = RestUtils.getVacancyList();

    }

    @Test(description = "Поиск на странице Google")
    public void testShouldPass() {
        open("https://www.google.com.ua/?hl=uk");
        searchPage.searchOnGoogle("selenide");
        searchResultsPage.checkResultsContains("Selenide: лаконичные и стабильные UI тесты на Java");
    }

}
