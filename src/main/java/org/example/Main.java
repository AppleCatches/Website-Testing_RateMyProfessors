package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class Main {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeSuite
    public void setupSuite() {
        FirefoxOptions options = new FirefoxOptions();
        String profilePath = "/Users/denisepierre/Library/Application Support/Firefox/Profiles/6ixoyzs3.default-release";
        options.addArguments("-profile", profilePath);

        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.ratemyprofessors.com/");

        dismissAdBlockerPopup();
    }

    @AfterSuite
    public void tearDownSuite() throws InterruptedException {
        System.out.println("TEST FINISHED\n");
        Thread.sleep(1500);
        if (driver != null) {
            driver.quit();
        }
    }

    public void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void dismissAdBlockerPopup() {
        try {
            WebElement continueBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(text(), 'Continue Without Supporting')]")));
            jsClick(continueBtn);
            System.out.println("AdBlocker popup dismissed.");
            pause(1500);
        } catch (Exception e) {
            // System.out.println("No AdBlocker popup found.");
        }
    }

    public void ensureLoggedOut() {
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        try {
            // Check for the specific 'Log In' button
            WebElement loginBtn = driver.findElement(By.cssSelector("button[data-testid='modal-button-link']"));
            if (loginBtn.isDisplayed()) return;
        } catch (Exception e) {
            try {
                // Click 'Hey, CEN4072' AccountButton
                WebElement profileMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='AccountButton']")));
                jsClick(profileMenu);
                pause(1000);

                // Click Logout
                WebElement logoutBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Logout']")));
                jsClick(logoutBtn);
                pause(2000);
            } catch (Exception ex) {
                System.out.println("Could not perform logout sequence.");
            }
        }
    }

    public void ensureLoggedIn() {
        driver.get("https://www.ratemyprofessors.com/");
        pause(2000);

        try {
            // Check for the specific 'Hey, CEN4072' button
            WebElement profileMenu = driver.findElement(By.cssSelector("button[data-testid='AccountButton']"));
            if (profileMenu.isDisplayed()) return;
        } catch (Exception e) {
            try {
                // Click 'Log In' button
                WebElement loginNavBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-testid='modal-button-link']")));
                jsClick(loginNavBtn);
                pause(1500);

                // Fill credentials
                WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
                emailInput.sendKeys("bleutray@gmail.com");
                pause(500);

                WebElement passwordInput = driver.findElement(By.id("password"));
                passwordInput.sendKeys("crn10421");
                pause(500);

                // Click Continue
                WebElement continueBtn = driver.findElement(By.xpath("//button[text()='Continue']"));
                jsClick(continueBtn);

                pause(3000);
            } catch (Exception ex) {
                System.out.println("Could not perform login sequence.");
            }
        }
    }
}
