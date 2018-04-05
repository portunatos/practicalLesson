package lessons;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;


public class ChromeAndFirefoxOptions {
    @Test
    public void firefoxOptionsTest() throws InterruptedException {

        //указываем путь к geckodriver
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

        //указываем путь к профайлу
        FirefoxProfile myprofile = new FirefoxProfile(new File("/home/lugovskoy/.mozilla/firefox/eyyc2tg1.default"));

        //создаем экземпляр класса FirefoxOptions
        FirefoxOptions options = new FirefoxOptions();

        //указуем путь к исполняемому файлу
        FirefoxBinary firefoxBinary = new FirefoxBinary(new File("/usr/bin/firefox"));

        //создаем экземпляр класса Proxy
        Proxy proxy = new Proxy();

        //задаем прокси
        proxy.setSslProxy("proxy.pbank.com.ua:8080");

        options
                .setProfile(myprofile)//добавляем в FirefoxOptions наш профайл
                .setHeadless(false)//запуск браузера в headless режиме
                .addPreference("browser.startup.homepage", "https://test-start.privatbank.ua/DesktopGUI/") //задаем стартовую страницу  about:config
                .addPreference("browser.download.dir", "/home")//задаем путь для загрузки файлов
                .setBinary(firefoxBinary)//задаем исполняемый файл для запуска
                .setAcceptInsecureCerts(true)//автоподтверждение при переходе на ресурс с самоподписным сертификатом
                .setProxy(proxy);//задаем прокси

        //создаем экземпляр WebDriver
        WebDriver driver = new FirefoxDriver(options);

        //открываем страницу https://privatbank.ua
        driver.get("https://ukr.net");

    }

    @Test
    public void chromeOptionsTest() throws InterruptedException {

        //указываем путь к geckodriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");

        //создаем экземпляр класса ChromeOptions
        ChromeOptions options = new ChromeOptions();

        //создаем экземпляр класса Proxy
        Proxy proxy = new Proxy();

        //задаем прокси
        proxy.setSslProxy("proxy.pbank.com.ua:8080");
        //proxy.setHttpProxy("proxy.pbank.com.ua:8080");

        options
                .setBinary("/usr/bin/google-chrome")//задаем исполняемый файл для запуска
                .setHeadless(false)//запуск браузера в headless режиме
                .addArguments("start-maximized") //открыть окно браузера на весь экран
                .addArguments("homepage=https://test-start.privatbank.ua/DesktopGUI/")
//                .addArguments("--proxy-server=http://proxy.pbank.com.ua:8080") //ещё один способ задать прокси
//                .addArguments("user-data-dir=/home/lugovskoy/.config/google-chrome/")//задаем путь к репозиторию с профайлами
//                .addArguments("profile-directory=Profile 2")//задаем какой профайл использовать
                .setAcceptInsecureCerts(true)//автоподтверждение при переходе на ресурс с самоподписным сертификатом
                //.setProxy(proxy);//задаем прокси
;
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", "/home/testDirrrrrr");
        chromePrefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", chromePrefs);

        //создаем экземпляр WebDriver
        WebDriver driver = new ChromeDriver(options);

        //открываем страницу https://privatbank.ua
        driver.get("https://ukr.net");

    }
}
