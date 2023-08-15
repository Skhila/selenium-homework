import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WaitsTest {
    //    Setup WebDriver
    private static WebDriver driver;

    @BeforeMethod
    public static void setupChromeWebDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void testProgressBar() throws InterruptedException {
        //        Navigate to the url
        driver.get("https://demoqa.com/progress-bar");

//        Locate Start Button and Progress Bar
        WebElement startButton = driver.findElement(By.xpath("//*[@id=\"startStopButton\"]"));
        WebElement progressBar = driver.findElement(By.xpath("//*[@id=\"progressBar\"]"));

//        Actions
        startButton.click();
        new WebDriverWait(driver, 22).until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"progressBar\"]"), "100%"));
        System.out.println("Progress Bar percentage: " + progressBar.getText());

//        Result:

//        Progress Bar percentage: 100%
    }

//    Quit WebDriver
    @AfterMethod
    public void tearDown() {
    driver.quit();
}


}
