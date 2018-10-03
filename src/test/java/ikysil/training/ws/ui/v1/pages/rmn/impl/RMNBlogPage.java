package ikysil.training.ws.ui.v1.pages.rmn.impl;

import ikysil.training.ws.ui.v1.pages.rmn.RMNPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RMNBlogPage implements RMNPage {

    private By logoLocator = By.cssSelector("body > header > div.bottom > div > a");

    private final WebDriver driver;

    public RMNBlogPage(WebDriver driver) {
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
        return "/blog/";
    }

    @Override
    public String getExpextedURL() {
        return "/blog/";
    }

    @Override
    public String getExpextedTitle() {
        return "The Real Deal by RetailMeNot";
    }

}
