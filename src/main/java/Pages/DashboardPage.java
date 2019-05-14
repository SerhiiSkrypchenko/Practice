package Pages;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class DashboardPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy (css = "div[class='user-box']")
    WebElement accountIconBtn;
    @FindBy (xpath = "//label[text()=\"Logout\"]")
    WebElement logOutBtn;
    @FindBy (css = "div[class=\"message\"]")
    WebElement notification;
    @FindBy (className = "user-account-rs")
    WebElement userAccountRsBtn;
    @FindBy (css = "a[class='user-account-rs blue-text']")
    WebElement userAccountRsInfo;

    public DashboardPage(WebDriver driver) {
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
    }

    public void clickLogoutBtn() throws InterruptedException {
        wait.until(presenceOfElementLocated(By.cssSelector("div.image-button > label")));
        logOutBtn.click();
    }

    public void verifyUserAccountRs(String userAccountRs) {
        assertTrue(userAccountRsBtn.getText().equals(userAccountRs));
    }

    public void verifyUserAccountRsInfo(String userAccountRs) {
        wait.until(presenceOfElementLocated(By.className("user-account-rs")));
        wait.until(presenceOfElementLocated(By.cssSelector(".account-sub-titles:nth-child(2)")));
        assertTrue(userAccountRsBtn.getText().equals(userAccountRs));
        assertTrue(webDriver.findElement(By.cssSelector(".account-sub-titles:nth-child(2)")).getText().equals(userAccountRs));
    }

}
