package utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vacancy {

    @JsonProperty("title")
    public String title;

    @JsonProperty("id")
    public String id;
}
