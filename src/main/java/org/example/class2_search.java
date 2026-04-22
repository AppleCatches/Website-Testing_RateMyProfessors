package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class class2_search extends Main {

    @Test(priority = 6)
    public void RP06_SearchNonExistentSchool() {
        System.out.println("Starting RP06_SearchNonExistentSchool...");
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        // Select "I want to find a professor at a different school"
        try {
            WebElement diffSchool = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[text()='I want to find a professor at a different school']")));
            jsClick(diffSchool);
            pause(1500);
        } catch (Exception e) {}

        // Input fake school
        WebElement schoolInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Your school']")));
        schoolInput.sendKeys("Magic Academics");
        pause(1500);

        schoolInput.sendKeys(Keys.ENTER);
        pause(3000);

        String pageText = driver.getPageSource().toLowerCase();
        boolean noResultsText = pageText.contains("no school") || pageText.contains("0 school") || pageText.contains("no result");
        Assert.assertTrue(noResultsText, "Expected 'No results' message was not displayed for fake school.");
    }

    @Test(priority = 7)
    public void RP07_SearchSchoolByName() {
        System.out.println("Starting RP07_SearchSchoolByName...");

        // Return to Home Page
        try {
            WebElement logo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Logo']")));
            jsClick(logo);
            pause(2000);
        } catch (Exception e) {
            driver.get("https://www.ratemyprofessors.com/");
            pause(2000);
        }

        // Select "I want to find a professor at a different school"
        try {
            WebElement diffSchool = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[text()='I want to find a professor at a different school']")));
            jsClick(diffSchool);
            pause(1500);
        } catch (Exception e) {}

        WebElement schoolInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Your school']")));

        // Clear the text field
        schoolInput.sendKeys(Keys.COMMAND, "a");
        schoolInput.sendKeys(Keys.BACK_SPACE);
        pause(500);

        // Input Florida Atlantic University
        schoolInput.sendKeys("Florida Atlantic University");
        pause(1500);

        schoolInput.sendKeys(Keys.ENTER);
        pause(3000);

        // select Florida Atlantic University school card
        WebElement fauCard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@aria-label='Link to school page for Florida Atlantic University']")));
        jsClick(fauCard);
        pause(4000); // Wait for school's page to load

        Assert.assertTrue(driver.getCurrentUrl().contains("school"), "Failed to navigate to the School page.");
    }

    @Test(priority = 8)
    public void RP08_SearchProfessorByName() {
        System.out.println("Starting RP08_SearchProfessorByName...");

        // Click "View all Professors"
        WebElement viewAllBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(), 'View all Professors')]")));
        jsClick(viewAllBtn);
        pause(3000);

        // Target the search bar
        WebElement profInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Professor name' or @placeholder='Search for a professor']")));
        profInput.sendKeys("John Scott");
        pause(1500);

        profInput.sendKeys(Keys.ENTER);
        pause(3000);

        boolean found = driver.getPageSource().contains("John Scott");
        Assert.assertTrue(found, "Professor John Scott was not found.");
    }

    @Test(priority = 9)
    public void RP09_SearchNonExistentProfessor() {
        System.out.println("Starting RP09_SearchNonExistentProfessor...");

        WebElement profInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Professor name' or @placeholder='Search for a professor']")));

        // Clear the text field
        profInput.sendKeys(Keys.COMMAND, "a");
        profInput.sendKeys(Keys.BACK_SPACE);
        pause(1000);

        profInput.sendKeys("Black Panther");
        pause(1500);

        profInput.sendKeys(Keys.ENTER);
        pause(3000);

        String pageText = driver.getPageSource().toLowerCase();
        boolean noResults = pageText.contains("no professors") || pageText.contains("0 professors") || pageText.contains("no results");
        Assert.assertTrue(noResults, "Expected 'No results' message was not displayed.");
    }

    @Test(priority = 10)
    public void RP10_SearchSpecialCharacters() {
        System.out.println("Starting RP10_SearchSpecialCharacters...");

        WebElement profInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Professor name' or @placeholder='Search for a professor']")));

        profInput.sendKeys(Keys.COMMAND, "a");
        profInput.sendKeys(Keys.BACK_SPACE);
        pause(1000);

        profInput.sendKeys(" !)$ ");
        pause(1500);

        profInput.sendKeys(Keys.ENTER);
        pause(3000);

        String pageText = driver.getPageSource().toLowerCase();
        boolean noResults = pageText.contains("no professors") || pageText.contains("0 professors") || pageText.contains("no results");
        Assert.assertTrue(noResults, "System did not handle special characters gracefully.");
    }
}
