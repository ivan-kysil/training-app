package ikysil.training.ws.ui.v1.pages.Girhub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GithubHomePage {

    private By userDropdownLocator = By.cssSelector("#user-links > li:nth-child(3)");
    private By dropdownSignoutLocator = By.cssSelector("button.dropdown-item.dropdown-signout");
    private By usernameLocator = By.cssSelector("li.header-nav-current-user.css-truncate");

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
