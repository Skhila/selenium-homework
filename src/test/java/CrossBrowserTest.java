import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class CrossBrowserTest {
     WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else {
            throw new Exception("Browser is not correct");
        }
    }

    @Test
    public void hoverTest(){
//        Navigate to the URL
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");

//        Create Actions instance
        Actions builder = new Actions(driver);

//        Locate item, hover and click on the deleteButton
        WebElement practiceMagic = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[3]"));

        builder.moveToElement(practiceMagic).build().perform();

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

        WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[3]/span/i"));

        javascriptExecutor.executeScript("arguments[0].click();", deleteButton);
        System.out.println("Deleted Successfully!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
