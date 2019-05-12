package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(css = "a[class='form-tab']")
    WebElement secretPhraseBtn;
    @FindBy(css = "input[type=\"password\"]")
    WebElement password;
    @FindBy(css = "button.btn")
    WebElement submit;
    @FindBy(css = "[placeholder='Account ID']")
    WebElement accountId;
    @FindBy (css = "[class=\"button-block\"]:nth-child(3)")
    WebElement newUserBtn;



    public LoginPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);
        PageFactory.initElements(webDriver, this);
    }

    public void switchToLoginBySecretPhrase (){
        secretPhraseBtn.click();
    }

    public void clickNewUserBtn (){
        newUserBtn.click();
    }

    public void enterSecretPhrase (String secretphrase) {
        password.clear();
        password.sendKeys(secretphrase);
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
        Assert.assertTrue(webDriver.findElement(By.cssSelector("p[class='sub-title']")).getText().equals("APOLLO COMMAND CENTER"));
        Assert.assertTrue(webDriver.findElement(By.cssSelector("img")).getAttribute("alt").equals("Apollo"));
        Assert.assertTrue(webDriver.findElement(By.cssSelector("div.left-section p.title")).getText().contains("Apollonaut!"));
        Assert.assertTrue(webDriver.findElement(By.cssSelector("[class=\"button-block\"]:nth-child(3) span[class='title']")).getText().equals("NEW USER?"));
        Assert.assertTrue(webDriver.findElement(By.cssSelector("[class=\"button-block\"]:nth-child(3) span[class='sub-title']")).getText().equals("Create Apollo Wallet"));
    }

    public void checkErrorNotification(String message) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h4[class='title']")));
        Thread.sleep(1000);
        Assert.assertTrue(webDriver.findElement(By.cssSelector("h4[class='title']")).getText().equals("Error"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='message']")));
        Assert.assertTrue(webDriver.findElement(By.cssSelector("div[class='message']")).getText().equals(message));
    }

}
