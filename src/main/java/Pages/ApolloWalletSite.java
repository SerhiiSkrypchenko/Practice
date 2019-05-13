package Pages;

import org.openqa.selenium.WebDriver;

public class ApolloWalletSite {
    WebDriver webDriver;

    public ApolloWalletSite(WebDriver driver){
        webDriver = driver;
    }

    public DashboardPage mainPage() {return new DashboardPage(webDriver); }

    public LoginPage loginPage() {return new LoginPage(webDriver); }


}
