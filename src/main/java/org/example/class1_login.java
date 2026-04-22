package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class class1_login extends Main {

    @Test(priority = 1)
    public void RP01_ValidLogin() {
        System.out.println("Starting RP01_ValidLogin...");
        ensureLoggedOut();
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        WebElement loginNavBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='modal-button-link']")));
        jsClick(loginNavBtn);
        pause(1500);

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailInput.sendKeys("bleutray@gmail.com");
        pause(1000);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("crn10421");
        pause(1000);

        WebElement continueBtn = driver.findElement(By.xpath("//button[text()='Continue']"));
        jsClick(continueBtn);
        pause(4000);

        boolean isLoggedIn = false;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='AccountButton']")));
            isLoggedIn = true;
        } catch (Exception e) {}
    }

    @Test(priority = 2)
    public void RP02_Logout() {
        System.out.println("Starting RP02_Logout...");
        ensureLoggedIn();
        pause(2000);

        WebElement profileMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='AccountButton']")));
        jsClick(profileMenu);
        pause(1500);

        WebElement logoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Logout']")));
        jsClick(logoutButton);
        pause(3000);

        boolean isLoggedOut = false;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='modal-button-link']")));
            isLoggedOut = true;
        } catch (Exception e) {}

        Assert.assertTrue(isLoggedOut, "Logout failed.");
    }

    @Test(priority = 3)
    public void RP03_InvalidPassword() {
        System.out.println("Starting RP03_InvalidPassword...");
        ensureLoggedOut();
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        WebElement loginNavBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='modal-button-link']")));
        jsClick(loginNavBtn);
        pause(1500);

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailInput.sendKeys("bleutray@gmail.com");
        pause(1000);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("FakePassword123");
        pause(1000);

        WebElement continueBtn = driver.findElement(By.xpath("//button[text()='Continue']"));
        jsClick(continueBtn);
        pause(2000);
    }

    @Test(priority = 4)
    public void RP04_NonExistentEmail() {
        System.out.println("Starting RP04_NonExistentEmail...");
        ensureLoggedOut();
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        WebElement loginNavBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='modal-button-link']")));
        jsClick(loginNavBtn);
        pause(1500);

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailInput.sendKeys("cen4072fakeemail@gmail.com");
        pause(1000);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("crn10421");
        pause(1000);

        WebElement continueBtn = driver.findElement(By.xpath("//button[text()='Continue']"));
        jsClick(continueBtn);
        pause(2000);
    }

    @Test(priority = 5)
    public void RP05_ForgotPassword() {
        System.out.println("Starting RP05_ForgotPassword...");
        ensureLoggedOut();
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        WebElement loginNavBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='modal-button-link']")));
        jsClick(loginNavBtn);
        pause(1500);

        WebElement forgotPwdLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Forgot Password')]")));
        jsClick(forgotPwdLink);
        pause(2000);

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailInput.sendKeys("bleutray@gmail.com");
        pause(1000);

        WebElement resetBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Reset Password')]")));
        Assert.assertTrue(resetBtn.isDisplayed() || resetBtn.isEnabled(), "Reset Password button is not present on the modal.");

        ensureLoggedIn();
    }
}
