package TestBuilders;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;

public class HeadlessChromeBuilder {
//    Setup Headless WebDriver
    public static WebDriver driver;
    public HeadlessChromeBuilder() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

//    Quit WebDriver
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
