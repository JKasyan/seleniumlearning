package com.kasyan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestEnterInPMPDev2 {

    public static void main(String[] args) throws InterruptedException {

        String operationSystemName = System.getProperty("os.name");
        String pmpPassword = System.getenv("pmp_pass");
        System.out.println("Operation system -> " + operationSystemName);

        String driverPath = "src/main/resources/chromedriver";

        if(operationSystemName.equals("Windows")) {
            driverPath += ".exe";
        }

        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://pmpdev2-smartbox.cs89.force.com/s/login");

        List<WebElement> inputs = driver.findElements(By.tagName("input"));

        System.out.println("Inputs -> " + inputs.size());
        System.out.println("Buttons -> " + inputs.size());

        WebElement loginInput = inputs.get(0);
        WebElement passwordInput = inputs.get(1);

        loginInput.sendKeys("eugene.kasyas@pexlify.com.pmpdev2.community");
        passwordInput.sendKeys(pmpPassword);

        WebElement loginButton = driver.findElement(By.className("slds-button--neutral"));

        List<WebElement> innerSpans = loginButton.findElements(By.tagName("span"));
        System.out.println("innerSpans -> " + innerSpans.size());

        loginButton.click();


        List<WebElement> menuItems = driver.findElements(By.className("menuItemLink"));
        System.out.println(menuItems.size());

        menuItems.get(3).click();

        WebElement makeBookingInput = driver.findElement(By.tagName("input"));
        makeBookingInput.sendKeys("123456789");

        Thread.sleep(10000);

        driver.quit();
    }
}
