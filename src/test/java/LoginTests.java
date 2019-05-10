
import Pages.ApolloWalletSite;
import Pages.LoginPage;
import Pages.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTests {
    WebDriver webDriver;
    WebDriverWait wait;
    ApolloWalletSite website;
    String url;

    @Before
    public void setUp () throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, 30, 500);
        website = new ApolloWalletSite(webDriver);
        url = "http://localhost:7876";

        System.out.println("Step 1: Open " + url + " and click on notification if it is present");
        webDriver.get(url);
        website.mainPage().clickOnNotification();
    }

    @Test
    public void testLogInBySecretPhrase () throws Exception {
        System.out.println("Step 2: Switch to Login by Secret Phrase");
        website.loginPage().switchToLoginBySecretPhrase();

        System.out.println("Step 3: Enter Secret Phrase data");
        website.loginPage().enterSecretPhrase("0");

        System.out.println("Step 4: Click on Submit Button");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 5: Check URL = dashboard");
        Assert.assertTrue(webDriver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void testLogOut () throws Exception {
        System.out.println("Step 2: Switch to Login by Secret Phrase");
        website.loginPage().switchToLoginBySecretPhrase();

        System.out.println("Step 3: Enter Secret Phrase data");
        website.loginPage().enterSecretPhrase("0");

        System.out.println("Step 4: Click on Submit Button");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 5: Press on Account Icon");
        website.mainPage().clickAccountIconBtn();

        System.out.println("Step 6: Click on Log out button");
        website.mainPage().clickLogoutBtn();

        System.out.println("Step 7: Wait for Login Page is present");
        website.loginPage().waitForLoginPage();
    }

    @Test
    public void testLogInByAccountID() throws InterruptedException {
        System.out.println("Step 2: Log In by Account ID");
        website.loginPage().enterAccountID("AHWS-NGBG-V4LK-8Q65T");
        System.out.println("Step 3: Click on Submit Button");
        website.loginPage().clickSubmitBtn();
        Thread.sleep(3000);
        System.out.println("Step 4: Verify User Account Rs");
        website.mainPage().verifyUserAccountRs("APL-AHWS-NGBG-V4LK-8Q65T");
    }

    @Test
    public void testLogInByAccountIDNegative() throws InterruptedException {
        System.out.println("Step 2: Log In by Invalid Account ID");
        website.loginPage().enterAccountID("negativetest");
        System.out.println("Step 3: Click on Submit Button");
        website.loginPage().pressEnterBtn();
        System.out.println("Step 4: Check that Error Notification is present");
        website.loginPage().checkErrorNotification();

    }

    @After
    public void tearDown() {
        if (webDriver != null)
            webDriver.quit();
    }
}
