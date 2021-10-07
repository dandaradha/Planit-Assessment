package stepDefinitions;

import io.cucumber.datatable.DataTable;
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


import java.util.List;

public class AddItemsToCartStep {

    WebDriver driver = null;


    @Given("^I navigate from home page to shop page$")
    public void i_navigate_from_home_page_to_shop_page() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath+"/src/main/resources/chromedriver");
        driver = new ChromeDriver();
        //maximise browser window
        driver.manage().window().maximize();
        // open home page
        driver.get("http://jupiter.cloud.planittesting.com");
        // Navigate to contact page
        driver.findElement(By.cssSelector("#nav-shop")).click();
        System.out.println("Shop Page is opened");
    }

    @When("^I click buy button$")
    public void i_click_buy_button(DataTable table)  {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='products ng-scope']")));

        List<List<String>> rows = table.rows(1).asLists(String.class);
        for (List<String> columns : rows) {
            int count = Integer.parseInt(columns.get(0));
            String index = columns.get(2);

            for (int i= 0; i < count; i++){
                    driver.findElement(By.cssSelector("li:nth-of-type("+index+") .btn.btn-success")).click();
            }
        }
    }

    @And("I click the cart menu")
    public void i_click_the_cart_menu() {
        driver.findElement(By.cssSelector(".cart-count.ng-binding")).click();
    }

    @Then("I verify the items are in the cart")
    public void i_verify_the_items_are_in_the_cart(DataTable response) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-checkout.btn-success.ng-scope")));
        sleep(3000);

        int items_count=0;

        List<List<String>> rows = response.rows(1).asLists(String.class);
        for (List<String> columns : rows) {
            int quantity = Integer.parseInt(columns.get(0));
            String product = columns.get(1);
            String tr_index = columns.get(2);
            items_count = items_count+quantity;
            Assert.assertEquals(product, driver.findElement(By.xpath("//form[@name='form']/table/tbody/tr["+tr_index+"]/td[1]")).getText());
        }

        String expectedMessage = "There are "+items_count+" items in your cart, you can Checkout or carry on Shopping.";
        Assert.assertEquals(expectedMessage, driver.findElement(By.xpath("//body[@class='ng-scope']/div[@class='container-fluid']//p[@class='cart-msg']")).getText());

        driver.quit();

    }

    private void sleep(long m) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}