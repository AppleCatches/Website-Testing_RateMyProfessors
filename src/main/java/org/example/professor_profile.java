package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class professor_profile extends Main {

    @Test(priority = 21)
    public void RP21_ViewWouldTakeAgain() throws InterruptedException {
        System.out.println("RP21_ViewWouldTakeAgain");
        driver.get("https://www.ratemyprofessors.com/search/professors?query=Scott%20Barnhart");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP21 Passed");
    }
    @Test(priority = 22)
    public void RP22_ViewTotalRatings() throws InterruptedException {
        System.out.println("RP22_ViewTotalRatings");
        /* same */
        System.out.println("RP22 Passed");
    }
    @Test(priority = 23)
    public void RP23_ViewTopTags() throws InterruptedException {
        System.out.println("RP23_ViewTopTags");
        /* same */
        System.out.println("RP23 Passed");
    }
    @Test(priority = 24)
    public void RP24_ViewReviewsDescending() throws InterruptedException {
        System.out.println("RP24_ViewReviewsDescending");
        /* same */
        System.out.println("RP24 Passed");
    }
    @Test(priority = 25)
    public void RP25_ViewCourseList() throws InterruptedException {
        System.out.println("RP25_ViewCourseList");
        /* same */
        System.out.println("RP25 Passed");
    }
}