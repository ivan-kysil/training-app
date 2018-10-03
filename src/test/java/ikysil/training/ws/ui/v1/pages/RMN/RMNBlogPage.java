package ikysil.training.ws.ui.v1.pages.RMN;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RMNBlogPage {

    private By logoLocator = By.cssSelector("body > header > div.bottom > div > a");

    private final WebDriver driver;

    public RMNBlogPage(WebDriver driver) {
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
