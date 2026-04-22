package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class class5_professor_profile extends Main {

    @Test(priority = 21)
    public void RP21_NavigateToProfessorProfile() {
        System.out.println("Starting RP21_NavigateToProfessorProfile...");
        ensureLoggedIn();

        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        try {
            WebElement profToggle = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(), 'look up a professor by name')]")));
            jsClick(profToggle);
            pause(1000);
        } catch (Exception e) {}

        // search for professor
        WebElement profInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Professor name']")));
        profInput.sendKeys("Scott Barnhart");
        pause(1000);
        profInput.sendKeys(Keys.ENTER);
        pause(3000);

        // Select Professor Scott Barnhart
        WebElement scottCard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href, '/professor/1428473')]")));
        scrollToElement(scottCard);
        jsClick(scottCard);
        pause(3000);
    }

    @Test(priority = 22)
    public void RP22_SaveProfessor() {
        System.out.println("Starting RP22_SaveProfessor...");

        // bookmark professor
        WebElement saveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@alt='Bookmark' or @data-tip='Save Professor']")));

        scrollToElement(saveBtn);
        jsClick(saveBtn);
        pause(2000);
    }

    @Test(priority = 23)
    public void RP23_FilterReviewsByCourse() {
        System.out.println("Starting RP23_FilterReviewsByCourse...");

        // select course code dropdown menu
        WebElement courseFilterWrapper = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(text(), 'All courses')]/ancestor::div[contains(@class, 'control')] | //div[contains(@class, 'control') and .//input[contains(@aria-describedby, 'placeholder')]]")));

        scrollToElement(courseFilterWrapper);
        courseFilterWrapper.click();
        pause(1000);

        // enter course code
        WebElement reactInput = driver.findElement(By.xpath("//input[@role='combobox' and contains(@id, 'react-select')]"));
        reactInput.sendKeys("FIN3403");
        pause(1000);
        reactInput.sendKeys(Keys.ENTER);
        pause(3000);
    }

    @Test(priority = 24)
    public void RP24_LoadMoreRatings() {
        System.out.println("Starting RP24_LoadMoreRatings...");

        // click "Load More Ratings" 3 times
        for (int i = 1; i <= 3; i++) {
            try {
                WebElement loadMoreBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[contains(text(), 'Load More Ratings')]")));
                scrollToElement(loadMoreBtn);
                jsClick(loadMoreBtn);
                pause(2000);
            } catch (TimeoutException e) {
                break;
            }
        }
    }

    @Test(priority = 25)
    public void RP25_CompareProfessor() {
        System.out.println("Starting RP25_CompareProfessor...");

        WebElement compareBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("compare-professor-btn")));

        scrollToElement(compareBtn);
        jsClick(compareBtn);
        pause(3000);

        // Enter professor name to compare
        WebElement compareInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'CompareProfessorsSearch')]//input[@placeholder='Professor name']")));

        compareInput.sendKeys("John Scott");
        pause(2000);

        // scroll through dropdown menu of professor names
        compareInput.sendKeys(Keys.ARROW_DOWN);
        pause(500);
        compareInput.sendKeys(Keys.ENTER);
        pause(4000);
    }
}
