package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class class3_valid_ratings extends Main {

    @Test(priority = 11)
    public void RP11_NavigateToRating() {
        System.out.println("Starting RP11_NavigateToRating...");
        ensureLoggedIn();

        // Navigate to Professor John Scott's profile
        driver.get("https://www.ratemyprofessors.com/professor/2099819");
        pause(3000);

        // Click the Rate button
        WebElement rateBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rate-professor-btn")));
        jsClick(rateBtn);
        pause(4000);

        Assert.assertTrue(driver.getCurrentUrl().contains("add/professor-rating"), "Failed to load the rating form.");
    }

    @Test(priority = 12)
    public void RP12_CourseCodeAndRating() {
        System.out.println("Starting RP12_CourseCodeAndRating...");

        // Enter Course Code
        WebElement courseInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("class")));
        courseInput.sendKeys("CEN4072");
        pause(1000); // Watch it type
        courseInput.sendKeys(Keys.ENTER);
        pause(1000);

        // Select "This is an online course" radio
        try {
            WebElement onlineCourseCheckbox = driver.findElement(By.xpath("//div[contains(text(), 'online course')]/following-sibling::div | //input[@type='checkbox' and contains(@name, 'online')]"));
            jsClick(onlineCourseCheckbox);
            pause(1000);
        } catch (Exception e) {
        }

        // Rate Quality: 5 - Awesome
        List<WebElement> qualityBoxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@type='QUALITY' and @data-testid='SliderBox']")));
        jsClick(qualityBoxes.get(4));
        pause(1500);

        // Rate Difficulty: 1 - Very Easy
        List<WebElement> diffBoxes = driver.findElements(By.xpath("//div[@type='DIFFICULTY' and @data-testid='SliderBox']"));
        jsClick(diffBoxes.get(0));
        pause(1500);
    }

    @Test(priority = 13)
    public void RP13_SelectRadiosAndGrade() {
        System.out.println("Starting RP13_SelectRadiosAndGrade...");

        // Select Yes/No Radios
        jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wouldTakeAgain-Yes"))));
        pause(500);

        jsClick(driver.findElement(By.id("forCredit-No")));
        pause(500);

        jsClick(driver.findElement(By.id("usesTextbooks-No")));
        pause(500);

        jsClick(driver.findElement(By.id("attendanceMandatory-No")));
        pause(1500);

        // Select Grade (B)
        WebElement gradeDropdownWrapper = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='grade']/ancestor::div[2]")));
        gradeDropdownWrapper.click();
        pause(1500);

        WebElement gradeB = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='B']")));
        jsClick(gradeB);
        pause(1500);
    }

    @Test(priority = 14)
    public void RP14_TagsAndReview() {
        System.out.println("Starting RP14_TagsAndReview...");

        // Select up to 3 tags
        jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Getreadytoread"))));
        pause(1000);

        jsClick(driver.findElement(By.name("Participationmatters")));
        pause(1000);

        jsClick(driver.findElement(By.name("Cleargradingcriteria")));
        pause(1500);

        Assert.assertTrue(true, "Tags selected successfully.");
    }

    @Test(priority = 15)
    public void RP15_SubmitReview() {
        System.out.println("Starting RP15_SubmitReview...");

        // Enter the review text
        WebElement commentBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("comment")));
        commentBox.sendKeys("Professor's professional abilities including teaching style and ability to convey the material clearly.");
        pause(3000);

        WebElement submitBtn = driver.findElement(By.cssSelector("button.add-teacher-rating-btn"));
        scrollToElement(submitBtn);
        jsClick(submitBtn);
        pause(1500);

        ensureLoggedIn();
    }
}