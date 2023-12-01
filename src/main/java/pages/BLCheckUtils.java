package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;

public class BLCheckUtils {

    @Step("Проверка наличия '{elementName}' на странице")
    public void checkThatElementIsPresentOnPage(String elementName, SelenideElement element) {
        element.shouldBe(enabled,visible);
    }

    @Step("Проверка наличия текста '{elementText}' на странице")
    public void checkThatElementWithTextIsPresentOnPage(String elementText, SelenideElement element) {
        element.shouldBe(visible).should(text(elementText));
    }

    @Step("Проверка тайтла страницы после перехода")
    public void checkTitleOfPage(String pageTitle) {
        try{
            int tabCount = WebDriverRunner.getWebDriver().getWindowHandles().size();
            if (tabCount > 1){
                switchTo().window(1);
                $(By.xpath(String.format("//title[contains(text(), '%s')]", pageTitle))).shouldBe(exist);
                WebDriverRunner.getWebDriver().close();
                switchTo().window(0);
            }else {
                $(By.xpath(String.format("//title[contains(text(), '%s')]", pageTitle))).shouldBe(exist);
            }


        } catch (Throwable t) {
            $(withText(pageTitle)).shouldBe(exist);
        }
    }

    @Step("Проверка, что элемент '{elementName}' отображается, но недоступен")
    public void checkDisabledElementOnPage(String elementName, SelenideElement element) {
        element.shouldBe(visible).shouldBe(disabled);
    }

    @Step("Проверка, что элемент '{elementName}' отсутствует")
    public void checkInvisibleElementOnPage(String elementName, SelenideElement element) {
        element.shouldNotBe(visible);
    }

    @Step("Проверка, что URL страницы содержит - {title}")
    public void checkPageUrlHasTitle(String title) {
        int tabCount = WebDriverRunner.getWebDriver().getWindowHandles().size();
        String currentUrl;
        if (tabCount > 1){
            switchTo().window(1);
            currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl()
                    .replaceAll("\\%20"," ")
                    .replaceAll("_"," ")
                    .replaceAll("\\+"," ");
            assertThat(currentUrl).containsSequence(title);
            WebDriverRunner.getWebDriver().close();
            switchTo().window(0);
        }else {
            currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl()
                    .replaceAll("\\%20"," ")
                    .replaceAll("_"," ")
                    .replaceAll("\\+"," ");
            assertThat(currentUrl).containsSequence(title);
        }
    }
}
