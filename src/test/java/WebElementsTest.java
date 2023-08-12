import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebElementsTest {
//    Setup WebDriver
    private static WebDriver driver;

    @BeforeMethod
    public static void setupChromeWebDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void printLastDeleteButtons(){
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

//        Select elements
        WebElement addElement = driver.findElement(By.xpath("//*[@class='example']/button"));

//        Click addElement 3 times
        for(int i = 0; i < 3; i++){
            addElement.click();
        }

//        Print the last Delete button
//          Using findElement()
        WebElement lastDeleteButton = driver.findElement(By.xpath("//*[@id='elements']/button[@class='added-manually'][last()]"));
        System.out.println("The last Delete Button(using findElement(): " + lastDeleteButton);
        System.out.println("Button text: " + lastDeleteButton.getText());

//          Using findElements() and cssSelector
        List<WebElement> deleteButtonsArray = driver.findElements(By.cssSelector("[class^='added']"));
        WebElement lastDeleteButtonFromList = deleteButtonsArray.get(deleteButtonsArray.size() - 1);
        System.out.println("The last Delete Button(using findElements() and cssSelector): " + lastDeleteButtonFromList);
        System.out.println("Button text: " + lastDeleteButtonFromList.getText());


//          Using findElement() and xPath (we can change contains(text(), 'Delete') with text()='Delete')
        WebElement lastDeleteElement2 = driver.findElement(By.xpath("//*[contains(@class, 'manually') and contains(text(), 'Delete')][last()]"));
        System.out.println("The last Delete Button(using findElement() and xPath): " + lastDeleteElement2);
        System.out.println("Button text: " + lastDeleteElement2.getText());

//        Result

//        The last Delete Button(using findElement(): [[ChromeDriver: chrome on WINDOWS (78a8432b30cece667c198ff79a723ecc)] -> xpath: //*[@id='elements']/button[@class='added-manually'][last()]]
//        Button text: Delete
//        The last Delete Button(using findElements() and cssSelector): [[ChromeDriver: chrome on WINDOWS (78a8432b30cece667c198ff79a723ecc)] -> css selector: [class^='added']]
//        Button text: Delete
//        The last Delete Button(using findElement() and xPath): [[ChromeDriver: chrome on WINDOWS (78a8432b30cece667c198ff79a723ecc)] -> xpath: //*[contains(@class, 'manually') and contains(text(), 'Delete')][last()]]
//        Button text: Delete
    }

    @Test
    public void printLoremIpsumValue(){
        driver.get("https://the-internet.herokuapp.com/challenging_dom");

//        Select previous Value
        WebElement previousElement = driver.findElement(By.xpath("//td[contains(text(), 'Apeirian9')]//preceding-sibling::td[1]"));
//        We can get the same element using this relative xPath: //td[contains(text(), 'Apeirian9')]//parent::tr/child::td[1]
        System.out.println("Previous element (Lorem value): " + previousElement.getText());

//        Select next element
        WebElement nextElement = driver.findElement(By.xpath("//td[contains(text(), 'Apeirian9')]//following-sibling::td[1]"));
        System.out.println("Next element (Dolor value): " + nextElement.getText());

//        Result

//        Previous element (Lorem value): Iuvaret9
//        Next element (Dolor value): Adipisci9
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
