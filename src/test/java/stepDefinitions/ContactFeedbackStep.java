package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ContactFeedbackStep {

    WebDriver driver = null;

    @Given("^I navigate from home page to contact page$")
    public void i_navigate_from_home_page_to_contact_page() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath+"/src/main/resources/chromedriver");
        driver = new ChromeDriver();

        //maximise browser window
        driver.manage().window().maximize();

        // open home page
        driver.get("http://jupiter.cloud.planittesting.com");

        // Navigate to contact page
        driver.findElement(By.cssSelector("#nav-contact")).click();

        System.out.println("Contact Page is opened");

    }

    @When("^I enter Mandatory Values as \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void I_enter_Mandatory_values_as(String forename, String email, String message, String surname, String telephone) {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("forename")));

        driver.findElement(By.cssSelector("input#forename")).sendKeys(forename);
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.id("message")).sendKeys(message);
        driver.findElement(By.cssSelector("input#surname")).sendKeys(surname);
        driver.findElement(By.cssSelector("input#telephone")).sendKeys(telephone);
    }

    @And("^Click submit button$")
    public void click_submit_button() {
        driver.findElement(By.linkText("Submit")).click();

    }

    @Then("^I validate successful submission message \"([^\"]*)\"$")
    public void I_validate_successful_submission_message(String expectMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("« Back")));

        String actualMessage = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();

        Assert.assertTrue("Actual message does not contains expected message.\n Actual message:" + actualMessage + "\nExpected Message:" + expectMessage,actualMessage.contains(expectMessage));

        driver.quit();
    }

}