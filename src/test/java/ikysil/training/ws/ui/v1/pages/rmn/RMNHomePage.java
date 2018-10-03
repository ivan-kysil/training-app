package ikysil.training.ws.ui.v1.pages.rmn;

import ikysil.training.ws.ui.v1.pages.rmn.impl.RMNAboutPage;
import ikysil.training.ws.ui.v1.pages.rmn.impl.RMNBlogPage;
import ikysil.training.ws.ui.v1.pages.rmn.impl.RMNPrivatePolicyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RMNHomePage {

    private By AboutHrefLocator = By.cssSelector("div.site-footer-container > div.link-group-wrapper > div:nth-child(4) > ul > li:nth-child(1) > a");
    private By BlogHrefLocator = By.cssSelector("div.site-footer-container > div.link-group-wrapper > div:nth-child(4) > ul > li:nth-child(2) > a");
    private By PrivacyPolicyHrefLocator = By.cssSelector("div.site-footer-secondary-links > ul > li:nth-child(2) > a");

    private final WebDriver driver;

    public RMNHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public RMNAboutPage moveToAboutPage(){
        driver.findElement(AboutHrefLocator).click();
        return new RMNAboutPage(driver);
    }

    public RMNBlogPage moveToBlogPage(){
        driver.findElement(BlogHrefLocator).click();
        return new RMNBlogPage(driver);
    }

    public RMNPrivatePolicyPage moveToPrivacyPolicyPage(){
        driver.findElement(PrivacyPolicyHrefLocator).click();
        return new RMNPrivatePolicyPage(driver);
    }
}
