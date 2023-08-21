import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TestBuilder {
    //    Setup WebDriver
    static WebDriver driver;
    public TestBuilder(){
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
