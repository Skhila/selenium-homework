import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class WebTablesTest {
    //    Setup WebDriver
    private static WebDriver driver;

    @BeforeMethod
    public static void setupChromeWebDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void tablesTest(){
//        Navigate to url
        driver.get("https://techcanvass.com/Examples/webtable.html");

//        Locate table
        WebElement webTable = driver.findElement(By.xpath("//*[@id=\"t01\"]"));

//        Locate table rows and columns and make lists
        List<WebElement> rows = webTable.findElements(By.tagName("tr"));
        List<WebElement> columns = rows.get(0).findElements(By.tagName("th"));

        int rowSize = rows.size();
        int columnSize = columns.size();

//        Create HashMap to store cars' names and prices
        HashMap<String, String> cars = new HashMap<>();

//        Iterate through rows and columns, store information into cars hashMap
        for(int rowNum = 2; rowNum <= rowSize; rowNum++){
            String carName = "";
            String carPrice = "";
            for(int colNum = 1; colNum <= columnSize; colNum++){
                if(colNum == 1){
                    carName = driver.findElement(By.xpath("//*[@id=\"t01\"]/tbody/tr[" + rowNum + "]/td[" + colNum + "]")).getText();
                }
                if (colNum == columnSize){
                    carPrice = driver.findElement(By.xpath("//*[@id=\"t01\"]/tbody/tr[" + rowNum + "]/td[" + colNum + "]")).getText();
                    cars.put(carName, carPrice);
                }
            }
        }

//      Final results
        for (String key : cars.keySet()){
            if (key.equalsIgnoreCase("honda")) System.out.print("Our Favorite 🤞👉 ");
            System.out.printf("Make: %s, Price: %s₹ \n", key, cars.get(key));
        }

//      Result:

//        Make: Suzuki, Price: 8,00,000₹
//        Make: Hyundai, Price: 12,00,000₹
//        Our Favorite 🤞👉 Make: Honda, Price: 6,00,000₹
    }

    //    Quit WebDriver
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
