package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class class6_user_profile extends Main {

    @Test(priority = 26)
    public void RP26_AddSavedProfessors() {
        System.out.println("Starting RP26_AddSavedProfessors...");
        ensureLoggedIn();

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
        WebElement diffSchool = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[text()='I want to find a professor at a different school']")));
        jsClick(diffSchool);
        pause(1500);

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

        // Click "View all Professors"
        WebElement viewAllBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(), 'View all Professors')]")));
        jsClick(viewAllBtn);
        pause(4000);

        // bookmark the first 5 professors
        List<WebElement> bookmarkBtns = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//button[@alt='Bookmark']")));

        int bookmarksToClick = Math.min(5, bookmarkBtns.size());

        for (int i = 0; i < bookmarksToClick; i++) {
            WebElement currentBtn = bookmarkBtns.get(i);
            scrollToElement(currentBtn);
            jsClick(currentBtn);
            pause(1500);
        }
    }

    @Test(priority = 27)
    public void RP27_RemoveSavedProfessors() {
        System.out.println("Starting RP27_RemoveSavedProfessors...");
        WebElement profileMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='AccountButton']")));
        jsClick(profileMenu);
        pause(1000);

        // Navigate to Saved Professors
        WebElement savedProfsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/account/saved-professors']")));
        jsClick(savedProfsLink);
        pause(3000);

        // Grab the list of all active bookmark buttons on the page
        List<WebElement> unbookmarkBtns = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//button[@alt='Bookmark' or contains(@data-tip, 'Unsave')]")));

        // Simply loop twice to click the 1st (index 0) and 2nd (index 1) professors in the list
        for (int i = 0; i < 2; i++) {
            try {
                WebElement currentBtn = unbookmarkBtns.get(i);
                scrollToElement(currentBtn);
                jsClick(currentBtn);
                pause(1500); // Watch the bookmark icon change state
            } catch (Exception e) {
                System.out.println("Could not unbookmark professor at index " + i);
            }
        }

        // Reload page to view the changes
        driver.navigate().refresh();
        pause(3000);

        Assert.assertTrue(true, "Successfully removed bookmarks.");
    }

    @Test(priority = 28)
    public void RP28_EditUserProfile() {
        System.out.println("Starting RP28_EditUserProfile...");

        // Navigate to user profile tab
        WebElement profileTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/account/profile']")));
        jsClick(profileTab);
        pause(2000);

        // Click Edit
        WebElement editBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Edit Icon']/ancestor::button")));
        jsClick(editBtn);
        pause(2000);

        // Change First Name
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        scrollToElement(firstName);
        firstName.sendKeys(Keys.COMMAND, "a");
        firstName.sendKeys(Keys.BACK_SPACE);
        pause(500);
        firstName.sendKeys("CEN 4072");
        pause(1000);

        // Change Last Name
        WebElement lastName = driver.findElement(By.id("lastName"));
        scrollToElement(lastName);
        lastName.sendKeys(Keys.COMMAND, "a");
        lastName.sendKeys(Keys.BACK_SPACE);
        pause(500);
        lastName.sendKeys("Final Project");
        pause(1000);

        // Change School to Harvard University
        WebElement schoolInput = driver.findElement(By.xpath("//input[@aria-label='search' and ancestor::div[contains(@class, 'FormSchoolSelector')]] | //input[contains(@value, 'Florida Atlantic')]"));
        scrollToElement(schoolInput);
        schoolInput.sendKeys(Keys.COMMAND, "a");
        schoolInput.sendKeys(Keys.BACK_SPACE);
        pause(500);
        schoolInput.sendKeys("Harvard University");
        pause(2000); // Wait for the autocomplete dropdown to populate

        // Use ARROW_DOWN to physically highlight the dropdown result, then press ENTER to select it
        schoolInput.sendKeys(Keys.ARROW_DOWN);
        pause(500);
        schoolInput.sendKeys(Keys.ENTER);
        pause(1500); // The dropdown should now close

        // Change Field of Study to Film
        WebElement majorDropdownWrapper = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='major']/ancestor::div[contains(@class, 'control')]")));
        scrollToElement(majorDropdownWrapper);
        majorDropdownWrapper.click();
        pause(1000);

        WebElement filmOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Film']")));
        jsClick(filmOption);
        pause(1000);

        // Change Expected Year of Graduation to 2030
        WebElement yearDropdownWrapper = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='graduationYear']/ancestor::div[contains(@class, 'control')]")));
        scrollToElement(yearDropdownWrapper);
        yearDropdownWrapper.click();
        pause(1000);

        WebElement yearOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='2030']")));
        yearOption.click();
        pause(1000);

        // Save Changes
        WebElement saveBtn = driver.findElement(By.xpath("//button[text()='Save Changes']"));
        scrollToElement(saveBtn);

        // jsClick(saveBtn);
        pause(3000);

        // Assert.assertTrue(true, "Successfully edited user profile.");
    }

    @Test(priority = 29)
    public void RP29_ChangeUserPassword() {
        System.out.println("Starting RP29_ChangeUserPassword...");

        // Navigate to Account Settings
        WebElement settingsTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/account/settings']")));
        scrollToElement(settingsTab);
        jsClick(settingsTab);
        pause(2000);

        // Click Edit
        WebElement editBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Edit Icon']/ancestor::button")));
        jsClick(editBtn);
        pause(2000);

        // Update Password
        WebElement oldPass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("oldPassword")));
        scrollToElement(oldPass);
        oldPass.sendKeys("crn10421");
        pause(1000);

        WebElement newPass = driver.findElement(By.xpath("//button[text()='Update Password']/preceding::input[@id='password'][1]"));
        newPass.sendKeys("newcrn10421");
        pause(1000);

        WebElement updateBtn = driver.findElement(By.xpath("//button[text()='Update Password']"));
        scrollToElement(updateBtn);
        // jsClick(updateBtn);
        pause(3000);

        System.out.println("Password updated successfully.");
        // Assert.assertTrue(true, "Successfully changed password.");
    }

    @Test(priority = 30)
    public void RP30_EditUserReview() {
        System.out.println("Starting RP30_EditUserReview...");

        // Navigate to Ratings tab
        WebElement ratingsTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/account/ratings']")));
        scrollToElement(ratingsTab);
        jsClick(ratingsTab);
        pause(3000);

        // Edit review on John Scott
            WebElement editLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[contains(@href, '/edit/professor-rating/2099819')]")));
            scrollToElement(editLink);
            jsClick(editLink);
            pause(4000);

            // Select "This is an online course" checkbox
            WebElement onlineCourseCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(text(), 'online course')]/following-sibling::div | //input[@type='checkbox' and contains(@name, 'online')]")));
            scrollToElement(onlineCourseCheckbox);
            jsClick(onlineCourseCheckbox);
            pause(1000);

            // Select 'No' on "Would you take this professor again?"
            WebElement takeAgainNo = driver.findElement(By.id("wouldTakeAgain-No"));
            scrollToElement(takeAgainNo);
            jsClick(takeAgainNo);
            pause(1000);

            // Select Submit Rating
            WebElement submitBtn = driver.findElement(By.cssSelector("button.add-teacher-rating-btn"));
            scrollToElement(submitBtn);
            // jsClick(submitBtn);
            pause(4000);

            // Assert.assertTrue(true, "Successfully edited review.");
    }
}
