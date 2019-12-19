import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOrderTest {

    WebDriver driver;

    @BeforeEach
    void setDriverPath(){
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
    }

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
    }

    @Test
    void inputFieldsTest() throws InterruptedException {
        driver.get("http://localhost:9999");
       // List<WebElement> form = driver.findElements(By.cssSelector("[data_test_id=input__control]"));
        driver.findElement(By.cssSelector("[data_test_id=name]")).findElement(By.className("input__control")).sendKeys("Иванов Василий");
        driver.findElement(By.cssSelector("[data_test_id=phone]")).findElement(By.className("input__control")).sendKeys("+79250000000");
        driver.findElement(By.cssSelector("[data_test_id=agreement]")).findElement(By.className("checkbox__control")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data_test_id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
        Thread.sleep(5000);



    }
}
