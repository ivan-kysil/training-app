package ikysil.training.ws.ui.v1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GithubLogInPage {

    By usernameLocator = By.id("login_field");
    By passwordLocator = By.id("password");
    By signInBtnLocator = By.name("commit");
    By errrorLocator = By.id("js-flash-container");

    private final WebDriver driver;

    public GithubLogInPage(WebDriver driver) {
        this.driver = driver;
    }

    public GithubLogInPage typeUsername(String username){
        driver.findElement(usernameLocator).sendKeys(username);
        return this;
    }

    public GithubLogInPage typePassword(String password){
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public GithubHomePage submitLogIn(){
        driver.findElement(signInBtnLocator).click();
        return new GithubHomePage(driver);
    }

    public GithubLogInPage submitLoginExpectingFailure(){
        driver.findElement(signInBtnLocator).click();
        return new GithubLogInPage(driver);
    }

    public GithubHomePage loginAs(String username, String password){
        typeUsername(username);
        typePassword(password);
        return submitLogIn();
    }

    public String getErrorText(){
        return driver.findElement(errrorLocator).getText();
    }

}
