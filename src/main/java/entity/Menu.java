package entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@ToString
@Builder
@Getter
public class Menu {

    @Singular
    private List<String> levels;

    String pageUrl;
    String title;
    String group;
}
