package utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

public interface IWaitUtils {

    default void waitForJQueryPageLoad(){
        String state = executeJavaScript("return document.readyState");
        int counter = 0;
        while ( !"complete".equalsIgnoreCase(state) && counter < 100 ) {
            state = executeJavaScript("return document.readyState");
            if (state.equalsIgnoreCase("undefined")) break;
            counter++;
            sleep(200);
        }
    }

}
