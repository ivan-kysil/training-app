package ikysil.training.ws.ui.v1.pages.RMN;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RMNAboutPage {

    private By logoLocator = By.cssSelector("#header > div.header-holder > div > strong > a");

    private final WebDriver driver;

    public RMNAboutPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLogoHref(){
        return driver.findElement(logoLocator).getAttribute("href");
    }

    public String getURL(){
        return driver.getCurrentUrl();
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
