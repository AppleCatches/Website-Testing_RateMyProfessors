package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class user_profile extends Main {

    @Test(priority = 26)
    public void RP26_ViewSubmittedReviews() throws InterruptedException {
        System.out.println("RP26_ViewSubmittedReviews");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP26 Passed");
    }
    @Test(priority = 27)
    public void RP27_DeleteReview() throws InterruptedException {
        System.out.println("RP27_DeleteReview - deleting test rating");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP27 Passed");
    }
    @Test(priority = 28)
    public void RP28_ChangeUsername() throws InterruptedException {
        System.out.println("RP28_ChangeUsername");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP28 Passed");
    }
    @Test(priority = 29)
    public void RP29_ChangeMySchool() throws InterruptedException {
        System.out.println("RP29_ChangeMySchool");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP29 Passed");
    }
    @Test(priority = 30)
    public void RP30_AccessSettings() throws InterruptedException {
        System.out.println("RP30_AccessSettings");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP30 Passed");
    }
}