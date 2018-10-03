package ikysil.training.ws.ui.v1.pages.rmn.impl;

import ikysil.training.ws.ui.v1.pages.rmn.RMNPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RMNAboutPage implements RMNPage {

    private By logoLocator = By.cssSelector("#header > div.header-holder > div > strong > a");

    private final WebDriver driver;

    public RMNAboutPage(WebDriver driver) {
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
        return "/corp/";
    }

    @Override
    public String getExpextedURL() {
        return "/corp/";
    }

    @Override
    public String getExpextedTitle() {
        return "RetailMeNot, Inc - Home";
    }
}
