package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import placelab.utilities.WebDriverSetup;

public class LoginTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String user = "Vedad Karalić";
    private String username = System.getProperty("username");
    private String password = System.getProperty("password");
    private String homePageUrl = "https://demo.placelab.com/dashboard/traffic";
    private String forgotPasswordUrl = "https://demo.placelab.com/password/forgot";
    private String forgotPasswordSecondUrl = "https://demo.placelab.com/email/sent";
    private String passwordForgot="https://demo.placelab.com/password/forgot";

    //Specify the driver and browser that will be used for this scenario

    @Parameters({"browser"})

    @BeforeTest(alwaysRun = true, groups = {"Positive,Negative"},description = "Verify that the  user is able to open" + "PlaceLab App.")
    public void openApp(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.navigate().to(host);
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
    }
    //Actual test case implementation
    @Test(priority = 6,groups = {"Positive"},description = "Verify that user is able to Log in" + "PlaceLab with valid credentials.", suiteName = "Smoke Test")
    public void testLoginPage() {

        //Go to PlaceLab demo app
        driver.navigate().to(host);

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        WebElement logo=driver.findElement(By.xpath("//img[@src='/assets/logo"+ "-526ea19604d26801aca90fe441f7df4775a24a5d74ae273dbc4af85f42241259.png']"));
        boolean logoPresent=logo.isDisplayed();
        Assert.assertTrue(logoPresent);
        System.out.println(logo.getLocation());
        WebElement enterUsername=driver.findElement(By.cssSelector("input[placeholder$='Email']"));
        enterUsername.sendKeys(username);
        WebElement enterPassword=driver.findElement(By.name("password"));
        enterPassword.sendKeys(password);
        WebElement submitForm=driver.findElement(By.cssSelector("input[value$='Log in']"));
        submitForm.click();
        Assert.assertEquals(driver.getCurrentUrl(),homePageUrl);
        try {
            WebElement userName = driver.findElement(By.id("user-name"));
            assert (userName.getText().contains(user));
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Expected user is not logged in!");
        }
        WebElement userRole=driver.findElement(By.id("user-role"));
        Assert.assertEquals(userRole.getText(),"Group Admin");
    }
    @Test(priority = 2,groups = {"Negative"},description = "Verify that user is not able to  Log in with empty username field" + "PlaceLab with invalid credentials.", suiteName = "Smoke Test")
    public void testLoginEmptyUsername() {

        //Go to PlaceLab demo app
        driver.navigate().to(host);
        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        WebElement enterUsername = driver.findElement(By.cssSelector("input[placeholder$='Email']"));
        enterUsername.sendKeys("");
        WebElement enterPassword = driver.findElement(By.name("password"));
        enterPassword.sendKeys(password);
        WebElement submitForm = driver.findElement(By.cssSelector("input[value$='Log in']"));
        submitForm.click();
        Assert.assertEquals(driver.getCurrentUrl(), host);
        WebElement error = driver.findElement(By.className("error-area"));
    }
    @Test(priority = 1,groups = {"Negative"},description = "Verify that user is not able to  Log in with empty password field" + "PlaceLab with invalid credentials.", suiteName = "Smoke Test")
    public void testLoginEmptyPassword() {
        //Go to PlaceLab demo app
        driver.navigate().to(host);
        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        WebElement enterUsername = driver.findElement(By.cssSelector("input[placeholder$='Email']"));
        enterUsername.sendKeys(username);
        WebElement enterPassword = driver.findElement(By.name("password"));
        enterPassword.sendKeys("");
        WebElement submitForm = driver.findElement(By.cssSelector("input[value$='Log in']"));
        submitForm.click();
        Assert.assertEquals(driver.getCurrentUrl(), host);
        WebElement error = driver.findElement(By.className("error-area"));
    }
    @Test(priority = 3,groups = {"Negative"},description = "Verify that user is not able to  Log in with invalid password" + "PlaceLab with invalid credentials.", suiteName = "Smoke Test")
    public void testLoginInvalidPassword() {

        //Go to PlaceLab demo app
        driver.navigate().to(host);

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        WebElement enterUsername = driver.findElement(By.id("email"));
        enterUsername.sendKeys(username);
        WebElement enterPassword = driver.findElement(By.xpath("//*[@id='password']"));
        enterPassword.sendKeys("password");
        //If there is a unique identifier it will be used instead this is just practise :)
        WebElement submitForm = driver.findElement(By.cssSelector("input[value$='Log in']"));
        submitForm.click();
        Assert.assertEquals(driver.getCurrentUrl(), host);
        WebElement error = driver.findElement(By.className("error-area"));
    }
    @Test(priority = 4,groups = {"Negative"},description = "Verify that user is not able to  Log in with invalid username" + "PlaceLab with invalid credentials.", suiteName = "Smoke Test")
    public void testInvalidUsername() {
        //Go to PlaceLab demo app
        driver.navigate().to(host);
        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        WebElement enterUsername = driver.findElement(By.id("email"));
        enterUsername.sendKeys("username");
        WebElement enterPassword = driver.findElement(By.xpath("//*[@id='password']"));
        enterPassword.sendKeys(password);
        WebElement submitForm = driver.findElement(By.cssSelector("input[value$='Log in']"));
        submitForm.click();
        Assert.assertEquals(driver.getCurrentUrl(), host);
        WebElement error = driver.findElement(By.className("error-area"));
    }
    @Test(priority = 5,groups = {"Positive"},description = "Verify that user is able to click on Forgot password text" + "PlaceLab forgot password text.", suiteName = "Smoke Test")
    public void testForgotPassword() {
        //Go to PlaceLab demo app
        driver.navigate().to(host);

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        WebElement forgotPassword=driver.findElement(By.linkText("Forgot your password?"));
        forgotPassword.click();
        Assert.assertEquals(driver.getCurrentUrl(), passwordForgot);
    }
    @AfterTest(dependsOnGroups = {"Negative"},description = "Verify that user is not Logged in.")
    public void failedLogin() {

        Assert.assertEquals(driver.getCurrentUrl(),host);
    }
    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}