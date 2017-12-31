package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions extends AbstractSpringTests {

    private WebDriver driver = null;
    @Given("^User opens a web browser$")
    public void userOpensAWebBrowser() throws Throwable {
      driver = SpringHooks.getDriver();
    }

    @When("^User navigates to the home page$")
    public void userNavigatesToTheHomePage() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions builder = new Actions(driver);
        driver.get("http:\\localhost:8080");
    }

    @Then("^User is presented with a welcome message$")
    public void userIsPresentedWithAWelcomeMessage() throws Throwable {

        assertEquals("LOGIN", driver.getTitle());
        assertTrue(driver.findElement(By.id("sign-in")).isDisplayed());
    }
}
