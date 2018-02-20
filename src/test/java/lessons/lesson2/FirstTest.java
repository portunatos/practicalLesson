package lessons.lesson2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstTest {
    @Test
    public void firstTest() {
        System.setProperty("webdriver.chrome.driver", "/home/lugovskoy/IdeaProjects/practicalLessons/src/test/resources/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        WebElement fieldLogin = driver.findElement(By.name("login"));

        fieldLogin.sendKeys("mylogin");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertFalse(buttonContinue.isEnabled());

        driver.quit();

    }

    @Test
    public void secondTest() {
        System.setProperty("webdriver.gecko.driver", "/home/lugovskoy/IdeaProjects/practicalLessons/src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        WebElement fieldLogin = driver.findElement(By.name("login"));

        fieldLogin.sendKeys("aa123456ggg");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertTrue(buttonContinue.isEnabled());

        driver.quit();

    }
}
