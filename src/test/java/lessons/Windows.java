package lessons;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Set;

public class Windows {
    @Test
    public void chromeTest() throws InterruptedException {

        //указываем путь к geckodriver
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

        //указываем путь к профайлу
        FirefoxProfile myprofile = new FirefoxProfile(new File("/home/lugovskoy/.mozilla/firefox/eyyc2tg1.default"));

        //создаем экземпляр класса FirefoxOptions
        FirefoxOptions options = new FirefoxOptions();

        //добавляем в FirefoxOptions наш профайл
        options.setProfile(myprofile);

        //создаем экземпляр WebDriver с информацией о профайле
        WebDriver driver = new FirefoxDriver(options);

        //открываем страницу https://privatbank.ua
        driver.get("https://privatbank.ua");

        //получаем ид текущего окна
        String startWinId = driver.getWindowHandle();

        System.out.println("ID стартового окна " + startWinId + "\n");

        //открываем новое окно
        ((JavascriptExecutor)driver).executeScript("window.open()");

        //получаем набор ид открытых окон
        Set<String> currentWindowsSet = driver.getWindowHandles();

        //переключаемся на новое окно
        for(String id : currentWindowsSet) {
            if(!id.equals(startWinId)) {
                //переключаемся на окно по ид
                driver.switchTo().window(id);
            }
        }

        System.out.println("ID текущего окна " + driver.getWindowHandle() + "\n");

        //открываем новое окно
        ((JavascriptExecutor)driver).executeScript("window.open()");

        //получаем набор открытых окон
        currentWindowsSet = driver.getWindowHandles();

        //закрываем все открытые окна, кроме самого первого
        for(String id : currentWindowsSet) {

            //переключаемся на окно по ид
            driver.switchTo().window(id);

            //закрываем окно если его ид не соответствует стартовому
            if(!id.equals(startWinId)) {
                System.out.println(
                        "ID текущего окна "
                        + driver.getWindowHandle()
                        + " не равно ID стартового окна "
                        + startWinId + ", поэтому окно будет закрыто\n"
                );
                driver.close();
            } else {
                System.out.println("Это стартовое окно, его не закрываем\n");
            }
        }

        //переключаемся на стартовое окно
        driver.switchTo().window(startWinId);

        System.out.println("Количество открытых окон " + driver.getWindowHandles().size() + "\n");

        System.out.println("ID текущего окна " + driver.getWindowHandle() + "\n");
    }
}
