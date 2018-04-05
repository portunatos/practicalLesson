package lessons;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Waits {
    @Test
    public void webDriverWaitTest() {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        new WebDriverWait(driver, 2, 200).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().contains("аутентификации");
            }
        });

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5, 1000);

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("login")));

        WebElement fieldLogin = driver.findElement(By.name("login"));

        fieldLogin.sendKeys("mylogin");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertFalse(buttonContinue.isEnabled());

        driver.quit();
    }

    @Test
    public void fluentWaitTestEmpty() {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        Wait fluentWait = new FluentWait(driver);

        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.name("login")));

        WebElement fieldLogin = driver.findElement(By.name("login"));

        fieldLogin.sendKeys("mylogin");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertFalse(buttonContinue.isEnabled());

        driver.quit();
    }

    @Test
    public void fluentWaitTestWithTimeout() {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        Wait fluentWait = new FluentWait(driver).withTimeout(5, TimeUnit.SECONDS);

        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.name("login")));

        WebElement fieldLogin = driver.findElement(By.name("login"));

        fieldLogin.sendKeys("mylogin");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertFalse(buttonContinue.isEnabled());

        driver.quit();
    }

    @Test
    public void fluentWaitTestWithTimeoutWithTimeoutPolling() {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        Wait fluentWait = new FluentWait(driver).withTimeout(5, TimeUnit.SECONDS).pollingEvery(200, TimeUnit.MILLISECONDS);

        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.name("login")));

        WebElement fieldLogin = driver.findElement(By.name("login"));

        fieldLogin.sendKeys("mylogin");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertFalse(buttonContinue.isEnabled());

        driver.quit();
    }

    @Test
    public void fluentWaitTestWithTimeoutWithTimeoutPollingIgnoring() {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        Wait fluentWait = new FluentWait(driver)
                .withTimeout(3, TimeUnit.SECONDS)
                .pollingEvery(600, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.name("login")));

        WebElement fieldLogin = driver.findElement(By.name("login"));

        fieldLogin.sendKeys("mylogin");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertFalse(buttonContinue.isEnabled());

        driver.quit();
    }

    @Test
    public void fluentWaitTestWithTimeoutWithTimeoutPollingIgnoringMessage() {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        Wait fluentWait = new FluentWait(driver)
                .withTimeout(3, TimeUnit.SECONDS)
                .pollingEvery(600, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class, ElementNotVisibleException.class)
                .withMessage("Кривая проверка! Автор ровняй руки!");

        //ищем элемент с несуществующим именем
        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.name("loginb")));

        WebElement fieldLogin = driver.findElement(By.name("login"));

        fieldLogin.sendKeys("mylogin");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertFalse(buttonContinue.isEnabled());

        driver.quit();
    }

    @Test
    public void fluentWaitTestWithTimeoutWithTimeoutPollingMessageIgnoringAll() {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        List<Class <? extends Exception>> exceptionsToIgnore = new ArrayList<Class <? extends Exception>>();
        exceptionsToIgnore.add(NoSuchElementException.class);
        exceptionsToIgnore.add(StaleElementReferenceException.class);
        exceptionsToIgnore.add(ElementNotVisibleException.class);

        Wait fluentWait = new FluentWait(driver)
                .withTimeout(3, TimeUnit.SECONDS)
                .pollingEvery(600, TimeUnit.MILLISECONDS)
                .ignoreAll(exceptionsToIgnore)
                .withMessage("Кривая проверка! Автор ровняй руки!");

        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.name("login")));

        WebElement fieldLogin = driver.findElement(By.name("login"));

        fieldLogin.sendKeys("mylogin");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertFalse(buttonContinue.isEnabled());

        driver.quit();
    }

    @Test
    public void fluentWaitTestOwnExpectedCondition() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://promin.stage.it.loc/ProminShellClient");

        WebElement textFieldHeader = driver.findElement(By.className("header"));

        List<Class <? extends Exception>> exceptionsToIgnore = new ArrayList<Class <? extends Exception>>();
        exceptionsToIgnore.add(NoSuchElementException.class);
        exceptionsToIgnore.add(StaleElementReferenceException.class);
        exceptionsToIgnore.add(ElementNotVisibleException.class);

        Wait<WebElement> newFluentWait = new FluentWait<WebElement>(textFieldHeader)
                .withTimeout(3, TimeUnit.SECONDS)
                .pollingEvery(600, TimeUnit.MILLISECONDS)
                .ignoreAll(exceptionsToIgnore)
                .withMessage("Кривая проверка! Автор ровняй руки!");

        newFluentWait.until(new Function<WebElement, Boolean>() {
            public Boolean apply(WebElement elem) {
                return elem.getText().equals("Единая страница аутентификации");
            }
        });


        WebElement fieldLogin = new FluentWait<WebDriver>(driver)
                .withTimeout(3, TimeUnit.SECONDS)
                .pollingEvery(600, TimeUnit.MILLISECONDS)
                .ignoreAll(exceptionsToIgnore)
                .withMessage("Кривая проверка! Автор ровняй руки!")
                .until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.name("login"));
                    }
                });

        fieldLogin.sendKeys("mylogin");

        WebElement fieldpassword = driver.findElement(By.name("password"));

        fieldpassword.sendKeys("mypassword");

        WebElement buttonContinue = driver.findElement(By.id("firstAuth"));

        Assert.assertFalse(buttonContinue.isEnabled());

        Thread.sleep(3000);

        driver.quit();
    }
}
