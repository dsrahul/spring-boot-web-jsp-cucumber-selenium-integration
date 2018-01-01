package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class SpringHooks {

    //Set test parameters:
    private static ChromeDriverService service;
    private static WebDriver driver;

    @Before
    public static void createAndStartService() throws Throwable {
        driver = Browser.getInstance();
        /*service = new ChromeDriverService.Builder()
                //.usingDriverExecutable(new File("d:\\chromedriver.exe"))
                .usingDriverExecutable(new File("src/test/resources/driver/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");*/

        /*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        String[] listCapability={"--start-maximized","--disable-extensions","--disable-translate"};
        capabilities.setCapability("chrome.switches", listCapability);*/

        /*final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(
                ChromeOptions.CAPABILITY, chromeOptions
        );
        driver = new RemoteWebDriver(service.getUrl(),capabilities);*/
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); //stick it in the report
        }
        //driver.close();
        Browser.close();
    }
    public static WebDriver getDriver() {
        return driver;
    }
}