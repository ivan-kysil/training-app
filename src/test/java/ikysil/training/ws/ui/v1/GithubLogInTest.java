package ikysil.training.ws.ui.v1;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.*;


public class GithubLogInTest {

    private static ExtentReports extent = new ExtentReports("/Users/mkaza/projects/training-app/src/test/resources/Reports/GithubLogInTest.html", true);

    private ExtentTest test;
    private WebDriver cdriver = new ChromeDriver();

    private final String EMAIL = "studentlgotnik@gmail.com";
    private final String USERNAME_VALID = "BestOfTheBeast";
    private final String USERNAME_INVALID = "-Beast-";
    private final String USERNAME_EMPTY = "";
    private final String PASSWORD = "4everloveanimals";
    private final String PASSWORD_EMPTY = "";
    private final String PASSWORD_SHORT = "4ever";
    private final String PASSWORD_WITHOUT_NUMBERS = "everloveanimals";

    private final String GITHUB_URL = "https://github.com/login";

    @Before
    public void setUp(){
        cdriver.get(GITHUB_URL);
    }

    @Test
    public void shortPasswordTest(){
        test = extent.startTest("Attempting to enter short password");
        test.log(LogStatus.INFO, "Test is initiated");
        cdriver.findElement(By.id("login_field")).sendKeys(USERNAME_VALID);
        cdriver.findElement(By.id("password")).sendKeys(PASSWORD_SHORT);
        cdriver.findElement(By.name("commit")).click();
        test.log(LogStatus.INFO, "LogIn button is clicked");
        assertThat(cdriver.findElement(By.id("js-flash-container")).getText())
                .isEqualTo("Incorrect username or password.");
        test.log(LogStatus.PASS, "LogIn disapproved successfully");
    }

    @Test
    public void incorrectUsernameTest(){
        test = extent.startTest("Attempting to enter ivalid username");
        test.log(LogStatus.INFO, "Test is initiated");
        cdriver.findElement(By.id("login_field")).sendKeys(USERNAME_INVALID);
        cdriver.findElement(By.id("password")).sendKeys(PASSWORD);
        cdriver.findElement(By.name("commit")).click();
        test.log(LogStatus.INFO, "LogIn button is clicked");
        assertThat(cdriver.findElement(By.id("js-flash-container")).getText())
                .isEqualTo("Incorrect username or password.");
        test.log(LogStatus.PASS, "LogIn disapproved successfully");
    }

    @Test
    public void emptyUsernameAndPasswordTest(){
        test = extent.startTest("Attempting to enter empty password and username");
        test.log(LogStatus.INFO, "Test is initiated");
        cdriver.findElement(By.id("login_field")).sendKeys(USERNAME_EMPTY);
        cdriver.findElement(By.id("password")).sendKeys(PASSWORD_EMPTY);
        cdriver.findElement(By.name("commit")).click();
        test.log(LogStatus.INFO, "LogIn button is clicked");
        assertThat(cdriver.findElement(By.id("js-flash-container")).getText())
                .isEqualTo("Incorrect username or password.");
        test.log(LogStatus.PASS, "LogIn disapproved successfully");
    }

    @Test
    public void passwordWithoutNumberTest(){
        test = extent.startTest("Attempting to enter password without number");
        test.log(LogStatus.INFO, "Test is initiated");
        cdriver.findElement(By.id("login_field")).sendKeys(USERNAME_EMPTY);
        cdriver.findElement(By.id("password")).sendKeys(PASSWORD_WITHOUT_NUMBERS);
        cdriver.findElement(By.name("commit")).click();
        test.log(LogStatus.INFO, "LogIn button is clicked");
        assertThat(cdriver.findElement(By.id("js-flash-container")).getText())
                .isEqualTo("Incorrect username or password.");
        test.log(LogStatus.PASS, "LogIn disapproved successfully");
    }

    @Test
    public void usernameLogInOKTest(){
        test = extent.startTest("LogIn with username");
        test.log(LogStatus.INFO, "Test is initiated");
        cdriver.findElement(By.id("login_field")).sendKeys(USERNAME_VALID);
        cdriver.findElement(By.id("password")).sendKeys(PASSWORD);
        cdriver.findElement(By.name("commit")).click();
        test.log(LogStatus.INFO, "LogIn button is clicked");
        cdriver.findElement(By.xpath("//*[@id=\"user-links\"]/li[3]")).click();
        assertThat(cdriver.findElement(By.className("header-nav-current-user")).getText())
                .isEqualTo("Signed in as " + USERNAME_VALID);
        test.log(LogStatus.PASS, "LogIn successful");
    }

    @Test
    public void emailLogInOKTest(){
        test = extent.startTest("LogIn with email");
        test.log(LogStatus.INFO, "Test is initiated");
        cdriver.findElement(By.id("login_field")).sendKeys(EMAIL);
        cdriver.findElement(By.id("password")).sendKeys(PASSWORD);
        cdriver.findElement(By.name("commit")).click();
        test.log(LogStatus.INFO, "LogIn button is clicked");
        cdriver.findElement(By.xpath("//*[@id=\"user-links\"]/li[3]")).click();
        assertThat(cdriver.findElement(By.className("header-nav-current-user")).getText())
                .isEqualTo("Signed in as " + USERNAME_VALID);
        test.log(LogStatus.PASS, "LogIn successful");
    }

    @Test
    public void signOutOKTest(){
        test = extent.startTest("Verifying user sign out");
        test.log(LogStatus.INFO, "Test is initiated");
        cdriver.findElement(By.id("login_field")).sendKeys(USERNAME_VALID);
        cdriver.findElement(By.id("password")).sendKeys(PASSWORD);
        cdriver.findElement(By.name("commit")).click();
        test.log(LogStatus.PASS, "LogIn successful");
        cdriver.findElement(By.xpath("//*[@id=\"user-links\"]/li[3]")).click();
        cdriver.findElement(By.className("dropdown-signout")).click();
        test.log(LogStatus.INFO, "Sign out button is clicked");
        assertThat(cdriver.findElement(By.cssSelector("div.HeaderNavlink.px-0.py-2.m-0")).getText())
                .isEqualTo("Sign in or Sign up");
        test.log(LogStatus.PASS, "Sign out successful");
    }

    @After
    public void close(){
        extent.endTest(test);
        cdriver.close();
        extent.flush();
    }
}
