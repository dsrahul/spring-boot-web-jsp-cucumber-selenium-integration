package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class Browser {
    private static WebDriver driver = null;
    private static String os = System.getProperty("os.name");
    private static String chromeDriverPathWIN = "src/test/resources/driver/chromedriver.exe";// can be changed for your PC
    //private static String chromeDriverPathLINUX = System.getProperty("user.home")+"/selenium/chromedriver";
    private static String chromeDriverPathLINUX = System.getProperty("user.home")+"/selenium/chromedriver";
    private static String chromePathLINUX="";
    private static String chromePathWIN="";
    private static String firefoxPathLINUX= System.getProperty("user.home")+"/selenium/ff46/firefox";
    private static String firefoxPathWIN="D:\\firefox.exe";
    private static String firefoxGekoDriverPathLINUX="";
    private static String firefoxGekoDriverPathWIN="D:\\geckodriver.exe";
    private static String IEServerPath = "D:\\IEDriverServer.exe";

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = getABrowser("chrome");
        }
        return driver;
    }

    public static WebDriver getInstance(String browserName) {
        if (driver == null) {
            driver = getABrowser(browserName);
        }
        return driver;
    }

    private Browser() {
    }

    private static WebDriver getABrowser(String nameOfBrowser) {

        // System.out.println("OS>>>" + os);
        if ("firefox".equals(nameOfBrowser)) {
            //running old version(46) firefox, download link => https://ftp.mozilla.org/pub/firefox/releases/46.0/linux-x86_64-EME-free/en-US/
            if (os.contains("Windows")) {
                System.setProperty("webdriver.firefox.bin", firefoxPathWIN);
                //System.setProperty("webdriver.firefox.marionette",firefoxGekoDriverPathWIN);
                // if not working
                System.setProperty("webdriver.gecko.driver", firefoxGekoDriverPathWIN);
            } else {
                System.setProperty("webdriver.firefox.bin",firefoxPathLINUX);
            }
            // System.out.println("PROPERTY >>> " + System.getProperty("webdriver.firefox.bin"));
            return new FirefoxDriver();
//        } else if ("opera".equals(nameOfBrowser)) {
//            return new OperaDriver();
//        } else if ("ie".equals(nameOfBrowser)) {
//            File iedriver = new File(IEServerPath);//todo for your PC
//            System.setProperty("webdriver.ie.driver", iedriver.getAbsolutePath());
//            //-Dwebdriver.ie.driver=physicall
//            return new InternetExplorerDriver();
        } else {
            ChromeDriverService service;
            if (os.contains("Windows")) {
                System.setProperty("webdriver.chrome.driver", chromeDriverPathWIN);
                service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(chromeDriverPathWIN))
                        .usingAnyFreePort()
                        .build();
            } else {
                System.setProperty("webdriver.chrome.driver", chromeDriverPathLINUX);
                service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(chromeDriverPathLINUX))
                        .usingAnyFreePort()
                        .build();
            }
            try {
                service.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            final ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");

            /*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            String[] listCapability={"--start-maximized","--disable-extensions","--disable-translate"};
            capabilities.setCapability("chrome.switches", listCapability);*/

            final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setJavascriptEnabled(true);
            capabilities.setCapability(
                    ChromeOptions.CAPABILITY, chromeOptions
            );
            return new RemoteWebDriver(service.getUrl(), capabilities);
        }
    }

    private static ChromeOptions getLocalChromeOptions() {
        String exeChromium = "<path to your chtome or chromium >chrome.exe";
        ChromeOptions options = new ChromeOptions();
        String driverLocation = null;

        if (os.contains("Windows")) {
            driverLocation = "<path to chromium driver>chromedriver.exe";//windows path
        } else {
            driverLocation = "/usr/bin/google-chrome";//linux path, default, you can change it
        }
        System.setProperty("webdriver.chrome.driver", driverLocation);
        options.setBinary(exeChromium);

        return options;
    }

    public static void close() {
        driver.close();
        driver = null;// to avoid closeing time of browser by JVM
    }

    private static DesiredCapabilities getLocalChrome() {
        String exeChromium = "<path to chromium>chrome.exe";
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browserName", "chrome");
        cap.setCapability("binary", exeChromium);
        return cap;
    }
}