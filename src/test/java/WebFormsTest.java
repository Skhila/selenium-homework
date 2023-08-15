import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.MemoryAddress;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebFormsTest {
    //    Setup WebDriver
    private static WebDriver driver;

    @BeforeMethod
    public static void setupChromeWebDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

//        Navigate to the url (All the tests require the same url, so I've decided to put it here)
        driver.get("https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
    }

    @Test
    public void dropDownTest(){
//        Locate DropDown menu
        Select dropDownMenu = new Select(driver.findElement(By.xpath("//*[@id=\"dropdowm-menu-1\"]")));

//        Select desired programming language and check if it was selected correctly
        dropDownMenu.selectByValue("python");
        System.out.println("Selected option: " + dropDownMenu.getFirstSelectedOption().getText());
    }

    @Test
    public void checkboxTest(){
//        Locate and click on checkboxes
        List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id = 'checkboxes']/label/input"));
        for (WebElement checkbox: checkboxes) {
            if(!checkbox.isSelected()) checkbox.click();
        }

//        Check if all the checkboxes are checked
        int selectedBoxes = 0;
        for(WebElement checkbox: checkboxes) {
            if(checkbox.isSelected()) selectedBoxes++;
        }
        System.out.println("Number of checked checkboxes: " + selectedBoxes);
    }

    @Test
    public void radioButtonTest(){
//        Locate and click on the yellow radioButton
        WebElement yellowRadioButton = driver.findElement(By.cssSelector("input[value = 'yellow']"));
        yellowRadioButton.click();

//        Check if the yellow radioButton is selected
        System.out.println("Is the Yellow radio button selected? >> " + yellowRadioButton.isSelected());
    }

    @Test
    public void selectedAndDisabledTest(){
//        Locate dropDown and check if the Orange option is disabled
        Select dropDown = new Select(driver.findElement(By.xpath("//*[@id=\"fruit-selects\"]")));

//        Create a List of options and check if the orange is disabled
        List<WebElement> dropDownOptions = dropDown.getOptions();

        for (WebElement option : dropDownOptions) {
            if(!option.isEnabled()) System.out.printf("Option \"%s\" is Disabled \n", option.getText());
        }
    }

    //    Quit WebDriver
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
