package lessons;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ActionsAndAlerts {
    @Test
    public void actionsTest() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("https://javascript.ru/ui/draganddrop#demo-2");

        WebElement piki = driver.findElement(By.xpath("(//img[@src='/files/dnd/img/piki.gif'])[3]"));
        WebElement bubi = driver.findElement(By.xpath("(//img[@src='/files/dnd/img/bubi.gif'])[3]"));
        WebElement trefi = driver.findElement(By.xpath("(//img[@src='/files/dnd/img/trefi.gif'])[3]"));
        WebElement chervi = driver.findElement(By.xpath("(//img[@src='/files/dnd/img/chervi.gif'])[3]"));
        WebElement target = driver.findElement(By.cssSelector("[src=\"/files/dnd/img/trash.gif\"]"));

        Actions builder = new Actions(driver);

        builder.dragAndDrop(piki, target).pause(1000).build().perform();

        driver.switchTo().alert().accept();

        builder.dragAndDrop(bubi, target).pause(1000).build().perform();

        driver.switchTo().alert().accept();

        builder.dragAndDrop(trefi, target).pause(1000).build().perform();

        driver.switchTo().alert().accept();

        builder.dragAndDrop(chervi, target).pause(1000).build().perform();

        driver.switchTo().alert().accept();

    }
}
