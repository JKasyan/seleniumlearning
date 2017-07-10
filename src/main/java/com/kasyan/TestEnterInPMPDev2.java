package com.kasyan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestEnterInPMPDev2 {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\1\\Desktop\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://pmpdev2-smartbox.cs89.force.com/s/login");

        List<WebElement> inputs = driver.findElements(By.tagName("input"));

        System.out.println("Inputs -> " + inputs.size());
        System.out.println("Buttons -> " + inputs.size());

        WebElement loginInput = inputs.get(0);
        WebElement passwordInput = inputs.get(1);

        loginInput.sendKeys("eugene.kasyas@pexlify.com.pmpdev2.community");
        passwordInput.sendKeys("");

        WebElement loginButton = driver.findElement(By.className("slds-button--neutral"));

        List<WebElement> innerSpans = loginButton.findElements(By.tagName("span"));
        System.out.println("innerSpans -> " + innerSpans.size());

        loginButton.click();


        Thread.sleep(8000);

        driver.quit();
    }
}
