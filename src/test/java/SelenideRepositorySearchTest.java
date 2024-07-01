import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositorySearchTest {

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

        // открыть главную страницу
        open("/");
        // ввести в поле поиска selenide и нажать enter
        $(".placeholder").$(byText("Search or jump to...")).click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        // кликнуть на первый репозиторий из списка найденых
        $$("[data-testid='results-list']").first().$("a").click();
        // проверка: заголовок selenide/selenide
        $("#repository-container-header").$("a[rel='author']").shouldHave(Condition.text("selenide"));
    }
}
