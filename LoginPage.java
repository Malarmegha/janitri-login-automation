package com.janitri.qa.tests;

import com.janitri.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void testLoginButtonDisabledWhenFieldsAreEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("");
        loginPage.enterPassword("");
        Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Login button should be disabled when fields are empty");
    }

    @Test
    public void testPasswordMaskedButton() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPassword("testPassword");
        String typeBeforeToggle = loginPage.getPasswordFieldType();
        Assert.assertEquals(typeBeforeToggle, "password", "Password should be masked by default");

        loginPage.togglePasswordVisibility();
        String typeAfterToggle = loginPage.getPasswordFieldType();
        Assert.assertEquals(typeAfterToggle, "text", "Password should be visible after toggling");

        loginPage.togglePasswordVisibility();
        String typeAfterToggleBack = loginPage.getPasswordFieldType();
        Assert.assertEquals(typeAfterToggleBack, "password", "Password should be masked again after toggling back");
    }

    @Test
    public void testInvalidLoginShowErrorMsg() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUserId("randomUser");
        loginPage.enterPassword("randomPass");
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();
        System.out.println("Error Message Displayed: " + errorMessage);
        Assert.assertTrue(errorMessage.length() > 0, "Error message should be displayed on invalid login");
    }

    @Test
    public void testPresenceOfPageElements() {
        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.isElementPresent(By.id("userId")), "User ID input field should be present");
        Assert.assertTrue(loginPage.isElementPresent(By.id("password")), "Password input field should be present");
        Assert.assertTrue(loginPage.isElementPresent(By.id("loginButton")), "Login button should be present");
        Assert.assertTrue(loginPage.isElementPresent(By.cssSelector(".password-toggle")), "Password toggle (eye icon) should be present");

        // Optionally check title
        String pageTitle = driver.getTitle();
        System.out.println("Page title is: " + pageTitle);
        Assert.assertTrue(pageTitle.contains("Janitri Dashboard"), "Page title should contain 'Janitri Dashboard'");
    }
}
