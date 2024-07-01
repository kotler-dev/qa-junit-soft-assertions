import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FirstContributorToSelenideTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "firefox";
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 3000;
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void shouldFindSelenideRepositoryAtTheTop() {

        // arrange, act, assert
        // setTimeout(function() {debugger}, 3000);

        open("/selenide/selenide");
        $("div.Layout-sidebar")
                .$(byText("Contributors"))
                .closest(".BorderGrid-row")
                .$$("ul li")
                .first()
                .scrollTo()
                .hover();

        // .findBy(visible)
        $(".Popover-message")
                .$(byText("Andrei Solntsev"))
                .shouldHave(text("Andrei Solntsev"));

        // screenshot(filename)
    }
}
