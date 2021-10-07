package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SubTotalStep {

    WebDriver driver = null;
    double price, total;


    @Given("^I navigate to shop page$")
    public void i_navigate_to_shop_page() {
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

    @When("^I buy below products$")
    public void i_buy_below_products(DataTable table) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='products ng-scope']")));

        List<List<String>> rows = table.rows(1).asLists(String.class);

        for (List<String> columns : rows) {
            int count = Integer.parseInt(columns.get(0));
            String product = columns.get(1);

            for (int i= 0; i < count; i++){

                if(product.equals("Stuffed Frog")) {
                    driver.findElement(By.cssSelector("li:nth-of-type(2) .btn.btn-success")).click();

                }
                else if(product.equals("Fluffy Bunny")) {
                    driver.findElement(By.cssSelector("li:nth-of-type(4) .btn.btn-success")).click();
                }
                else if(product.equals("Valentine Bear")) {
                    driver.findElement(By.cssSelector("li:nth-of-type(7) .btn.btn-success")).click();
                }
                else
                    break;
            }
        }
    }

    @And("^I navigate to the cart page$")
    public void i_navigate_to_cart_page() {
        driver.findElement(By.cssSelector(".cart-count.ng-binding")).click();
    }

    @Test
    @Then("^I verify the subtotal for each product is correct$")
    public void i_verify_the_subtotal_for_each_product_is_correct(DataTable table) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-checkout.btn-success.ng-scope")));

        sleep(3000);

        List<List<String>> rows = table.rows(1).asLists(String.class); // skip heading row
        for (List<String> columns : rows) {

            int count = Integer.parseInt(columns.get(0));
            String product = columns.get(1);
            price = Double.parseDouble(columns.get(2));
            total = count*price;

            System.out.println(" Product: "+product+" and Quantity: "+count+" and total price is: $"+total);

            if(product.equals("Stuffed Frog")) {
                Assert.assertEquals("$"+Double.toString(total),driver.findElement(By.xpath("//form[@name='form']/table/tbody/tr[1]/td[4]")).getText());
            }
            else if(product.equals("Fluffy Bunny")) {
                Assert.assertEquals("$"+Double.toString(total),driver.findElement(By.xpath("//form[@name='form']/table/tbody/tr[2]/td[4]")).getText());
            }
            else if(product.equals("Valentine Bear")) {
                Assert.assertEquals("$"+Double.toString(total),driver.findElement(By.xpath("//form[@name='form']/table/tbody/tr[3]/td[4]")).getText());
            }
            else
                break;
        }
        driver.close();
    }

    private void sleep(long m) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}