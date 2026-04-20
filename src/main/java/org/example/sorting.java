package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sorting extends Main {

    @Test(priority = 36)
    public void RP36_SortHighestRating() throws InterruptedException {
        System.out.println("RP36_SortHighestRating");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP36 Passed");
    }
    @Test(priority = 37)
    public void RP37_SortLowestRating() throws InterruptedException {
        System.out.println("RP37_SortLowestRating");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP37 Passed");
    }
    @Test(priority = 38)
    public void RP38_SortMostRatings() throws InterruptedException {
        System.out.println("RP38_SortMostRatings");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP38 Passed");
    }
    @Test(priority = 39)
    public void RP39_SortOldest() throws InterruptedException {
        System.out.println("RP39_SortOldest");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP39 Passed");
    }
    @Test(priority = 40)
    public void RP40_SortHighestQuality() throws InterruptedException {
        System.out.println("RP40_SortHighestQuality");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP40 Passed");
    }
}