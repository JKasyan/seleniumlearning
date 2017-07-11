package com.kasyan.test;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class GoogleCalcStepDefinition {

    private WebDriver driver;

    @Before
    public void setup() {
        System.out.println("Setup");
        String operationSystemName = System.getProperty("os.name");
        String driverPath = "src/main/resources/chromedriver";
        if(operationSystemName.contains("Windows")) {
            driverPath += ".exe";
        }
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
    }

    @Given("^User open google$")
    public void iOpenGoogle() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.google.com");
    }

    @When("^User enter \"([^\"]*)\" in search bar$")
    public void iEnterDigits(String terms) {
        System.out.println("terms -> " + terms);
        driver.findElement(By.id("lst-ib")).sendKeys(terms);
        driver.findElement(By.id("_fZl")).click();
    }

    @Then("^User should get \"([^\"]*)\"$")
    public void userShouldGetCorrectResult(String expectedResult) {
        System.out.println("expectedResult -> " + expectedResult);
        String actual = driver.findElement(By.id("cwos")).getText();
        Assert.assertEquals(actual, expectedResult);
        driver.close();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
