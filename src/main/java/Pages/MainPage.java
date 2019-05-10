package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(css = "div[class='user-box']")
    WebElement accountIconBtn;
    @FindBy(css = "div.image-button > label")
    WebElement logOutBtn;
    @FindBy(css = "div[class=\"message\"]")
    WebElement notification;
    @FindBy(className = "user-account-rs")
    WebElement userAccountRsBtn;





    public MainPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);
        PageFactory.initElements(webDriver, this);
    }

    public void clickAccountIconBtn() {
        accountIconBtn.click();
    }

    public void clickOnNotification() throws Exception {
        if(!webDriver.findElements(By.cssSelector("div[class=\"message\"]")).isEmpty()){
            notification.click();
        }
        Thread.sleep(3000);
    }

    public void clickLogoutBtn() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.image-button > label")));
        Thread.sleep(1000);
        logOutBtn.click();
        Thread.sleep(3000);
    }

    public void verifyUserAccountRs(String userAccountRs) {
        Assert.assertTrue(userAccountRsBtn.getText().equals(userAccountRs));
    }

}
