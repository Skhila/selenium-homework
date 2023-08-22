import TestBuilders.ChromeTestBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SwitchToTest extends ChromeTestBuilder {
    @Test
    public void iFrameTest(){
//        Navigate to URL
        driver.get("http://the-internet.herokuapp.com/iframe");

//        Switch to iFrame
        WebElement iFrame = driver.findElement(By.xpath("//*[@id=\"mce_0_ifr\"]"));
        driver.switchTo().frame(iFrame);

//        Locate text Field, Clear current text and write "Here Goes"
        new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"tinymce\"]/p")));
        WebElement textField = driver.findElement(By.xpath("//*[@id=\"tinymce\"]/p"));

        textField.clear();
        textField.sendKeys("Here Goes");

//        Switch back to parent Default frame
        driver.switchTo().defaultContent();

//        Locate and click 'Align center' button
        WebElement alignCenter = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[1]/div[1]/div[2]/div/div[4]/button[2]"));
        alignCenter.click();
    }

    @Test
    public void alertsTest() throws InterruptedException {
//        Navigate to url
        driver.get("https://demoqa.com/alerts");

//        Locate button and click it
        WebElement clickMeButton = driver.findElement(By.xpath("//*[@id=\"alertButton\"]"));
        clickMeButton.click();

//        Switch to alert and accept it
        driver.switchTo().alert().accept();
    }
}
