package Pages;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;


public class LoginPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(css = "a[class='form-tab']")
    WebElement nonActiveBtn;
    @FindBy(css = "input[type=\"password\"]")
    WebElement password;
    @FindBy(css = "button.btn")
    WebElement submit;
    @FindBy(css = "[placeholder='Account ID']")
    WebElement accountId;
    @FindBy (css = "[class=\"button-block\"]:nth-child(3)")
    WebElement newUserBtn;
    @FindBy (css = "form[class='tab-body active'] button")
    WebElement createAccountBtn;
    @FindBy (css = "input[value='false']")
    WebElement checkboxDataStored;
    @FindBy (css = "button[type='submit']")
    WebElement nextBtn;
    @FindBy (css = ".exit")
    WebElement closeModalWindowBtn;
    @FindBy (css = "[type=\'checkbox\']")
    WebElement checkboxCustomSecretPhrase;
    @FindBy (css = "textarea")
    WebElement customSecretPhrase;
    @FindBy (css = "[class=\"button-block\"]:nth-child(2)")
    WebElement importVaultWalletBtn;
    @FindBy (className = "upload-block")
    WebElement importFileBtn;
    @FindBy (css = "input[type='file']")
    WebElement uploadFileBtn;


    public LoginPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);

        PageFactory.initElements(webDriver, this);
    }

    public void switchToNonActive (){
        nonActiveBtn.click();
    }

    public void clickNewUserBtn (){ newUserBtn.click(); }

    public void clickImportVaultWalletBtn() {importVaultWalletBtn.click();}

    public void clickNextBtn() { nextBtn.click();}

    public void clickCheckboxDataStored() { checkboxDataStored.click(); }

    public void clickCheckBoxCustomSecretPhrase() {checkboxCustomSecretPhrase.click();}

    public void clickCreateAccountBtn (){ createAccountBtn.click(); }

    public void clickCloseModalWindow() {
        closeModalWindowBtn.click();
    }

    public void enterSecretPhrase (String secretphrase) {
        password.clear();
        password.sendKeys(secretphrase);
    }

    public void importFile (String filePath) {
        uploadFileBtn.sendKeys(filePath);
    }

    public void enterCustomSecretPhrase (String secretphrase) {
        customSecretPhrase.clear();
        customSecretPhrase.sendKeys(secretphrase);
    }

    public void enterAccountID (String accountID){

        accountId.clear();
        accountId.click();
        accountId.sendKeys(accountID);
    }

    public void clickSubmitBtn () {
        submit.click();
    }

    public void pressEnterBtn (){ submit.sendKeys(Keys.RETURN); }

    public void verifyLoginPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='sub-title']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img.logo")));
        assertTrue(webDriver.findElement(By.cssSelector("p[class='sub-title']")).getText().equals("APOLLO COMMAND CENTER"));
        assertTrue(webDriver.findElement(By.cssSelector("img")).getAttribute("alt").equals("Apollo"));
        assertTrue(webDriver.findElement(By.cssSelector("div.left-section p.title")).getText().contains("Apollonaut!"));
        assertTrue(webDriver.findElement(By.cssSelector("[class=\"button-block\"]:nth-child(3) span[class='title']")).getText().equals("NEW USER?"));
        assertTrue(webDriver.findElement(By.cssSelector("[class=\"button-block\"]:nth-child(3) span[class='sub-title']")).getText().equals("Create Apollo Wallet"));
        assertTrue(webDriver.findElement(By.cssSelector("[class=\"button-block\"]:nth-child(2) span[class='title']")).getText().equals("ADVANCED USER?"));
        assertTrue(webDriver.findElement(By.cssSelector("[class=\"button-block\"]:nth-child(2) span[class='sub-title']")).getText().equals("Import Vault Wallet"));
        assertTrue(webDriver.findElements(By.className("exit")).isEmpty());
    }

    public void checkErrorNotification(String message) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h4[class='title']")));
        (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.cssSelector("h4[class='title']")).getText().equals("Error");
            }
        });
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='message']")));
        assertTrue(webDriver.findElement(By.cssSelector("div[class='message']")).getText().equals(message));
    }

    public void checkSuccessfullMessageImported(String message) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='message']")));
        assertTrue(webDriver.findElement(By.cssSelector("div[class='message']")).getText().equals(message));
    }

    public void verifyCreateStandardWallet() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='info-box dark-info marked-list'] li:nth-child(1)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='info-box dark-info marked-list'] li:nth-child(2)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='info-box dark-info marked-list'] li:nth-child(3)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[class='tab-body active'] button")));
        assertTrue(webDriver.findElement(By.cssSelector("[class='info-box dark-info marked-list'] li:nth-child(1)")).getText().equals("You can log in to this wallet using only your secret phrase"));
        assertTrue(webDriver.findElement(By.cssSelector("[class='info-box dark-info marked-list'] li:nth-child(2)")).getText().contains("Available to use from any device"));
        assertTrue(webDriver.findElement(By.cssSelector("[class='info-box dark-info marked-list'] li:nth-child(3)")).getText().contains("2FA is available only on the device where it was enabled"));
        assertTrue(createAccountBtn.getText().equals("CREATE ACCOUNT"));
    }

    public void verifyCreateStandardWalletSecondStep() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("danger-icon")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.mb-3:nth-child(1)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.mb-3:nth-child(2)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.mb-3:nth-child(3)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='false']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));

        assertTrue(webDriver.findElement(By.className("danger-icon")).getText().equals("Remember to store your Account ID and secret phrase in a secured place. Make sure to write down this secret phrase and store it securely (the secret phrase is order and case sensitive). This secret phrase is needed to use your wallet."));
        assertTrue(webDriver.findElement(By.cssSelector("p.mb-3:nth-child(1)")).getText().equals("Your randomly generated secret phrase is:"));
        assertTrue(webDriver.findElement(By.cssSelector("p.mb-3:nth-child(2)")).getText().contains("Secret Phrase:"));
        assertTrue(webDriver.findElement(By.cssSelector("p.mb-3:nth-child(3)")).getText().contains("Account ID:"));
        assertTrue(nextBtn.getText().equals("NEXT"));
    }

    public String copySecretPhrase (){
        String secretPhrase = webDriver.findElement(By.cssSelector("p.mb-3:nth-child(2)")).getText().substring(15);
                return secretPhrase;
    }

    public String copyAccountId () {
        String accountId = webDriver.findElement(By.cssSelector("p.mb-3:nth-child(3)")).getText().substring(12);
                return accountId;
    }

    public String copyPublicKey () {
        String publicKey = webDriver.findElement(By.cssSelector("p.mb-3:nth-child(4)")).getText().substring(12);
                return publicKey;
    }

    public void verifyCreateVaultWallet() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li:nth-child(1)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li:nth-child(2)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li:nth-child(3)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li:nth-child(4)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li:nth-child(5)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li:nth-child(6)")));

        assertTrue(webDriver.findElement(By.cssSelector("li:nth-child(1)")).getText().equals("The most secure Apollo Wallet."));
        assertTrue(webDriver.findElement(By.cssSelector("li:nth-child(2)")).getText().equals("You can log in to this wallet using your Account ID."));
        assertTrue(webDriver.findElement(By.cssSelector("li:nth-child(3)")).getText().equals("The wallet is encrypted (via Secret File) on one device."));
        assertTrue(webDriver.findElement(By.cssSelector("li:nth-child(4)")).getText().equals("You can export/import your Secret File to use on other devices."));
        assertTrue(webDriver.findElement(By.cssSelector("li:nth-child(5)")).getText().equals("2FA works from any device when you use your Vault."));
        assertTrue(webDriver.findElement(By.cssSelector("li:nth-child(6)")).getText().equals("If you loose your device before export your Secret File you will not be able to access your wallet."));
        assertTrue(createAccountBtn.getText().equals("CREATE ACCOUNT"));
    }

    public void verifyCreateVaultWalletSecondStep() {
        verifyCreateStandardWalletSecondStep();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.mb-3:nth-child(4)")));
        assertTrue(webDriver.findElement(By.cssSelector("p.mb-3:nth-child(4)")).getText().contains("Public Key:"));
    }

    public void verifyCreateVaultWalletSecondStepCustomSecretPhrase() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("danger-icon")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.mb-3:nth-child(1)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.mb-3:nth-child(2)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.mb-3:nth-child(3)")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='false']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.mb-3:nth-child(4)")));

        assertTrue(webDriver.findElement(By.className("danger-icon")).getText().equals("Remember to store your Account ID and secret phrase in a secured place. Make sure to write down this secret phrase and store it securely (the secret phrase is order and case sensitive). This secret phrase is needed to use your wallet."));
        assertTrue(webDriver.findElement(By.cssSelector("p.mb-3:nth-child(1)")).getText().equals("Your secret phrase is:"));
        assertTrue(webDriver.findElement(By.cssSelector("p.mb-3:nth-child(2)")).getText().contains("Secret Phrase:"));
        assertTrue(webDriver.findElement(By.cssSelector("p.mb-3:nth-child(3)")).getText().contains("Account ID:"));
        assertTrue(nextBtn.getText().equals("NEXT"));
        assertTrue(webDriver.findElement(By.cssSelector("p.mb-3:nth-child(4)")).getText().contains("Public Key:"));
    }

    public void verifyImportAccountSecretKeyPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".danger-icon span")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form p.title")));

        assertTrue(webDriver.findElement(By.cssSelector(".danger-icon span")).getText().equals("Please enter your account secret key."));
        assertTrue(nextBtn.getText().equals("RESTORE ACCOUNT"));
        assertTrue(webDriver.findElement(By.cssSelector("form p.title")).getText().equals("IMPORT ACCOUNT"));
    }

    public void verifyImportAccountSecretFilePage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".danger-icon span")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form p.title")));


        assertTrue(webDriver.findElement(By.cssSelector(".danger-icon span")).getText().equals("Please notice that usage of the same vault wallet on different nodes will cause creation of different ETH, PAX, BTC wallets for each node."));
        assertTrue(nextBtn.getText().equals("RESTORE ACCOUNT"));
        assertTrue(webDriver.findElement(By.cssSelector("form p.title")).getText().equals("IMPORT ACCOUNT"));
        assertTrue(importFileBtn.getText().equals("Click or drag file to upload"));
    }

    public void deleteFile(String filepath, String filename) {
        File folder = new File(filepath);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                if (file.getName().contains(filename)) { file.delete();
                break;
                }
            }
        }
    }




}
