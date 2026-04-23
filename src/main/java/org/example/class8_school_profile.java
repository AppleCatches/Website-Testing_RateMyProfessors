package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class class8_school_profile extends Main {

    @Test(priority = 36)
    public void RP36_NavigateToSchoolPage() {
        System.out.println("Starting RP36_NavigateToSchoolPage...");
        ensureLoggedIn();
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        // find a school
        try {
            WebElement schoolToggle = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[text()='I want to find a professor at a different school']")));
            jsClick(schoolToggle);
            pause(1500);
        } catch (Exception e) {}

        // Florida Atlantic University
        WebElement schoolInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Your school']")));

        schoolInput.sendKeys(Keys.COMMAND, "a");
        schoolInput.sendKeys(Keys.BACK_SPACE);
        pause(500);
        schoolInput.sendKeys("Florida Atlantic University");
        pause(1500);
        schoolInput.sendKeys(Keys.ENTER);
        pause(3000);

        WebElement fauCard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href, '/school/1268')]")));
        scrollToElement(fauCard);
        jsClick(fauCard);
        pause(4000);
    }

    @Test(priority = 37)
    public void RP37_RateSchool() {
        System.out.println("Starting RP37_RateSchool...");

        // Rate
        WebElement rateBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("mini-rate-school-btn")));
        jsClick(rateBtn);
        pause(4000);

        // All 5 stars (Awesome)
        List<WebElement> sliderBoxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@data-testid='SliderBox']")));

        for (int i = 4; i < Math.min(50, sliderBoxes.size()); i += 5) {
            scrollToElement(sliderBoxes.get(i));
            jsClick(sliderBoxes.get(i));
            pause(300);
        }

        // Write the review
        WebElement commentBox = driver.findElement(By.name("comment"));
        scrollToElement(commentBox);
        commentBox.sendKeys("My personal experience on this school.");
        pause(1500);

        // Submit review
        WebElement submitBtn = driver.findElement(By.cssSelector("button.add-school-rating-btn"));
        scrollToElement(submitBtn);

        jsClick(submitBtn);
        pause(2000);
    }

    @Test(priority = 38)
    public void RP38_CompareSchool() {
        System.out.println("Starting RP38_CompareSchool...");

        // Return to the school profile page
        driver.get("https://www.ratemyprofessors.com/school/1268");
        pause(3000);

        // Click the Compare button
        WebElement compareBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("mini-compare-school-btn")));
        scrollToElement(compareBtn);
        jsClick(compareBtn);
        pause(3000);

        // Search Harvard University
        WebElement compareInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'CompareSchoolsSearch')]//input[@aria-label='search']")));

        compareInput.sendKeys("Harvard University");
        pause(2000);

        // select the first Harvard result
        compareInput.sendKeys(Keys.ARROW_DOWN);
        pause(500);
        compareInput.sendKeys(Keys.ENTER);
        pause(4000);
    }

    @Test(priority = 39)
    public void RP39_ViewAllProfessors() {
        System.out.println("Starting RP39_ViewAllProfessors...");
        driver.get("https://www.ratemyprofessors.com/school/1268");
        pause(3000);

        // View all Professors
        WebElement viewAllBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(), 'View all Professors') and contains(@href, '/search/professors/1268')]")));
        scrollToElement(viewAllBtn);
        jsClick(viewAllBtn);
        pause(3000);

        driver.navigate().back();
        pause(3000);
    }

    @Test(priority = 40)
    public void RP40_ThumbUpAndShowMore() {
        System.out.println("Starting RP40_ThumbUpAndShowMore...");
        // Scroll down and click 'Show More' for reviews
        WebElement showMoreBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(text(), 'Show More')]")));
        scrollToElement(showMoreBtn);
        jsClick(showMoreBtn);
        pause(2000);

        // Find the 'Helpful' thumbs-up icons and click the first two
        List<WebElement> thumbsUpIcons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//img[@alt='Thumbs up']")));

        int clicks = Math.min(2, thumbsUpIcons.size());
        for (int i = 0; i < clicks; i++) {
            WebElement thumb = thumbsUpIcons.get(i);
            scrollToElement(thumb);
            jsClick(thumb);
            pause(1500);
        }
        ensureLoggedOut();
    }
}
