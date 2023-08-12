import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommandsTest {
//    Chrome WebDriver setup
    private static WebDriver driver;
    @BeforeMethod
    public static void setupChromeWebDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void inputFieldAndButtonTest() throws InterruptedException {
//        ** Dynamic Controls Task **
        String url = "http://the-internet.herokuapp.com/dynamic_controls";
        driver.get(url);

//        Select Web Elements
        WebElement enableButton = driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));
        WebElement inputField = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input"));

//        Commands
        enableButton.click();
        Thread.sleep(4000);

        System.out.println("Is the input field enabled? >> " + inputField.isEnabled());

        WebElement message = driver.findElement(By.id("message"));
        System.out.println("Is the message (\"It's enabled!\") displayed? >> " + message.isDisplayed());

        System.out.println("Did the button text change from Enable to Disable? >> " + enableButton.getText().equalsIgnoreCase("Disable"));

        inputField.sendKeys("Bootcamp");
        Thread.sleep(2000);
        inputField.clear();

//        ** Drag and Drop Task **
        String dragAndDropUrl = "http://the-internet.herokuapp.com/drag_and_drop";
        driver.navigate().to(dragAndDropUrl);

//        Select Web Elements
        WebElement elementA = driver.findElement(By.id("column-a"));
        WebElement elementB = driver.findElement(By.id("column-b"));

//        Get Elements' locations
        Point locationA = elementA.getLocation();
        Point locationB = elementB.getLocation();

//        Check
        boolean areYsEqual = locationA.y == locationB.y;
        System.out.println("Are the Y coordinates of elements A and B the same? >> " + areYsEqual);


//        Results:

//        Is the input field enabled? >> true
//        Is the message ("It's enabled!") displayed? >> true
//        Did the button text change from Enable to Disable? >> true
//        Are the Y coordinates of elements A and B the same? >> true
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}