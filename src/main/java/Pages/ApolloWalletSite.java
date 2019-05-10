package Pages;

import org.openqa.selenium.WebDriver;

public class ApolloWalletSite {
    WebDriver webDriver;

    public ApolloWalletSite(WebDriver driver){
        webDriver = driver;
    }

    public MainPage mainPage() {return new MainPage(webDriver); }

    public LoginPage loginPage() {return new LoginPage(webDriver); }


}
