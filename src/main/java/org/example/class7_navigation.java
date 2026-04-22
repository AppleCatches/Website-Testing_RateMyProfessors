package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class class7_navigation extends Main {

    @Test(priority = 31)
    public void RP31_ClickLogoToHome() {
        System.out.println("Starting RP31_ClickLogoToHome...");

        // Start from the user ratings page
        driver.get("https://www.ratemyprofessors.com/account/ratings");
        pause(2000);

        // Click webpage logo
        WebElement logo = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//img[@alt='Logo']")));
        jsClick(logo);
        pause(3000);
    }

    @Test(priority = 32)
    public void RP32_NavigateFindSchool() {
        System.out.println("Starting RP32_NavigateFindSchool...");

        WebElement schoolToggle = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[text()='I want to find a professor at a different school']")));
        jsClick(schoolToggle);
        pause(3000);
    }

    @Test(priority = 33)
    public void RP33_NavigateFindProfessor() {
        System.out.println("Starting RP33_NavigateFindProfessor...");

        WebElement profToggle = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[text()=\"I'd like to look up a professor by name\"]")));
        jsClick(profToggle);
        pause(3000);
    }

    @Test(priority = 34)
    public void RP34_ScrollPageUpDown() {
        System.out.println("Starting RP34_ScrollPageUpDown...");
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll up and down 2 times
        for (int i = 1; i <= 2; i++) {
            // scroll down
            js.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
            pause(2000);

            // scroll up
            js.executeScript("window.scrollTo({ top: 0, behavior: 'smooth' });");
            pause(2000);

            System.out.println("Scrolled page");
        }
    }

    @Test(priority = 35)
    public void RP35_CheckFooterLinks() {
        System.out.println("Starting RP35_CheckFooterLinks...");
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        String originalWindow = driver.getWindowHandle();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // HELP LINK
        WebElement helpLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[text()='Help']")));
        scrollToElement(helpLink);
        jsClick(helpLink);
        pause(2000);

        // Switch to Help tab
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                pause(3000);
                driver.close();
            }
        }
        driver.switchTo().window(originalWindow);
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        // SITE GUIDELINES LINK
        WebElement guidelinesLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[text()='Site Guidelines']")));
        scrollToElement(guidelinesLink);
        jsClick(guidelinesLink);
        pause(2000);

        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                pause(3000);
                driver.close();
            }
        }
        driver.switchTo().window(originalWindow);
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        // DO NOT SELL MY PERSONAL INFORMATION
        WebElement dnsLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(), 'Do Not Sell My Personal Information')]")));
        scrollToElement(dnsLink);
        jsClick(dnsLink);
        pause(3000);

        // Expand Strictly Necessary Cookies
        WebElement strictCookiesBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-controls='ot-desc-id-C0001']")));
        jsClick(strictCookiesBtn);
        pause(1000);

        // Expand Opt Out of Sale of Sharing/Targeted Advertising
        WebElement optOutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-controls='ot-desc-id-OSSTA_BG']")));
        jsClick(optOutBtn);
        pause(1500);

        // Turn on then turn off
        WebElement toggleSwitch = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("span.ot-switch-nob")));
        jsClick(toggleSwitch);
        pause(1000);
        jsClick(toggleSwitch);
        pause(1000);

        // Confirm My Choices
        WebElement confirmBtn = driver.findElement(
                By.xpath("//button[contains(text(), 'Confirm My Choices')]"));
        jsClick(confirmBtn);
        pause(2000);
    }
}
