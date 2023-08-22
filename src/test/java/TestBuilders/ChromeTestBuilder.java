package TestBuilders;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class ChromeTestBuilder {
    //    Setup WebDriver
    public static WebDriver driver;
    public ChromeTestBuilder(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    //    Quit WebDriver
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
