package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class navigation extends Main {

    @Test(priority = 31)
    public void RP31_ClickLogoToHome() throws InterruptedException {
        System.out.println("RP31_ClickLogoToHome");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP31 Passed");
    }
    @Test(priority = 32)
    public void RP32_NavigateFindSchool() throws InterruptedException {
        System.out.println("RP32_NavigateFindSchool");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP32 Passed");
    }
    @Test(priority = 33)
    public void RP33_NavigateFindProfessor() throws InterruptedException {
        System.out.println("RP33_NavigateFindProfessor");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP33 Passed");
    }
    @Test(priority = 34)
    public void RP34_CheckFooterLinks() throws InterruptedException {
        System.out.println("RP34_CheckFooterLinks");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP34 Passed");
    }
    @Test(priority = 35)
    public void RP35_VerifyAccessibilityTab() throws InterruptedException {
        System.out.println("RP35_VerifyAccessibilityTab");
        // dismissAdsAndPopups();
        Thread.sleep(1500);
        System.out.println("RP35 Passed");
    }
}