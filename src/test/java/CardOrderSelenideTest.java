import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderSelenideTest {

    private Map<String, SelenideElement> elementList = new HashMap<>();
    private String warningColor = "rgba(255, 92, 92, 1)";

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        elementList.put("name", $("[data-test-id=\"name\"]"));
        elementList.put("phone", $("[data-test-id=\"phone\"]"));
        elementList.put("agreement", $("[data-test-id=\"agreement\"]"));
        elementList.put("button", $("button"));
        elementList.put("success", $("[data-test-id=\"order-success\"]"));
    }

    @AfterEach
    void clearList(){
        elementList.clear();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/cardOrderValidInputData.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void inputFormSelenideTest(String name, String phone){
       elementList.get("name").$(By.name("name")).setValue(name);
       elementList.get("phone").$(By.name("phone")).setValue(phone);
       elementList.get("agreement").click();
       elementList.get("button").click();
       elementList.get("success").shouldHave(Condition.exactText(
               "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void inputNameShouldBeFilledTest(){
        elementList.get("name").$(By.name("name")).setValue("");
        elementList.get("phone").$(By.name("phone")).setValue("+79250000000");
        elementList.get("agreement").click();
        elementList.get("button").click();
        elementList.get("name").$(By.className("input__sub"))
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void inputNameInvalidInputSubTextShouldBeRedTest(){
        elementList.get("name").$(By.name("name")).setValue("");
        elementList.get("phone").$(By.name("phone")).setValue("+79250000000");
        elementList.get("agreement").click();
        elementList.get("button").click();
        elementList.get("name").$(By.className("input__sub"))
                .shouldBe(Condition.cssValue("color", warningColor));
    }

    @Test
    void inputPhoneShouldBeFilledTest(){
        elementList.get("name").$(By.name("name")).setValue("Иван Иванов");
        elementList.get("phone").$(By.name("phone")).setValue("");
        elementList.get("agreement").click();
        elementList.get("button").click();
        elementList.get("phone").$(By.className("input__sub"))
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void inputPhoneInvalidInputSubTextShouldBeRedTest(){
        elementList.get("name").$(By.name("name")).setValue("Иван Иванов");
        elementList.get("phone").$(By.name("phone")).setValue("");
        elementList.get("agreement").click();
        elementList.get("button").click();
        elementList.get("phone").$(By.className("input__sub"))
                .shouldBe(Condition.cssValue("color", warningColor));
    }

    @Test
    void checkBoxShouldBeFilledTest(){
        elementList.get("name").$(By.name("name")).setValue("Иванов Иван");
        elementList.get("phone").$(By.name("phone")).setValue("+79250000000");
        elementList.get("button").click();
        elementList.get("agreement").$(By.className("checkbox__text"))
                .shouldBe(Condition.cssValue("color", warningColor));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cardOrderInvalidNameInputData.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void inputNameInvalidDataWarningTest(String name, String phone){
        elementList.get("name").$(By.name("name")).setValue(name);
        elementList.get("phone").$(By.name("phone")).setValue(phone);
        elementList.get("agreement").click();
        elementList.get("button").click();
        elementList.get("name").$(By.className("input__sub"))
                .shouldHave(Condition.exactText(
                        "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cardOrderInvalidPhoneInputData.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void inputPhoneInvalidDataWarningTest(String name, String phone){
        elementList.get("name").$(By.name("name")).setValue(name);
        elementList.get("phone").$(By.name("phone")).setValue(phone);
        elementList.get("agreement").click();
        elementList.get("button").click();
        elementList.get("phone").$(By.className("input__sub"))
                .shouldHave(
                        Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
