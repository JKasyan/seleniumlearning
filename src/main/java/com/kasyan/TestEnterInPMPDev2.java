package com.kasyan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestEnterInPMPDev2 {

    public static void main(String[] args) throws InterruptedException {

        String operationSystemName = System.getProperty("os.name");
        String pmpPassword = System.getenv("pmp_pass");
        System.out.println("Operation system -> " + operationSystemName);

        String driverPath = "src/main/resources/chromedriver";

        if(operationSystemName.contains("Windows")) {
            driverPath += ".exe";
        }

        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://pmpdev2-smartbox.cs89.force.com/s/login");

        List<WebElement> inputs = driver.findElements(By.tagName("input"));

        WebElement loginInput = inputs.get(0);
        WebElement passwordInput = inputs.get(1);

        loginInput.sendKeys("eugene.kasyas@pexlify.com.pmpdev2.community");
        passwordInput.sendKeys(pmpPassword);

        WebElement loginButton = driver.findElement(By.className("slds-button--neutral"));

        loginButton.click();


        List<WebElement> menuItems = driver.findElements(By.className("menuItemLink"));
        System.out.println(menuItems.size());

        menuItems.get(3).click();

        new WebDriverWait(driver, 10)
                .until(webDriver -> webDriver.findElements(By.tagName("input")).size() > 2);

        WebElement makeBookingInput = driver.findElement(By.tagName("input"));
        makeBookingInput.sendKeys("720675197117");

        waitSpinner(driver);

        WebElement button = driver.findElement(By.className("pmp_make_a_booking_first_button"));
        button.click();

        waitSpinner(driver);

        driver.findElement(By.className("pmp_make_a_booking_booking_date_input")).sendKeys("12-Jul-2017");
        driver.findElement(By.className("pmp_make_a_booking_beneficiary_name_input")).sendKeys("Ben name");
        driver.findElement(By.className("pmp_make_a_booking_beneficiary_email_input")).sendKeys("noreply@gmail.com");
        driver.findElement(By.className("uiInputPhone")).sendKeys("0011223344");

        driver.findElement(By.className("pmp_make_a_booking_second_button ")).click();

        new WebDriverWait(driver, 10)
                .until(wd -> !wd.findElement(By.className("cModal")).getAttribute("class").contains("slds-hide"));

        driver.findElement(By.className("pmp_make_booking_success_message_close_button")).click();

        //2

        driver.findElement(By.className("pmp_search_voucher_number")).sendKeys("720675197117");
        driver.findElement(By.className("pmp_search_voucher_button")).click();
        WebElement searchForVoucherComponent = driver.findElement(By.className("cSearchForVoucher"));

        new WebDriverWait(driver, 10)
                .until(wd -> searchForVoucherComponent.findElement(By.className("cSpinnerQueue"))
                        .getCssValue("visibility").equals("hidden"));

        WebElement table = driver.findElement(By.cssSelector(".cSearchForVoucher table tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        System.out.println("Number rows -> " + rows.size());

        assert rows.size() == 1;

        rows.get(0).findElement(By.tagName("a")).click();

        new WebDriverWait(driver, 10)
                .until(wd -> searchForVoucherComponent.findElement(By.className("cSpinnerQueue"))
                        .getCssValue("visibility").equals("hidden"));

        driver.findElement(By.className("pmp_cancel_booking_button")).click();

        driver.findElement(By.cssSelector(".pmp_update_cancel_voucher .slds-medium-size--6-of-12")).click();

        new WebDriverWait(driver, 10)
                .until(wd -> searchForVoucherComponent
                        .findElement(By.className("pmp_update_cancel_success_message"))
                        .getCssValue("display").equals("block"));

        driver.findElement(By.cssSelector(".pmp_update_cancel_success_message button")).click();

        Thread.sleep(5000);

        driver.quit();
    }

    private static void waitSpinner(WebDriver driver) {
        new WebDriverWait(driver, 10)
                .until(wd -> wd.findElement(By.className("cSpinnerQueue")).getCssValue("visibility").equals("hidden"));
    }
}
