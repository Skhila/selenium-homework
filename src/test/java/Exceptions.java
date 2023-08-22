import TestBuilders.HeadlessChromeBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Exceptions extends HeadlessChromeBuilder {
    @Test
    public void exceptionsTest(){
//        Navigate to the URL
        driver.get("https://demoqa.com/alerts ");

//        Locate and click 'Click Me' button
        WebElement clickMe = driver.findElement(By.cssSelector("#timerAlertButton"));
        clickMe.click();

//        Invoke TimeoutException and handle it
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
        }catch (TimeoutException e){
            System.out.printf("*** *** *** *** \nAlert is NOT present within given time!!!\n*** *** *** ***\n%s\n", e.getMessage());
        }

//        Avoid NoAlertPresentException and handle possible exception
        try{
            new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            System.out.println("Accept Successful!");
        }catch (NoAlertPresentException e){
            System.out.printf("*** *** *** ***\nNO Alert Present!!!\n*** *** *** ***\n%s\n", e.getMessage());
        }catch (TimeoutException e){
            System.out.printf("*** *** *** ***\nTimed Out!!!\n*** *** *** ***\n%s\n", e.getMessage());
        }

//        Invoke StaleElementReferenceException and handle it
//        Refresh page to make 'Click me' button stale
        try {
            driver.navigate().refresh();
            clickMe.click();
        }catch(StaleElementReferenceException e){
            System.out.printf("*** *** *** *** \nStale Element Exception caught!!!\n*** *** *** ***\n%s\n", e.getMessage());
        }
    }
}
