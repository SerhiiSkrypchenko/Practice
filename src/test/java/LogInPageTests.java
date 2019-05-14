import Pages.ApolloWalletSite;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogInPageTests {
    WebDriver webDriver;
    WebDriverWait wait;
    ApolloWalletSite website;
    String url;

    @BeforeEach
    public void setUp () throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, 30, 500);
        website = new ApolloWalletSite(webDriver);
        url = "http://localhost:7876/";

        System.out.println("Step 1: Open " + url + " and click on notification if it is present");
        webDriver.get(url);
        website.dashboardPage().clickOnNotification();
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("Log In by Secret Phrase")
    @Order(1)
    public void testLogInBySecretPhrase () throws Exception {
        System.out.println("Step 2: Switch to Login by Secret Phrase");
        website.loginPage().switchToNonActive();

        System.out.println("Step 3: Enter Secret Phrase data");
        website.loginPage().enterSecretPhrase("0");

        System.out.println("Step 4: Click on Submit Button");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 5: Check URL = dashboard");
        Thread.sleep(2000);
        assertTrue(webDriver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    @DisplayName("NEGATIVE: Log in without any password (empty field)")
    @Order(5)
    public void testLogInByEmptySecretPhraseNegative() throws InterruptedException {
        System.out.println("Step 2: Switch to Login by Secret Phrase");
        website.loginPage().switchToNonActive();
        System.out.println("Step 3: Click on Submit Button");
        website.loginPage().clickSubmitBtn();
        System.out.println("Step 4: Check that Error Notification Secret Phrase is required is present");
        website.loginPage().checkErrorNotification("Secret Phrase is required.");
    }

    @Test
    @DisplayName("Log In by Account ID")
    @Order(2)
    public void testLogInByAccountID() throws InterruptedException {
        System.out.println("Step 2: Log In by Account ID");
        website.loginPage().enterAccountID("AHWS-NGBG-V4LK-8Q65T");
        System.out.println("Step 3: Click on Submit Button");
        website.loginPage().clickSubmitBtn();
        Thread.sleep(3000);
        System.out.println("Step 4: Verify User Account Rs");
        website.dashboardPage().clickAccountIconBtn();
        website.dashboardPage().verifyUserAccountRsInfo("APL-AHWS-NGBG-V4LK-8Q65T");
        System.out.println("Step 5: Log out");
        website.dashboardPage().clickLogoutBtn();
        System.out.println("Step 6: Check Log In Page");
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("NEGATIVE: Log In by Invalid Account ID")
    @Order(6)
    public void testLogInByAccountIDNegative() throws InterruptedException {
        System.out.println("Step 2: Log In by Invalid Account ID");
        website.loginPage().enterAccountID("negativetest");
        System.out.println("Step 3: Press ENTER button");
        website.loginPage().pressEnterBtn();
        System.out.println("Step 4: Check that Error Notification is present");
        website.loginPage().checkErrorNotification("Incorrect \"account\"");

    }

    @Test
    @DisplayName("NEGATIVE: Log In without any Account ID")
    @Order(7)
    public void testLogInByEmptyAccountIDNegative() throws InterruptedException {
        System.out.println("Step 2: Click on Submit Button");
        website.loginPage().clickSubmitBtn();
        System.out.println("Step 3: Check that Error Notification is present");
        website.loginPage().checkErrorNotification("Account ID is required.");
    }

    @Test
    @DisplayName("Log Out")
    @Order(3)
    public void testLogOut () throws Exception {
        System.out.println("Step 2: Switch to Login by Secret Phrase");
        website.loginPage().switchToNonActive();

        System.out.println("Step 3: Enter Secret Phrase data");
        website.loginPage().enterSecretPhrase("0");

        System.out.println("Step 4: Click on Submit Button");
        website.loginPage().clickSubmitBtn();
        Thread.sleep(3000);

        System.out.println("Step 5: Press on Account Icon");
        website.dashboardPage().clickAccountIconBtn();

        System.out.println("Step 6: Click on Log out button");
        website.dashboardPage().clickLogoutBtn();

        System.out.println("Step 7: Wait for Login Page is present");
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("NEGATIVE: Press BACKSPACE button after logging out")
    @Order(2)
    public void testBackSpaceBtn () throws Exception {
        System.out.println("Step 2: Switch to Login by Secret Phrase");
        website.loginPage().switchToNonActive();

        System.out.println("Step 3: Enter Secret Phrase data");
        website.loginPage().enterSecretPhrase("0");

        System.out.println("Step 4: Click on Submit Button");
        website.loginPage().clickSubmitBtn();
        Thread.sleep(3000);

        System.out.println("Step 5: Press on Account Icon");
        website.dashboardPage().clickAccountIconBtn();

        System.out.println("Step 6: Click on Log out button");
        website.dashboardPage().clickLogoutBtn();

        System.out.println("Step 7: Wait for Login Page is present");
        website.loginPage().verifyLoginPage();

        System.out.println("Step 8: Press BACKSPACE button");
        webDriver.navigate().back();

        System.out.println("Step 9: Wait for Login Page is present");
        website.loginPage().verifyLoginPage();

    }

    @Test
    @DisplayName("Close Modal Window")
    @Order(4)
    public void testExitModalWindowBtn () throws Exception {
        System.out.println("Step 2: Click on Create New User? button");
        website.loginPage().clickNewUserBtn();
        Thread.sleep(1000);

        System.out.println("Step 3: Click on Exit button");
        website.loginPage().clickCloseModalWindow();

        System.out.println("Step 4: Check that modal window is closed");
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("Create Vault Wallet with randomly generated secret phrase")
    @Order(9)
    public void testCreateVaultWalletRandomlyGeneratedSecretPhrase() throws InterruptedException {
        String secretPhrase;
        String accountId;
        System.out.println("Step 2: Click on Create New User? button");
        website.loginPage().clickNewUserBtn();

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

        System.out.println("Step 6: Click on CheckBox");
        website.loginPage().clickCheckboxDataStored();

        System.out.println("Step 7: Click on Next Button");
        website.loginPage().clickNextBtn();

        System.out.println("Step 8: Input Secret Phrase");
        website.loginPage().enterSecretPhrase(secretPhrase);

        System.out.println("Step 9: Press Create New Account button ");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 10: Verify User Account Rs");
        website.dashboardPage().clickAccountIconBtn();
        website.dashboardPage().verifyUserAccountRsInfo(accountId);

        System.out.println("Step 11: Do Log out");
        website.dashboardPage().clickLogoutBtn();

        System.out.println("Step 12: Check Log In Page");
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("NEGATIVE: VAULT WALLET -> Error message is present when User didn't store private data")
    @Order(11)
    public void testCreateVaultWalletVerifyStoreDataNegative() throws Exception {
        System.out.println("Step 2: Click on Create New User? button");
        website.loginPage().clickNewUserBtn();

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

        System.out.println("Step 8: Click on Next Button");
        website.loginPage().clickNextBtn();

        System.out.println("Step 9: Check that error message is present");
        website.loginPage().checkErrorNotification("You have to verify that you stored your private data");
        website.dashboardPage().clickOnNotification();
        Thread.sleep(1000);

        System.out.println("Step 10: Close modal window");
        website.loginPage().clickCloseModalWindow();

        System.out.println("Step 11: Verify Log In page");
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("Create Vault Wallet with custom secret phrase")
    @Order(10)
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
        website.dashboardPage().clickAccountIconBtn();
        website.dashboardPage().verifyUserAccountRsInfo(accountId);

        System.out.println("Step 11: Do Log out");
        website.dashboardPage().clickLogoutBtn();

        System.out.println("Step 12: Check Log In Page");
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("NEGATIVE: VAULT WALLET -> Error message is present when User enter invalid secret phrase")
    @Order(12)
    public void testCreateVaultWalletInvalidSecretPhraseNegative() throws Exception {
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
        Thread.sleep(2000);

        System.out.println("Step 6: Click on CheckBox");
        website.loginPage().clickCheckboxDataStored();

        System.out.println("Step 7: Click on Next Button");
        website.loginPage().clickNextBtn();
        Thread.sleep(2000);

        System.out.println("Step 8: Input Invalid Secret Phrase");
        website.loginPage().enterSecretPhrase("invalid secret phrase");
        Thread.sleep(2000);

        System.out.println("Step 9: Press Create New Account button ");
        website.loginPage().clickSubmitBtn();
        Thread.sleep(2000);

        System.out.println("Step 10: Verify Error message is shown");
        website.loginPage().checkErrorNotification("Incorrect secret phrase!");
        website.dashboardPage().clickOnNotification();

        System.out.println("Step 11: Close modal window");
        website.loginPage().clickCloseModalWindow();

        System.out.println("Step 12: Verify Log In page");
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("Create Standard Wallet")
    @Order(13)
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
        website.dashboardPage().clickAccountIconBtn();
        website.dashboardPage().verifyUserAccountRsInfo(accountId);

        System.out.println("Step 11: Do Log out");
        website.dashboardPage().clickLogoutBtn();

        System.out.println("Step 12: Check Log In Page");
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("NEGATIVE: STANDARD WALLET -> Error message is present when User enter invalid secret phrase")
    @Order(14)
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
    @DisplayName("NEGATIVE: STANDARD WALLET -> Error message is present when User didn't store private data")
    @Order(15)
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
    @DisplayName("Import Secret File")
    @Order(16)
    public void testImportFile() throws Exception {
        System.out.println("Step 2: Delete file if it exists");
        website.loginPage().deleteFile("C:\\Users\\skrypchenko\\.apl-blockchain\\apl-blockchain-vault-keystore\\a2e9b9\\", "APL-AHWS-NGBG-V4LK-8Q65T");

        System.out.println("Step 3: Click on Import Vault Wallet button");
        website.loginPage().clickImportVaultWalletBtn();

        System.out.println("Step 4: Verify IMPORT ACCOUNT Page (SECRET KEY)");
        website.loginPage().verifyImportAccountSecretKeyPage();

        System.out.println("Step 5: Switch to SECRET FILE IMPORT");
        website.loginPage().switchToNonActive();

        System.out.println("Step 6: Verify IMPORT ACCOUNT Page (SECRET FILE)");
        website.loginPage().verifyImportAccountSecretFilePage();

        System.out.println("Step 7: Input Correct Secret Phrase");
        website.loginPage().enterSecretPhrase("11");

        System.out.println("Step 8: Import correct file");
        website.loginPage().importFile("C:\\FilesForImport\\APL-AHWS-NGBG-V4LK-8Q65T");

        System.out.println("Step 9: Click on RESTORE ACCOUNT button");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 10: Verify successful message");
        website.loginPage().checkSuccessfullMessageImported("Your account imported successfully!");
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("NEGATIVE: Import valid Secret File + Invalid secret phrase")
    @Order(17)
    public void testImportInvalidSecretPhrase() throws Exception {
        System.out.println("Step 2: Delete file if it exists");
        website.loginPage().deleteFile("C:\\Users\\skrypchenko\\.apl-blockchain\\apl-blockchain-vault-keystore\\a2e9b9\\", "APL-AHWS-NGBG-V4LK-8Q65T");

        System.out.println("Step 3: Click on Import Vault Wallet button");
        website.loginPage().clickImportVaultWalletBtn();

        System.out.println("Step 4: Verify IMPORT ACCOUNT Page (SECRET KEY)");
        website.loginPage().verifyImportAccountSecretKeyPage();

        System.out.println("Step 5: Switch to SECRET FILE IMPORT");
        website.loginPage().switchToNonActive();

        System.out.println("Step 6: Verify IMPORT ACCOUNT Page (SECRET FILE)");
        website.loginPage().verifyImportAccountSecretFilePage();

        System.out.println("Step 7: Input Invalid Secret Phrase");
        website.loginPage().enterSecretPhrase("invalid secret phrase");

        System.out.println("Step 8: Import correct file");
        website.loginPage().importFile("C:\\FilesForImport\\APL-AHWS-NGBG-V4LK-8Q65T");

        System.out.println("Step 9: Click on RESTORE ACCOUNT button");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 10: Verify Error message");
        website.loginPage().checkErrorNotification("Vault wallet for account was not import : KeyStore or passPhrase is not valid.");
        Thread.sleep(3000);
    }

    @Test
    @DisplayName("NEGATIVE: Restore Account with empty data: NO FILE and NO Secret Phrase")
    @Order(18)
    public void testImportEmptyData() throws Exception {
        System.out.println("Step 2: Delete file if it exists");
        website.loginPage().deleteFile("C:\\Users\\skrypchenko\\.apl-blockchain\\apl-blockchain-vault-keystore\\a2e9b9\\", "APL-AHWS-NGBG-V4LK-8Q65T");

        System.out.println("Step 3: Click on Import Vault Wallet button");
        website.loginPage().clickImportVaultWalletBtn();

        System.out.println("Step 4: Verify IMPORT ACCOUNT Page (SECRET KEY)");
        website.loginPage().verifyImportAccountSecretKeyPage();

        System.out.println("Step 5: Switch to SECRET FILE IMPORT");
        website.loginPage().switchToNonActive();

        System.out.println("Step 6: Verify IMPORT ACCOUNT Page (SECRET FILE)");
        website.loginPage().verifyImportAccountSecretFilePage();

        System.out.println("Step 7: Click on RESTORE ACCOUNT button");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 10: Verify Error message");
        website.loginPage().checkErrorNotification("No file chosen");
        website.loginPage().clickCloseModalWindow();
        website.loginPage().verifyLoginPage();
    }

    @Test
    @DisplayName("NEGATIVE: Import INVALID Secret File + VALID secret phrase")
    @Order(19)
    public void testImportInvalidSecretFile() throws Exception {
        System.out.println("Step 2: Delete file if it exists");
        website.loginPage().deleteFile("C:\\Users\\skrypchenko\\.apl-blockchain\\apl-blockchain-vault-keystore\\a2e9b9\\", "APL-AHWS-NGBG-V4LK-8Q65T");

        System.out.println("Step 3: Click on Import Vault Wallet button");
        website.loginPage().clickImportVaultWalletBtn();

        System.out.println("Step 4: Verify IMPORT ACCOUNT Page (SECRET KEY)");
        website.loginPage().verifyImportAccountSecretKeyPage();

        System.out.println("Step 5: Switch to SECRET FILE IMPORT");
        website.loginPage().switchToNonActive();

        System.out.println("Step 6: Verify IMPORT ACCOUNT Page (SECRET FILE)");
        website.loginPage().verifyImportAccountSecretFilePage();

        System.out.println("Step 7: Input Invalid Secret Phrase");
        website.loginPage().enterSecretPhrase("11");

        System.out.println("Step 8: Import correct file");
        website.loginPage().importFile("C:\\FilesForImport\\APL-FQ3F-QZWM-RZ9X-7YNZ3");

        System.out.println("Step 9: Click on RESTORE ACCOUNT button");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 10: Verify Error message");
        website.loginPage().checkErrorNotification("Vault wallet for account was not import : KeyStore or passPhrase is not valid.");
    }

    @Test
    @DisplayName("NEGATIVE: Import Wrong Format Secret File + VALID secret phrase")
    @Order(20)
    public void testImportSecretFileWrongFormat() throws Exception {
        System.out.println("Step 2: Delete file if it exists");
        website.loginPage().deleteFile("C:\\Users\\skrypchenko\\.apl-blockchain\\apl-blockchain-vault-keystore\\a2e9b9\\", "APL-AHWS-NGBG-V4LK-8Q65T");

        System.out.println("Step 3: Click on Import Vault Wallet button");
        website.loginPage().clickImportVaultWalletBtn();

        System.out.println("Step 4: Verify IMPORT ACCOUNT Page (SECRET KEY)");
        website.loginPage().verifyImportAccountSecretKeyPage();

        System.out.println("Step 5: Switch to SECRET FILE IMPORT");
        website.loginPage().switchToNonActive();

        System.out.println("Step 6: Verify IMPORT ACCOUNT Page (SECRET FILE)");
        website.loginPage().verifyImportAccountSecretFilePage();

        System.out.println("Step 7: Input Invalid Secret Phrase");
        website.loginPage().enterSecretPhrase("11");

        System.out.println("Step 8: Import correct file");
        website.loginPage().importFile("C:\\FilesForImport\\test.txt");

        System.out.println("Step 9: Click on RESTORE ACCOUNT button");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 10: Verify Error message");
        website.loginPage().checkErrorNotification("Vault wallet for account was not import : Parameter 'passPhrase' or 'keyStore' is null");
    }

    @Test
    @DisplayName("NEGATIVE: Import Big Size Secret File + VALID secret phrase")
    @Order(21)
    public void testImportBigSizeSecretFile() throws Exception {
        System.out.println("Step 2: Delete file if it exists");
        website.loginPage().deleteFile("C:\\Users\\skrypchenko\\.apl-blockchain\\apl-blockchain-vault-keystore\\a2e9b9\\", "APL-AHWS-NGBG-V4LK-8Q65T");

        System.out.println("Step 3: Click on Import Vault Wallet button");
        website.loginPage().clickImportVaultWalletBtn();

        System.out.println("Step 4: Verify IMPORT ACCOUNT Page (SECRET KEY)");
        website.loginPage().verifyImportAccountSecretKeyPage();

        System.out.println("Step 5: Switch to SECRET FILE IMPORT");
        website.loginPage().switchToNonActive();

        System.out.println("Step 6: Verify IMPORT ACCOUNT Page (SECRET FILE)");
        website.loginPage().verifyImportAccountSecretFilePage();

        System.out.println("Step 7: Input Invalid Secret Phrase");
        website.loginPage().enterSecretPhrase("11");

        System.out.println("Step 8: Import correct file");
        website.loginPage().importFile("C:\\FilesForImport\\test.jpg");

        System.out.println("Step 9: Click on RESTORE ACCOUNT button");
        website.loginPage().clickSubmitBtn();

        System.out.println("Step 10: Verify Error message");
        website.loginPage().checkErrorNotification("error_secret_file_too_big");
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null)
            webDriver.quit();
    }
}
