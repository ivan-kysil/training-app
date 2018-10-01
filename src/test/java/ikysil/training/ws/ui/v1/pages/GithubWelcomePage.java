package ikysil.training.ws.ui.v1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GithubWelcomePage{

    By noRegisterUserLocator = By.cssSelector("div.HeaderNavlink.px-0.py-2.m-0");

    private final WebDriver driver;

    public GithubWelcomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getNoUserText(){
        return driver.findElement(noRegisterUserLocator).getText();
    }
}
