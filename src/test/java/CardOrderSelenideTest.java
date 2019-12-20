import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderSelenideTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/cardOrderValidInputData.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void inputFormSelenideTest(String name, String phone){
        open("http://localhost:9999");
        $("[data-test-id=\"name\"]").$(By.name("name")).setValue(name);
        $("[data-test-id=\"phone\"]").$(By.name("phone")).setValue(phone);
        $("[data-test-id=\"agreement\"]").click();
        $("button").click();
        $("[data-test-id=\"order-success\"]")
                .shouldHave(Condition.exactText(
                        "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."
                ));
    }

    @Test
    void inputNameShouldBeFilledTest(){
        open("http://localhost:9999");
        $("[data-test-id=\"name\"]").$(By.name("name")).setValue("");
        $("[data-test-id=\"phone\"]").$(By.name("phone")).setValue("+79250000000");
        $("[data-test-id=\"agreement\"]").click();
        $("button").click();
        $("[data-test-id=\"name\"]")
                .$(By.className("input__sub"))
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void inputNameInvalidInputSubTextShouldBeRedTest(){
        open("http://localhost:9999");
        $("[data-test-id=\"name\"]").$(By.name("name")).setValue("");
        $("[data-test-id=\"phone\"]").$(By.name("phone")).setValue("+79250000000");
        $("[data-test-id=\"agreement\"]").click();
        $("button").click();
        $("[data-test-id=\"name\"]")
                .$(By.className("input__sub"))
                .shouldBe(Condition.cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void inputPhoneShouldBeFilledTest(){
        open("http://localhost:9999");
        $("[data-test-id=\"name\"]").$(By.name("name")).setValue("Иван Иванов");
        $("[data-test-id=\"phone\"]").$(By.name("phone")).setValue("");
        $("[data-test-id=\"agreement\"]").click();
        $("button").click();
        $("[data-test-id=\"phone\"]")
                .$(By.className("input__sub"))
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void inputPhoneInvalidInputSubTextShouldBeRedTest(){
        open("http://localhost:9999");
        $("[data-test-id=\"name\"]").$(By.name("name")).setValue("Иван Иванов");
        $("[data-test-id=\"phone\"]").$(By.name("phone")).setValue("");
        $("[data-test-id=\"agreement\"]").click();
        $("button").click();
        $("[data-test-id=\"phone\"]")
                .$(By.className("input__sub"))
                .shouldBe(Condition.cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cardOrderInvalidNameInputData.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void inputNameInvalidDataWarningTest(String name, String phone){
        open("http://localhost:9999");
        $("[data-test-id=\"name\"]").$(By.name("name")).setValue(name);
        $("[data-test-id=\"phone\"]").$(By.name("phone")).setValue(phone);
        $("[data-test-id=\"agreement\"]").click();
        $("button").click();
        $("[data-test-id=\"name\"]")
                .$(By.className("input__sub"))
                .shouldHave(
                        Condition.exactText(
                                "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."
                        ));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cardOrderInvalidPhoneInputData.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void inputPhoneInvalidDataWarningTest(String name, String phone){
        open("http://localhost:9999");
        $("[data-test-id=\"name\"]").$(By.name("name")).setValue(name);
        $("[data-test-id=\"phone\"]").$(By.name("phone")).setValue(phone);
        $("[data-test-id=\"agreement\"]").click();
        $("button").click();
        $("[data-test-id=\"phone\"]")
                .$(By.className("input__sub"))
                .shouldHave(Condition.exactText(
                        "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."
                ));
    }


}
