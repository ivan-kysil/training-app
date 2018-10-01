package ikysil.training.ws.ui.v1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GithubHomePage {

    private By userDropdownLocator = By.xpath("//*[@id=\"user-links\"]/li[3]");
    private By dropdownSignoutLocator = By.className("dropdown-signout");
    private By usernameLocator = By.className("header-nav-current-user");

    private final WebDriver driver;

    public GithubHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void dropDownProfile(){
        driver.findElement(userDropdownLocator).click();
    }

    public GithubWelcomePage signOut(){
        dropDownProfile();
        driver.findElement(dropdownSignoutLocator).click();
        return new GithubWelcomePage(driver);
    }

    public String getUsernameText(){
        return driver.findElement(usernameLocator).getText();
    }
}
