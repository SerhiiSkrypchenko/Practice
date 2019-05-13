package LogInPageTests;

import Pages.ApolloWalletSite;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateVaultWalletTests {
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

    @After
    public void tearDown() {
        if (webDriver != null)
            webDriver.quit();
    }
}
