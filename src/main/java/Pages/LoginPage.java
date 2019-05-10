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



    public LoginPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);
        PageFactory.initElements(webDriver, this);
    }

    public void switchToLoginBySecretPhrase (){
        secretPhraseBtn.click();
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

    public void clickSubmitBtn () throws InterruptedException {
        submit.click();
        Thread.sleep(5000);
    }

    public void pressEnterBtn (){
        submit.sendKeys(Keys.RETURN);

    }

    public void waitForLoginPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='sub-title']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img.logo")));
        Assert.assertTrue(webDriver.findElement(By.cssSelector("p[class='sub-title']")).getText().equals("APOLLO COMMAND CENTER"));
        Assert.assertTrue(webDriver.findElement(By.cssSelector("img")).getAttribute("alt").equals("Apollo"));
        Assert.assertTrue(webDriver.findElement(By.cssSelector("div.left-section p.title")).getText().contains("Apollonaut!"));
    }

    public void checkErrorNotification() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h4[class='title']")));
        Thread.sleep(1000);
        Assert.assertTrue(webDriver.findElement(By.cssSelector("h4[class='title']")).getText().equals("Error"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='message']")));
        Assert.assertTrue(webDriver.findElement(By.cssSelector("div[class='message']")).getText().equals("Incorrect \"account\""));
    }

}
