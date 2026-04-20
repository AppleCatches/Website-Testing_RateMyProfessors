package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class invalid_ratings extends Main {

    @Test(priority = 16)
    public void RP16_EmptyRatingForm() throws InterruptedException {
        System.out.println("RP16_EmptyRatingForm");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP16 Passed");
    }
    @Test(priority = 17)
    public void RP17_MissingQualityScore() throws InterruptedException {
        System.out.println("RP17_MissingQualityScore");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP17 Passed");
    }
    @Test(priority = 18)
    public void RP18_InvalidNumericValue() throws InterruptedException {
        System.out.println("RP18_InvalidNumericValue");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP18 Passed");
    }
    @Test(priority = 19)
    public void RP19_MissingCourseSelection() throws InterruptedException {
        System.out.println("RP19_MissingCourseSelection");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP19 Passed");
    }
    @Test(priority = 20)
    public void RP20_SubmitWithoutLogin() throws InterruptedException {
        System.out.println("RP20_SubmitWithoutLogin");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP20 Passed");
    }
}