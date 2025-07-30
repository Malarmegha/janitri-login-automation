package com.janitri.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By userIdInput = By.id("userId");         // Adjust locator as per actual element
    private By passwordInput = By.id("password");
    private By loginButton = By.id("loginButton");
    private By passwordToggle = By.cssSelector(".password-toggle"); // eye icon, adjust selector
    private By errorMsg = By.cssSelector(".error-message");         // adjust selector if needed

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with elements
    public void enterUserId(String userId) {
        driver.findElement(userIdInput).clear();
        driver.findElement(userIdInput).sendKeys(userId);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void togglePasswordVisibility() {
        driver.findElement(passwordToggle).click();
    }

    public boolean isLoginButtonEnabled() {
        return driver.findElement(loginButton).isEnabled();
    }

    public String getPasswordFieldType() {
        return driver.findElement(passwordInput).getAttribute("type");
    }

    public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
}
