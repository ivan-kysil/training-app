package ikysil.training.ws.ui.v1.pages.rmn.impl;

import ikysil.training.ws.ui.v1.pages.rmn.RMNPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RMNPrivatePolicyPage implements RMNPage {

    private By logoLocator = By.cssSelector("#logo");

    private final WebDriver driver;

    public RMNPrivatePolicyPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public String getLogoHref(){
        return driver.findElement(logoLocator).getAttribute("href");
    }

    @Override
    public String getURL(){
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle(){
        return driver.getTitle();
    }

    @Override
    public String getExpextedLogoHref() {
        return "/";
    }

    @Override
    public String getExpextedURL() {
        return "/static/privacy/";
    }

    @Override
    public String getExpextedTitle() {
        return "Privacy Policy";
    }

}
