package ui.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MouseHover {

    public static void mouseHover(WebDriver driver, By object)  {
        WebElement element= driver.findElement(object);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void mouseHoverAndClick(WebDriver driver, int iteration, By hoverelem, By clickElem)
    {
        //Locate Elements to hover
        List<WebElement> hoverElements = driver.findElements(hoverelem);

        // Locate the element to click
        List<WebElement> clickElements = driver.findElements(clickElem);

        // Create Actions instance
        Actions actions = new Actions(driver);

        // Perform mouse hover and click
        for(int i=0;i<iteration;i++)
        {
            actions.moveToElement(hoverElements.get(i)).click(clickElements.get(i)).build().perform();
        }

    }

    public static void mouseHoverAndClick(WebDriver driver, int iteration, String hoverelem, String clickElemlocator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));



        // Create Actions instance
        Actions actions = new Actions(driver);

        // Perform mouse hover and click elements
        for(int i=0;i<iteration;i++)
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(hoverelem+"["+(i+1)+"]")));
            actions.moveToElement(driver.findElement(By.xpath(hoverelem+"["+(i+1)+"]"))).click(driver.findElement(By.xpath(clickElemlocator+"["+(i+1)+"]"))).build().perform();

        }
    }
    public static void mouseHoverAndClick(WebDriver driver, By hoverelem, By clickElem)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement hoverElement = wait.until(ExpectedConditions.elementToBeClickable(hoverelem));

        // Locate the element to click
        List<WebElement> clickElements = driver.findElements(clickElem);

        // Create Actions instance
        Actions actions = new Actions(driver);

        // Perform mouse hover and click
        for(int clickElement=0;clickElement<clickElements.size();clickElement++)

        {
            WebElement element=clickElements.get(clickElement);

            actions.moveToElement(hoverElement).click(element).build().perform();
        }

    }

    public static List<String> mouseHoverAndGetListOfNames(WebDriver driver, int iteration , String hoverElem, String basepath, String targetelement)  {

        List<String> listNames=new ArrayList<>();

        // Create Actions object
        Actions actions = new Actions(driver);

        // Perform mousehover
        for(int i=0;i<iteration;i++)
        {
            actions.moveToElement(driver.findElement(By.xpath(hoverElem+"["+(i+1)+"]")));

            listNames.add(driver.findElement(By.xpath(basepath+"["+(i+1)+"]"+targetelement)).getText());
        }
        return listNames;
    }

    public static List<WebElement> mouseHoverAndGetListOfNames(WebDriver driver, By hoverElem, By targetElem) {
        WebElement element=driver.findElement(hoverElem);

        // Create Actions object
        Actions actions = new Actions(driver);

        // Click and hold the element
        actions.clickAndHold(element).pause(3000).build();

        // Hold for 2 seconds
//        Thread.sleep(3000);

        List<WebElement> targetElementNames=driver.findElements(targetElem);

        // Release the mouse button
        actions.release(element).perform();

        return targetElementNames;
    }


}
