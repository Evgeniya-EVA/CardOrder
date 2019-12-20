import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOrderTest {

    WebDriver driver;



    @BeforeAll
    static void setDriverPath(){
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
    }

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
   
    @Test
    void inputFieldsSeleniumTest(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=\"name\"]"))
                .findElement(By.className("input__control")).sendKeys("Иванов Василий");
        driver.findElement(By.cssSelector("[data-test-id=\"phone\"]"))
                .findElement(By.className("input__control")).sendKeys("+79250000000");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();
        assertEquals(
                "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",
                text.trim());
    }
}
