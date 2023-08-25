import TestBuilders.ChromeTestBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

public class AutoComplete extends ChromeTestBuilder{

    @Test
    public void searchTest(){
//        Navigate to the URL
        driver.get("https://www.google.com/");

//        Type 'Automation' in search box
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));
        searchBox.sendKeys("Automation");

        new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"Alh6id\"]/div[1]/div/ul/li")));

//        Locate and select all the suggestions
        List<WebElement> suggestions = driver.findElements(By.xpath("//*[@id=\"Alh6id\"]/div[1]/div/ul/li"));
        int suggestionCount = suggestions.size();
        WebElement lastSuggestion = suggestions.get(suggestionCount-1);
        System.out.printf("The last suggestion is: \"%s\"" ,lastSuggestion.getText());
        lastSuggestion.click();
    }
}
