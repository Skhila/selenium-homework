import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JSexecutor extends TestBuilder{
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

    @Test
    public void scrollTest(){
//        Navigate to the URL
        driver.get("http://webdriveruniversity.com/Scrolling/index.html");

//        Locate left box and scroll to it using JS Executor
        WebElement leftBox = driver.findElement(By.xpath("//*[@id=\"zone2\"]"));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String scriptForHover = "arguments[0].scrollIntoView();" +
                                "var leftBox = arguments[0];" +
                                "var hoverEvent = new MouseEvent('mouseover');" +
                                "leftBox.dispatchEvent(hoverEvent);";

        javascriptExecutor.executeScript(scriptForHover, leftBox);

//        Validation using Assert
        String expectedTextOfLeftBox = "1 Entries";
        WebElement h1ElementInsideZone2Div = leftBox.findElement(By.cssSelector("#zone2-entries"));
        String actualTextOfLeftBox = javascriptExecutor.executeScript("return arguments[0].textContent", h1ElementInsideZone2Div).toString();

        System.out.println(actualTextOfLeftBox);
        Assert.assertEquals(actualTextOfLeftBox, expectedTextOfLeftBox, "Validation Error!");
//        Print Success message if assertion passes, otherwise it won't be printed
        System.out.println("Validation Successful!");
    }
}
