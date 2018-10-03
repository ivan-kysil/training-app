package ikysil.training.ws.ui.v1.pages.RMN;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RMNPrivatePolicyPage {

    private By logoLocator = By.cssSelector("#logo");

    private final WebDriver driver;

    public RMNPrivatePolicyPage(WebDriver driver) {
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
