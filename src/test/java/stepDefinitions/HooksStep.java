package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class HooksStep {

    @Before(order = 0)
    public void beforeScenario() {
        System.out.println("*************Test scenario Started***********");
    }
    @Before("@feedback")
    public void beforeFeedbackFeature() {
        System.out.println("*************Feedback Testcases Execution Started***********");
    }
    @Before("@cart")
    public void beforeCartScenario() {
        System.out.println("*************Cart Testcases Execution Started***********");

    }
    @Before("@products")
    public void beforeProductScenario() {
        System.out.println("*************Subtotal Testcases Execution Started***********");

    }
    @After("@feedback")
    public void afterFeedbackScenario() {
        System.out.println("*************Feedback Testcases Execution Ended***********");

    }
    @After("@cart")
    public void afterCartFeature() {
        System.out.println("*************Cart Testcases Execution Ended***********");

    }
    @After("@products")
    public void afterProductFeature() {
        System.out.println("*************Subtotal Testcases Execution Ended***********");

    }
    @After(order=0)
    public void afterScenario() {
        System.out.println("*************Test scenario Ended***********");

    }
}
