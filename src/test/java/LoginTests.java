
import Pages.ApolloWalletSite;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        url = "http://localhost:7876/";

        System.out.println("Step 1: Open " + url + " and click on notification if it is present");
        webDriver.get(url);
        website.mainPage().clickOnNotification();
        website.loginPage().verifyLoginPage();
    }

    @Test
    public void testLogInBySecretPhrase () throws Exception {
        System.out.println("Step 2: Switch to Login by Secret Phrase");
        website.loginPage().switchToNonActive();

        System.out.println("Step 3: Enter Secret Phrase data");
        website.loginPage().enterSecretPhrase("0");

        System.out.println("Step 4: Click on Submit Button");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 5: Check URL = dashboard");
        Thread.sleep(2000);
        Assert.assertTrue(webDriver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void testLogInByEmptySecretPhraseNegative() throws InterruptedException {
        System.out.println("Step 2: Switch to Login by Secret Phrase");
        website.loginPage().switchToNonActive();
        System.out.println("Step 3: Click on Submit Button");
        website.loginPage().clickSubmitBtn();
        System.out.println("Step 4: Check that Error Notification Secret Phrase is required is present");
        website.loginPage().checkErrorNotification("Secret Phrase is required.");
    }

    @Test
    public void testLogOut () throws Exception {
        System.out.println("Step 2: Switch to Login by Secret Phrase");
        website.loginPage().switchToNonActive();

        System.out.println("Step 3: Enter Secret Phrase data");
        website.loginPage().enterSecretPhrase("0");

        System.out.println("Step 4: Click on Submit Button");
        website.loginPage().clickSubmitBtn();
        Thread.sleep(3000);

        System.out.println("Step 5: Press on Account Icon");
        website.mainPage().clickAccountIconBtn();

        System.out.println("Step 6: Click on Log out button");
        website.mainPage().clickLogoutBtn();

        System.out.println("Step 7: Wait for Login Page is present");
        website.loginPage().verifyLoginPage();
    }

    @Test
    public void testLogInByAccountID() throws InterruptedException {
        System.out.println("Step 2: Log In by Account ID");
        website.loginPage().enterAccountID("AHWS-NGBG-V4LK-8Q65T");
        System.out.println("Step 3: Click on Submit Button");
        website.loginPage().clickSubmitBtn();
        Thread.sleep(3000);
        System.out.println("Step 4: Verify User Account Rs");
        website.mainPage().clickAccountIconBtn();
        website.mainPage().verifyUserAccountRsInfo("APL-AHWS-NGBG-V4LK-8Q65T");
        System.out.println("Step 5: Log out");
        website.mainPage().clickLogoutBtn();
        System.out.println("Step 6: Check Log In Page");
        website.loginPage().verifyLoginPage();
    }

    @Test
    public void testLogInByAccountIDNegative() throws InterruptedException {
        System.out.println("Step 2: Log In by Invalid Account ID");
        website.loginPage().enterAccountID("negativetest");
        System.out.println("Step 3: Press ENTER button");
        website.loginPage().pressEnterBtn();
        System.out.println("Step 4: Check that Error Notification is present");
        website.loginPage().checkErrorNotification("Incorrect \"account\"");

    }

    @Test
    public void testLogInByEmptyAccountIDNegative() throws InterruptedException {
        System.out.println("Step 2: Click on Submit Button");
        website.loginPage().clickSubmitBtn();
        System.out.println("Step 3: Check that Error Notification is present");
        website.loginPage().checkErrorNotification("Account ID is required.");
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
    public void testCreateVaultWalletRandomlyGeneratedSecretPhrase() throws InterruptedException {
        String secretPhrase;
        String accountId;
        System.out.println("Step 2: Click on Create New User? button");
        website.loginPage().clickNewUserBtn();
        Thread.sleep(1000);

        System.out.println("Step 3: Verify Create New User page");
        website.loginPage().verifyCreateStandardWallet();

        System.out.println("Step 4: Switch to Vault Wallet");
        website.loginPage().switchToNonActive();

        System.out.println("Step 5: Verify Create new vault wallet page");
        website.loginPage().verifyCreateVaultWallet();

        System.out.println("Step 6: Press Create Account button");
        website.loginPage().clickCreateAccountBtn();

        System.out.println("Step 7: Verify Create New Vault Wallet Page (Second Step)");
        website.loginPage().verifyCreateVaultWalletSecondStep();
        secretPhrase = website.loginPage().copySecretPhrase();
        accountId = website.loginPage().copyAccountId();
        Thread.sleep(2000);

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
    public void testCreateVaultWalletCustomSecretPhrase() throws InterruptedException {
        String secretPhrase;
        String accountId;
        System.out.println("Step 2: Click on Create New User? button");
        website.loginPage().clickNewUserBtn();
        Thread.sleep(1000);

        System.out.println("Step 3: Verify Create New User page");
        website.loginPage().verifyCreateStandardWallet();

        System.out.println("Step 4: Switch to Vault Wallet");
        website.loginPage().switchToNonActive();

        System.out.println("Step 5: Verify Create new vault wallet page");
        website.loginPage().verifyCreateVaultWallet();

        System.out.println("Step 6: Use custom secret phrase");
        website.loginPage().clickCheckBoxCustomSecretPhrase();

        System.out.println("Step 7: Input custom secret phrase");
        website.loginPage().enterCustomSecretPhrase("automationtesting");
        Thread.sleep(3000);

        System.out.println("Step 6: Press Create Account button");
        website.loginPage().clickCreateAccountBtn();

        System.out.println("Step 7: Verify Create New Vault Wallet Page (Second Step)");
        website.loginPage().verifyCreateVaultWalletSecondStepCustomSecretPhrase();
        secretPhrase = website.loginPage().copySecretPhrase();
        accountId = website.loginPage().copyAccountId();
        Thread.sleep(2000);

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

    @Test
    public void testExitModalWindowBtn () throws Exception {
        System.out.println("Step 2: Click on Create New User? button");
        website.loginPage().clickNewUserBtn();
        Thread.sleep(1000);

        System.out.println("Step 3: Click on Exit button");
        website.loginPage().clickCloseModalWindow();

        System.out.println("Step 4: Check that modal window is closed");
        website.loginPage().verifyLoginPage();
    }

    @After
    public void tearDown() {
        if (webDriver != null)
            webDriver.quit();
    }
}
