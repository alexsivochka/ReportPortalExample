package pages;


import com.codeborne.selenide.SelenideElement;

import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.By;
import utils.IWaitUtils;

import java.util.HashMap;


import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class MainPage implements IWaitUtils {

    public HashMap<String, SelenideElement> mainPageBlocksMap = new HashMap<>();
    public MainPage(){
        baseUrl = "https://privatbank.ua";
        mainPageBlocksMap.put("Баннер", banner);
        mainPageBlocksMap.put("Быстро оплатить", fastPayBlock);
        mainPageBlocksMap.put("Новости", newsBlock);
        mainPageBlocksMap.put("Благотворительность", charityBlock);
        mainPageBlocksMap.put("Проекты Банка", projectsBlock);
        mainPageBlocksMap.put("Мобильные приложения", mobAppsBlock);
        mainPageBlocksMap.put("Статистика", statisticsBlock);
        mainPageBlocksMap.put("Курсы валют", courses);
        mainPageBlocksMap.put("Конвертер", converter);
        mainPageBlocksMap.put("Футер сайта", footer);
    }

    private SelenideElement
        banner = $(By.id("indexCarousel")),
        fastPayBlock = $(By.cssSelector("article.clearfix")),
        newsBlock = $(By.cssSelector(".news_index")),
        charityBlock = $(By.cssSelector(".charity")),
        projectsBlock = $(By.cssSelector(".ptivatbank-project")),
        mobAppsBlock = $(By.cssSelector(".mobile-apps")),
        statisticsBlock = $(By.cssSelector(".statistics")),
        courses = $(By.cssSelector(".courses")),
        converter = $(By.cssSelector(".converter")),
        footer = $(By.cssSelector(".footer"));

    @Step("Откроем страницу {subUrl}")
    public void openPage(String subUrl){
        open(subUrl);
    }

    @Step("Откроем главную страницу pb.ua")
    public void openPage(){
        open(baseUrl);
        waitForJQueryPageLoad();
        acceptCookie();
    }

    @Step("Подтвердить использование куки")
    public static void acceptCookie(){
        SelenideElement acceptCookieButton = $("a.cookies-btn.btn-success");
        sleep(500);
        if (acceptCookieButton.isDisplayed()){
            acceptCookieButton.click();
        }
    }

    private void acceptCookieMsg(){
        if($(By.className("cookie-message")).exists())
            $x(".//*[@class='cookie-message'] //a[contains(@class,'close') or contains(text(),'Ок')]").click();
    }

    @Step("Закроем окно браузера")
    public void closePage(){
        closeWindow();
    }

}
