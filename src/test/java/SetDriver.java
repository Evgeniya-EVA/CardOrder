import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.sun.javafx.PlatformUtil;

public class SetDriver {
    private String osName;
    private String driverPath;

    public SetDriver() {
    }

    public WebDriver getDriver(){
        driverPath = getDriverPath();
        return new ChromeDriver();
    }
//
//    private String getOperationSystem() {
//        return System.getProperty("os.name");
//    }

    public String getDriverPath(){
//        osName = getOperationSystem().toLowerCase();
        String osDriverPath;
        if (PlatformUtil.isWindows()) {
            osDriverPath = System.setProperty("webdriver.chrome.driver", "./drivers/windows_web_driver/chromedriver_80.exe");
        } else if (PlatformUtil.isMac()){
            osDriverPath = System.setProperty("webdriver.chrome.driver", "./drivers/mac_web_driver/chromedriver_80");
        } else osDriverPath = System.setProperty("webdriver.chrome.driver", "./drivers/linux_web_driver/chromedriver_80");
        return osDriverPath;
    }
}
