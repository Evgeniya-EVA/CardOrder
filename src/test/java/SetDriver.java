import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetDriver {
    private String osName;
    private String driverPath;

    public SetDriver() {
    }

    public WebDriver getDriver(){
        driverPath = getDriverPath();
        return new ChromeDriver();
    }

    private String getOperationSystem() {
        return System.getProperty("os.name");
    }

    public String getDriverPath(){
        osName = getOperationSystem().toLowerCase();
        String osDriverPath;
        if (osName.contains("win")) {
            osDriverPath = System.setProperty("webdriver.chrome.driver", "./drivers/windows_web_driver/chromedriver.exe");
        } else if (osName.contains("mac") || (osName.contains("os x"))){
            osDriverPath = System.setProperty("webdriver.chrome.driver", "./drivers/mac_web_driver/chromedriver");
        } else osDriverPath = System.setProperty("webdriver.chrome.driver", "./drivers/linux_web_driver/chromedriver");
        return osDriverPath;
    }
}
