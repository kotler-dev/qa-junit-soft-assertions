import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class JUnitSoftAssertionsTest {

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
    void checkJUnit5ExampleInPageSoftAssertionsTest() {

        // открыть главную страницу
        open("/");
        // ввести в поле поиска selenide и нажать enter
        $(".placeholder").$(byText("Search or jump to...")).click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        // кликнуть на первый репозиторий из списка найденных
        $$("[data-testid='results-list']")
                .first()
                .$("a")
                .click();
        // проверка наличия заголовока selenide/selenide
        $("#repository-container-header").$("a[rel='author']").shouldHave(Condition.text("selenide"));
        // перейти в раздел Wiki проекта
        $("#wiki-tab").click();
        // убедиться, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-body").shouldHave(Condition.text("Soft assertions"));
        // открыть страницу SoftAssertions
        $(".markdown-body")
                .$(byText("Soft assertions"))
                .click();
        // проверить, что внутри есть пример кода для JUnit5
        var jUnitFiveExample = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """.trim();
        $(".markdown-body").shouldHave(text(jUnitFiveExample));
    }
}
