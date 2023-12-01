package test;

import entity.Menu;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BLCheckUtils;
import pages.MainPage;
import pages.NavBarPage;

import utils.ReportPortalListener;


@Listeners({ReportPortalListener.class})
public class DataProviderTest extends Base {

    private MainPage mainPage = new MainPage();
    private BLCheckUtils blCheckUtils = new BLCheckUtils();
    private NavBarPage navBarPage = new NavBarPage();

    @DataProvider
    public static Object[][] menusForDepositesUA() {
        return new Menu[][] {
                {Menu.builder().level("Заощадження").level("Процентні ставки за вкладами").title("Депозити в ПриватБанку").build()},
                {Menu.builder().level("Заощадження").level("Фонд гарантування вкладів").title("Фонд гарантування вкладів фізичних осіб").build()},
                {Menu.builder().level("Заощадження").level("Мобільний додаток «Мої вклади»").title("Мобільний додаток «Мої вклади»").build()},
                {Menu.builder().level("Заощадження").level("Мобільний додаток «Скарбничка»").title("Додаток Скарбничка").build()},
                {Menu.builder().level("Заощадження").level("Тарифи за вкладами").title("Тарифи за вкладами").build()},
                {Menu.builder().level("Заощадження").level("Купівля валюти для розміщення на вклад").title("Купівля валюти для розміщення на вклад").build()}


        };
    }

    @Test(
            dataProvider = "menusForDepositesUA",
            description = "Проверка переходов на страницы для меню 'Депозити'",
            groups = {"физ. лица"},
            priority = 20)
    public void test2(Menu menu){
        mainPage.openPage();
        navBarPage.goTo(menu);
        blCheckUtils.checkTitleOfPage(menu.getTitle());
    }

}
