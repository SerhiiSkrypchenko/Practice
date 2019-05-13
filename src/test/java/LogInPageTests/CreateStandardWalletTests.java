package LogInPageTests;

import Pages.ApolloWalletSite;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateStandardWalletTests {
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
        url = "http://localhost:7876/";

        System.out.println("Step 1: Open " + url + " and click on notification if it is present");
        webDriver.get(url);
        website.mainPage().clickOnNotification();
        website.loginPage().verifyLoginPage();
    }

    @Test
    public void testCreateStandardWallet() throws InterruptedException {
        String secretPhrase;
        String accountId;
        System.out.println("Step 2: Click on Create New User? button");
        website.loginPage().clickNewUserBtn();
        Thread.sleep(1000);

        System.out.println("Step 3: Verify Create New User page");
        website.loginPage().verifyCreateStandardWallet();

        System.out.println("Step 4: Press Create Account button");
        website.loginPage().clickCreateAccountBtn();

        System.out.println("Step 5: Verify Create new user page (Second Step)");
        secretPhrase = website.loginPage().copySecretPhrase();
        accountId = website.loginPage().copyAccountId();
        website.loginPage().verifyCreateStandardWalletSecondStep();

        System.out.println("Step 6: Click on CheckBox");
        website.loginPage().clickCheckboxDataStored();

        System.out.println("Step 7: Click on Next Button");
        website.loginPage().clickNextBtn();
        Thread.sleep(2000);

        System.out.println("Step 8: Input Secret Phrase");
        website.loginPage().enterSecretPhrase(secretPhrase);
        Thread.sleep(2000);

        System.out.println("Step 9: Press Create New Account button ");
        website.loginPage().clickSubmitBtn();
        Thread.sleep(2000);

        System.out.println("Step 10: Verify User Account Rs");
        website.mainPage().clickAccountIconBtn();
        website.mainPage().verifyUserAccountRsInfo(accountId);

        System.out.println("Step 11: Do Log out");
        website.mainPage().clickLogoutBtn();

        System.out.println("Step 12: Check Log In Page");
        website.loginPage().verifyLoginPage();
    }

    @Test
    public void testCreateStandardWalletInvalidSecretPhrase() throws InterruptedException {
        System.out.println("Step 2: Click on Create New User? button");
        website.loginPage().clickNewUserBtn();
        Thread.sleep(1000);

        System.out.println("Step 3: Verify Create New User page");
        website.loginPage().verifyCreateStandardWallet();

        System.out.println("Step 4: Press Create Account button");
        website.loginPage().clickCreateAccountBtn();

        System.out.println("Step 5: Verify Create new user page (Second Step)");
        website.loginPage().verifyCreateStandardWalletSecondStep();

        System.out.println("Step 6: Click on CheckBox");
        website.loginPage().clickCheckboxDataStored();

        System.out.println("Step 7: Click on Next Button");
        website.loginPage().clickNextBtn();
        Thread.sleep(2000);

        System.out.println("Step 8: Input Secret Phrase");
        website.loginPage().enterSecretPhrase("Invalid Secret Phrase");
        Thread.sleep(2000);

        System.out.println("Step 9: Press Create New Account button ");
        website.loginPage().clickSubmitBtn();
        Thread.sleep(2000);

        System.out.println("Step 10: Error message is present");
        website.loginPage().checkErrorNotification("Incorrect secret phrase!");

        System.out.println("Step 11: Close Modal Window");
        website.loginPage().clickCloseModalWindow();

        System.out.println("Step 12: Verify Log In Page");
        website.loginPage().verifyLoginPage();
    }

    @Test
    public void testCreateStandardWalletNegative() throws InterruptedException {
        System.out.println("Step 2: Click on Create New User? button");
        website.loginPage().clickNewUserBtn();
        Thread.sleep(1000);

        System.out.println("Step 3: Verify Create New User page");
        website.loginPage().verifyCreateStandardWallet();

        System.out.println("Step 4: Press Create Account button");
        website.loginPage().clickCreateAccountBtn();

        System.out.println("Step 5: Verify Create new user page (Second Step)");
        website.loginPage().verifyCreateStandardWalletSecondStep();

        System.out.println("Step 6: Click on Next Button");
        website.loginPage().clickNextBtn();
        Thread.sleep(2000);

        System.out.println("Step 7: Error message is present");
        website.loginPage().checkErrorNotification("You have to verify that you stored your private data.");
    }

    @After
    public void tearDown() {
        if (webDriver != null)
            webDriver.quit();
    }
}
