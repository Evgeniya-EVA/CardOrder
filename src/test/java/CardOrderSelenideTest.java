import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderSelenideTest {

    List<SelenideElement> elementList = new ArrayList<>();
    String warningColor = "rgba(255, 92, 92, 1)";

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        elementList.add($("[data-test-id=\"name\"]"));
        elementList.add($("[data-test-id=\"phone\"]"));
        elementList.add($("[data-test-id=\"agreement\"]"));
        elementList.add($("button"));
        elementList.add($("[data-test-id=\"order-success\"]"));
    }

    @AfterEach
    void clearList(){
        elementList.clear();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/cardOrderValidInputData.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void inputFormSelenideTest(String name, String phone){
       elementList.get(0).$(By.name("name")).setValue(name);
       elementList.get(1).$(By.name("phone")).setValue(phone);
       elementList.get(2).click();
       elementList.get(3).click();
       elementList.get(4).shouldHave(Condition.exactText(
               "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void inputNameShouldBeFilledTest(){
        elementList.get(0).$(By.name("name")).setValue("");
        elementList.get(1).$(By.name("phone")).setValue("+79250000000");
        elementList.get(2).click();
        elementList.get(3).click();
        elementList.get(0).$(By.className("input__sub"))
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void inputNameInvalidInputSubTextShouldBeRedTest(){
        elementList.get(0).$(By.name("name")).setValue("");
        elementList.get(1).$(By.name("phone")).setValue("+79250000000");
        elementList.get(2).click();
        elementList.get(3).click();
        elementList.get(0).$(By.className("input__sub"))
                .shouldBe(Condition.cssValue("color", warningColor));
    }

    @Test
    void inputPhoneShouldBeFilledTest(){
        elementList.get(0).$(By.name("name")).setValue("Иван Иванов");
        elementList.get(1).$(By.name("phone")).setValue("");
        elementList.get(2).click();
        elementList.get(3).click();
        elementList.get(1).$(By.className("input__sub"))
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void inputPhoneInvalidInputSubTextShouldBeRedTest(){
        elementList.get(0).$(By.name("name")).setValue("Иван Иванов");
        elementList.get(1).$(By.name("phone")).setValue("");
        elementList.get(2).click();
        elementList.get(3).click();
        elementList.get(1).$(By.className("input__sub"))
                .shouldBe(Condition.cssValue("color", warningColor));
    }

    @Test
    void checkBoxShouldBeFilledTest(){
        elementList.get(0).$(By.name("name")).setValue("Иванов Иван");
        elementList.get(1).$(By.name("phone")).setValue("+79250000000");
        elementList.get(3).click();
        elementList.get(2).$(By.className("checkbox__text"))
                .shouldBe(Condition.cssValue("color", warningColor));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cardOrderInvalidNameInputData.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void inputNameInvalidDataWarningTest(String name, String phone){
        elementList.get(0).$(By.name("name")).setValue(name);
        elementList.get(1).$(By.name("phone")).setValue(phone);
        elementList.get(2).click();
        elementList.get(3).click();
        elementList.get(0).$(By.className("input__sub"))
                .shouldHave(Condition.exactText(
                        "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cardOrderInvalidPhoneInputData.csv", numLinesToSkip = 1, encoding = "UTF-8")
    void inputPhoneInvalidDataWarningTest(String name, String phone){
        elementList.get(0).$(By.name("name")).setValue(name);
        elementList.get(1).$(By.name("phone")).setValue(phone);
        elementList.get(2).click();
        elementList.get(3).click();
        elementList.get(1).$(By.className("input__sub"))
                .shouldHave(
                        Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
