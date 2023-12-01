package utils;

import com.epam.reportportal.annotations.Step;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestUtils {

    @Step("Получение массива вакансий")
    public static List<Vacancy> getVacancyList(){
        return given()
                .when().get("https://privatbank.ua/work/api/getvacancies?lang=uk&position_key=1")
                .then().extract().body().jsonPath().getList(".", Vacancy.class);
    }

}
