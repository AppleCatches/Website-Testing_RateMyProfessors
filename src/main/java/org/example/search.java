package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class search extends Main {

    @Test(priority = 6)
    public void RP06_SearchNonExistentSchool() {
        System.out.println("Starting RP06_SearchNonExistentSchool...");
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        // Click "I want to find a professor at a different school" toggle (if present)
        try {
            WebElement diffSchool = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[text()='I want to find a professor at a different school']")));
            jsClick(diffSchool);
            pause(1500); // Watch the toggle switch
        } catch (Exception e) {}

        // Input fake school
        WebElement schoolInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Your school']")));
        schoolInput.sendKeys("Magic Academics");
        pause(1500); // Watch it type

        schoolInput.sendKeys(Keys.ENTER); // Hit Enter to search
        pause(3000); // Wait for the UI to update

        String pageText = driver.getPageSource().toLowerCase();

        // Check for any variation of a "Not Found" message
        boolean noResultsText = pageText.contains("no school") ||
                pageText.contains("0 school") ||
                pageText.contains("no result") ||
                pageText.contains("not exist") ||
                pageText.contains("not found");

        // Fallback: Ensure no school cards were generated in the dropdown
        List<WebElement> schoolCards = driver.findElements(By.xpath("//a[contains(@aria-label, 'Link to school page')]"));
        boolean noDropdownCards = schoolCards.isEmpty();

        Assert.assertTrue(noResultsText || noDropdownCards, "Expected 'No results' message or empty dropdown for fake school.");
    }

    @Test(priority = 7)
    public void RP07_SearchSchoolByName() {
        System.out.println("Starting RP07_SearchSchoolByName...");

        // Return to Home Page via Logo
        try {
            WebElement logo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Logo']")));
            jsClick(logo);
            pause(2000);
        } catch (Exception e) {
            driver.get("https://www.ratemyprofessors.com/");
            pause(2000);
        }

        // Click toggle again
        try {
            WebElement diffSchool = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[text()='I want to find a professor at a different school']")));
            jsClick(diffSchool);
            pause(1500);
        } catch (Exception e) {}

        // Input real school
        WebElement schoolInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Your school']")));

        // Clear the field first using Keyboard commands
        schoolInput.sendKeys(Keys.COMMAND, "a");
        schoolInput.sendKeys(Keys.BACK_SPACE);
        pause(500);

        schoolInput.sendKeys("Florida Atlantic University");
        pause(1500);

        schoolInput.sendKeys(Keys.ENTER); // Press Enter to submit search
        pause(3000); // Wait for the multiple results to load in dropdown

        // Target and click the exact Florida Atlantic University @ Boca Raton, FL school card
        WebElement fauCard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@aria-label='Link to school page for Florida Atlantic University']")));
        jsClick(fauCard);
        pause(4000); // Wait to arrive at the specific school's page

        Assert.assertTrue(driver.getCurrentUrl().contains("school"), "Failed to navigate to the School page.");
    }

    @Test(priority = 8)
    public void RP08_SearchProfessorByName() {
        System.out.println("Starting RP08_SearchProfessorByName...");
        // We are currently on the FAU school page from RP07, seamlessly continuing the flow

        // Click "View all Professors"
        WebElement viewAllBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(), 'View all Professors')]")));
        jsClick(viewAllBtn);
        pause(3000); // Wait for the professor directory to load

        // Target the search bar
        WebElement profInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Professor name' or @placeholder='Search for a professor']")));
        profInput.sendKeys("John Scott");
        pause(1500); // Watch it type

        profInput.sendKeys(Keys.ENTER); // Hit Enter to search
        pause(3000); // Watch it filter the list

        boolean found = driver.getPageSource().contains("John Scott");
        Assert.assertTrue(found, "Professor John Scott was not found.");
    }

    @Test(priority = 9)
    public void RP09_SearchNonExistentProfessor() {
        System.out.println("Starting RP09_SearchNonExistentProfessor...");

        WebElement profInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Professor name' or @placeholder='Search for a professor']")));

        // React forms require a "Select All -> Delete" keyboard command to clear properly
        profInput.sendKeys(Keys.COMMAND, "a");
        profInput.sendKeys(Keys.BACK_SPACE);
        pause(1000);

        profInput.sendKeys("Black Panther");
        pause(1500); // Watch it type

        profInput.sendKeys(Keys.ENTER); // Hit Enter
        pause(3000); // Wait for results to update

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

        profInput.sendKeys(Keys.ENTER); // Hit Enter
        pause(3000); // Wait for validation

        // Verification: Validate that the text field turns red
        // Retrieves the computed CSS properties directly from the DOM Element
        String borderColor = profInput.getCssValue("border-color");
        String outlineColor = profInput.getCssValue("outline-color");
        String classAttribute = profInput.getAttribute("class").toLowerCase();

        // Check for RGB/RGBA combinations for red, or error classes
        boolean isRed = borderColor.contains("255, 0, 0") || outlineColor.contains("255, 0, 0")
                || borderColor.contains("red") || classAttribute.contains("error") || classAttribute.contains("invalid");

        // Fallback: The site might just filter the list to 0 without highlighting the box red
        String pageText = driver.getPageSource().toLowerCase();
        boolean noResults = pageText.contains("no professors") || pageText.contains("0 professors") || pageText.contains("no results");

        Assert.assertTrue(isRed || noResults, "System did not turn red or handle special characters gracefully.");
    }
}
