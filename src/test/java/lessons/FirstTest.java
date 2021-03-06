package lessons;

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
    public void chromeTest() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);


        driver.manage().timeouts().setScriptTimeout(3000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.MILLISECONDS);

        WebElement fieldLogin = driver.findElement(By.name("login"));

        fieldLogin.sendKeys("mylogin");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertFalse(buttonContinue.isEnabled());

        driver.quit();

    }

    @Test
    public void firefoxTest() {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

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
