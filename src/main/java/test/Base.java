package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.restassured.ReportPortalRestAssuredLoggingFilter;
import com.epam.reportportal.selenide.ReportPortalSelenideEventListener;
import io.restassured.RestAssured;

public class Base {
    static {
        RestAssured.filters(new ReportPortalRestAssuredLoggingFilter(42, LogLevel.INFO));
//        SelenideLogger.addListener("Report Portal logger", new ReportPortalSelenideEventListener());
    }
}
