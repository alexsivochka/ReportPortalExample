
package test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.SearchResultsPage;
import utils.ReportPortalListener;

import static com.codeborne.selenide.Selenide.open;

@Listeners({ReportPortalListener.class})
public class ScreenshotTest {

	SearchPage searchPage = new SearchPage();
	SearchResultsPage searchResultsPage = new SearchResultsPage();

	@Test(description = "Поиск на странице Google")
	public void testShouldFailed() {
		open("https://www.google.com.ua/?hl=uk");
		searchPage.searchOnGoogle("selenide");
		searchResultsPage.checkResultsContains("bla");
	}

	@Test(description = "Поиск на странице Google")
	public void testShouldFailedToo() {
		open("https://www.google.com.ua/?hl=uk");
		searchPage.searchOnGoogle("selenide");
		searchResultsPage.checkResultsContains("bla");
	}

	@Test(description = "Поиск на странице Google")
	public void testShouldPass() {
		open("https://www.google.com.ua/?hl=uk");
		searchPage.searchOnGoogle("selenide");
		searchResultsPage.checkResultsContains("Selenide: лаконичные и стабильные UI тесты на Java");
	}

}
