package ikysil.training.ws.ui.v1;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import ikysil.training.ws.ui.v1.pages.RMN.RMNAboutPage;
import ikysil.training.ws.ui.v1.pages.RMN.RMNBlogPage;
import ikysil.training.ws.ui.v1.pages.RMN.RMNHomePage;
import ikysil.training.ws.ui.v1.pages.RMN.RMNPrivatePolicyPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class RMNFooterTest {

    private static ExtentReports extent = new ExtentReports(System.getProperty("user.dir") + "/src/test/resources/Reports/RMNFooterTest.html", true);

    private ExtentTest test;
    private WebDriver cdriver = new ChromeDriver();

    private final String RMN_URL = "https://www.retailmenot.com/";

    private RMNHomePage rmnPage = new RMNHomePage(cdriver);

    @Before
    public void setUp() {
        cdriver.get(RMN_URL);
    }

    @Test
    public void correctAboutHrefTest() {
        test = extent.startTest("Test 'about' URL");
        test.log(LogStatus.INFO, "Test is initiated");
        RMNAboutPage aboutPage = rmnPage.moveToAboutPage();
        test.log(LogStatus.INFO, "About page is open");
        assertThat(aboutPage.getTitle())
                .isEqualTo("RetailMeNot, Inc - Home");
        test.log(LogStatus.PASS, "Correct title");
        assertThat(aboutPage.getURL())
                .isEqualTo(RMN_URL + "corp/");
        test.log(LogStatus.PASS, "Correct url");
        assertThat(aboutPage.getLogoHref())
                .isEqualTo(RMN_URL + "corp/");
        test.log(LogStatus.PASS, "Correct logo href");
        test.log(LogStatus.PASS, "Correct 'about' URL");
    }

    @Test
    public void correctBlogHrefTest() {
        test = extent.startTest("Test 'blog' URL");
        test.log(LogStatus.INFO, "Test is initiated");
        RMNBlogPage blogPage = rmnPage.moveToBlogPage();
        test.log(LogStatus.INFO, "Blog page is open");
        assertThat(blogPage.getTitle())
                .contains("The Real Deal by RetailMeNot");
        test.log(LogStatus.PASS, "Correct title");
        assertThat(blogPage.getURL())
                .isEqualTo(RMN_URL + "blog/");
        test.log(LogStatus.PASS, "Correct url");
        assertThat(blogPage.getLogoHref())
                .isEqualTo(RMN_URL + "blog/");
        test.log(LogStatus.PASS, "Correct logo href");
        test.log(LogStatus.PASS, "Correct 'blog' URL");
    }

    @Test
    public void correctPrivacyPolicyHrefTest() {
        test = extent.startTest("Test 'private policy' URL");
        test.log(LogStatus.INFO, "Test is initiated");
        RMNPrivatePolicyPage policyPage = rmnPage.moveToPrivacyPolicyPage();
        test.log(LogStatus.INFO, "Private Policy page is open");
        assertThat(policyPage.getTitle())
                .isEqualTo("Privacy Policy");
        test.log(LogStatus.PASS, "Correct title");
        assertThat(policyPage.getURL())
                .isEqualTo(RMN_URL + "static/privacy/");
        test.log(LogStatus.PASS, "Correct url");
        assertThat(policyPage.getLogoHref())
                .isEqualTo(RMN_URL);
        test.log(LogStatus.PASS, "Correct logo href");
        test.log(LogStatus.PASS, "Correct 'about' href");
    }

    @After
    public void close(){
        extent.endTest(test);
        cdriver.close();
        extent.flush();
    }
}
