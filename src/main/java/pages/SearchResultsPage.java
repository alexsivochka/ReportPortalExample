package pages;

import com.codeborne.selenide.ElementsCollection;
import com.epam.reportportal.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {

    ElementsCollection results = $$("a h3");

    @Step("Результаты поиска содержат - {expectedResult}")
    public void checkResultsContains(String expectedResult){
        results.shouldHave(sizeGreaterThanOrEqual(1)).filterBy(visible)
                .shouldHave(itemWithText(expectedResult));
    }

}
