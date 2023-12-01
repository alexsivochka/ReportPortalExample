package pages;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.annotations.Step;
import entity.Menu;
import org.openqa.selenium.By;
import utils.IWaitUtils;

import java.util.HashMap;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class NavBarPage implements IWaitUtils {

    public HashMap<String, SelenideElement> navBarElementsMap = new HashMap<>();

    public NavBarPage(){
        navBarElementsMap.put("Навбар", navBar);
        navBarElementsMap.put("Логотип", navBarLogoButton);
        navBarElementsMap.put("Депозиты", navBarDepositsDropDown);
        navBarElementsMap.put("Кредиты", navBarCreditsDropDown);
        navBarElementsMap.put("Карты", navBarPayCardsDropDown);
        navBarElementsMap.put("Платежи", navBarPaymentsAndTransfersDropDown);
        navBarElementsMap.put("Переводы", navBarRemoteBankingDropDown);
        navBarElementsMap.put("Отделения", navBarMapButton);
        navBarElementsMap.put("Прочее", navBarMiscDropDown);
        navBarElementsMap.put("Вход в Приват24", navBarPrivat24DropDown);
        navBarElementsMap.put("Поиск по сайту", navBarSearchButton);
    }

    private ElementsCollection navBarMenus = $$(By.xpath("//ul[contains(@class, ' navbar-nav')]/li"));

    public SelenideElement
            navBar = $("div.navbar.navbar-default"),
            navBarLogoButton = $(By.xpath("//div[@class='navbar-header']")),
            navBarDepositsDropDown = $(By.xpath("//a[text()='Заощадження' or text()='Сбережения']/parent::li")),
            navBarCreditsDropDown = $(By.xpath("//a[text()='Кредити' or text()='Кредиты']/parent::li")),
            navBarPayCardsDropDown = $(By.xpath("//a[text()='Картки' or text()='Карты']/parent::li")),
            navBarPaymentsAndTransfersDropDown = $(By.xpath("//a[text()='Платежі' or text()='Платежи']/parent::li")),
            navBarRemoteBankingDropDown = $(By.xpath("//a[text()='Перекази' or text()='Переводы']/parent::li")),
            navBarMapButton = $(By.xpath("//a[text()='Відділення' or text()='Отделения']/parent::li")),
            navBarMiscDropDown = $(By.xpath("//a[text()='Інше' or text()='Прочее']/parent::li")),
            navBarPrivat24DropDown = $(By.xpath("//a[contains(@href,'privat24.ua')]/parent::li")),
            navBarSearchButton = $(By.xpath("//button[@class='search-open-btn']/parent::li"));

    @Step("открыть меню {menu.levels}")
    public void goTo(Menu menu) {
        navBarMenus.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
        navBar.scrollTo();

        menu.getLevels().forEach(s -> $$(byText(s)).filter(visible).first().click());
    }

    @Step("открыть меню {menu.levels}")
    public void goToMiscMenu(Menu menu) {
        SelenideElement sliderRight = $x("//button[contains(@class,'menu-next')]"),
                        slidersBar = $(By.className("arrow-fiz-menu"));

        navBarMenus.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
        navBar.scrollTo();
        navBarMiscDropDown.shouldBe(visible).hover();

        int counter = 0;
        //Ждем, что hover сработал и меню раскрылось.
        while(!slidersBar.isDisplayed() && counter < 50) {
            if (counter % 10 == 0) {
                //Если Hover не сработал - отведем курсор на соседнее меню и вернем обратно
                navBarPrivat24DropDown.hover();
                sleep(200);
                navBarMiscDropDown.hover();
            }

            if(counter % 25 == 0) {
                refresh();
                waitForJQueryPageLoad();
                sleep(1000);
                navBarMiscDropDown.hover();
            }

            sleep(200);
            ++counter;
        }

        SelenideElement subMenu = $(byText(menu.getLevels().get(1)));

        try {
            //Если подменю не отображается в списке - кликаем стрелку вправо для прокрутки
            if( !subMenu.is(visible) && slidersBar.isDisplayed() ) {
                sliderRight.click();
                sleep(200);
            }
            //Кликаем по пункту меню
            subMenu.shouldBe(visible).click();
            subMenu.should(disappear);
        } catch (Error | Exception e) {
            //Если клик не сработал, переоткроем меню или пролистаем подменю еще раз
            if (navBarMiscDropDown.isDisplayed() && !slidersBar.isDisplayed()) {
                navBarPrivat24DropDown.hover();
                navBarMiscDropDown.hover();
            }
            if (!subMenu.isDisplayed() && sliderRight.isDisplayed()) sliderRight.click(); //Листаем еще раз
            sleep(200);
            subMenu.shouldBe(visible.because("Пункт меню '" + menu.getLevels().get(1) + "' не найден")).click();
        }

    }


    @Step("открыть меню {menu.levels}")
    public void goToMenuWithHover (Menu menu, SelenideElement element) {
        navBarMenus.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(9));
        navBar.scrollTo();
        element.shouldBe(visible).hover();

        try {
            $(byText(menu.getLevels().get(1))).shouldBe(visible).click();
        } catch (Error er) {
            $(By.className("navbar-header")).shouldBe(visible.because("Если Hover не сработал на меню - отведем курсор на начало навбара")).hover();
            sleep(100);
            element.shouldBe(visible).hover();
            sleep(200);
            $(byText(menu.getLevels().get(1))).shouldBe(visible).click();
        }
    }

}