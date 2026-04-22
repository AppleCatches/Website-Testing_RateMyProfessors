package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class invalid_ratings extends Main {

    // Helper method to navigate to the review form
    private void loadFreshRatingForm() {
        driver.get("https://www.ratemyprofessors.com/professor/2099819"); // John Scott
        pause(3000);
        WebElement rateBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rate-professor-btn")));
        jsClick(rateBtn);
        pause(4000);
    }

    // Helper method to quickly fill valid data
    private void fillValidDataExcept(String skipField) {
        if (!skipField.equals("Course")) {
            WebElement courseInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("class")));
            scrollToElement(courseInput); // Visual scroll
            courseInput.sendKeys("CEN4072");
            pause(500);
            courseInput.sendKeys(Keys.ENTER);
            pause(500);
        }

        // Quality and Difficulty
        List<WebElement> qualityBoxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@type='QUALITY' and @data-testid='SliderBox']")));
        scrollToElement(qualityBoxes.get(4)); // Visual scroll
        jsClick(qualityBoxes.get(4));

        List<WebElement> diffBoxes = driver.findElements(By.xpath("//div[@type='DIFFICULTY' and @data-testid='SliderBox']"));
        scrollToElement(diffBoxes.get(0)); // Visual scroll
        jsClick(diffBoxes.get(0));
        pause(500);

        // Radios
        scrollToElement(driver.findElement(By.id("wouldTakeAgain-Yes"))); // Visual scroll
        jsClick(driver.findElement(By.id("wouldTakeAgain-Yes")));
        jsClick(driver.findElement(By.id("forCredit-No")));
        jsClick(driver.findElement(By.id("usesTextbooks-No")));
        jsClick(driver.findElement(By.id("attendanceMandatory-No")));
        pause(500);

        // Grade
        WebElement gradeDropdownWrapper = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='grade']/ancestor::div[2]")));
        scrollToElement(gradeDropdownWrapper); // Visual scroll
        gradeDropdownWrapper.click();
        pause(1000);
        jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='B']"))));
        pause(500);

        // Tags
        scrollToElement(driver.findElement(By.name("Getreadytoread"))); // Visual scroll
        jsClick(driver.findElement(By.name("Getreadytoread")));
        jsClick(driver.findElement(By.name("Participationmatters")));
        jsClick(driver.findElement(By.name("Cleargradingcriteria")));
        pause(500);
    }

    @Test(priority = 16)
    public void RP16_EmptyRatingForm() {
        System.out.println("Starting RP16_EmptyRatingForm...");
        ensureLoggedIn();
        loadFreshRatingForm();

        // Scroll straight to the bottom without doing anything
        WebElement submitBtn = driver.findElement(By.cssSelector("button.add-teacher-rating-btn"));
        scrollToElement(submitBtn);
        pause(2000);
        jsClick(submitBtn);
    }

    @Test(priority = 17)
    public void RP17_ExceedMaxCharacters() {
        System.out.println("Starting RP17_ExceedMaxCharacters...");
        loadFreshRatingForm();
        fillValidDataExcept("None");

        // Generate a 400+ character string
        StringBuilder longReview = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            longReview.append("word ");
        }

        WebElement commentBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("comment")));
        scrollToElement(commentBox); // Visual scroll
        commentBox.sendKeys(longReview.toString());
        pause(2000);

        // Click Submit to trigger the validation error
        WebElement submitBtn = driver.findElement(By.cssSelector("button.add-teacher-rating-btn"));
        scrollToElement(submitBtn);
        jsClick(submitBtn);
        pause(2000);
    }

    @Test(priority = 18)
    public void RP18_DuplicateRating() {
        System.out.println("Starting RP18_DuplicateRating...");
        loadFreshRatingForm();
        fillValidDataExcept("None");

        // Add a valid comment
        WebElement commentBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("comment")));
        scrollToElement(commentBox);
        commentBox.sendKeys("Duplicate rating test.");
        pause(1500);

        // Scroll to and click Submit
        WebElement submitBtn = driver.findElement(By.cssSelector("button.add-teacher-rating-btn"));
        scrollToElement(submitBtn);
        jsClick(submitBtn);
        pause(4000); // Wait for the server response
    }

    @Test(priority = 19)
    public void RP19_BlankCourseCode() {
        System.out.println("Starting RP19_BlankCourseCode...");
        loadFreshRatingForm();

        // Fill out everything EXCEPT the Course Code
        fillValidDataExcept("Course");

        WebElement commentBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("comment")));
        scrollToElement(commentBox);
        commentBox.sendKeys("Blank Course Code Test");
        pause(1500);

        WebElement submitBtn = driver.findElement(By.cssSelector("button.add-teacher-rating-btn"));
        scrollToElement(submitBtn);
    }

    @Test(priority = 20)
    public void RP20_SubmitWithoutLogin() {
        System.out.println("Starting RP20_SubmitWithoutLogin...");

        ensureLoggedOut();
        loadFreshRatingForm();

        // Fill out valid data
        fillValidDataExcept("None");

        WebElement commentBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("comment")));
        scrollToElement(commentBox);
        commentBox.sendKeys("Leave a review without logging in.");
        pause(1500);

        WebElement submitBtn = driver.findElement(By.cssSelector("button.add-teacher-rating-btn"));
        scrollToElement(submitBtn);
        jsClick(submitBtn);
        pause(3000);

        ensureLoggedIn();
    }
}
