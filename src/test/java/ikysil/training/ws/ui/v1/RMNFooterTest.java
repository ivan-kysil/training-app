package ikysil.training.ws.ui.v1;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import ikysil.training.ws.ui.v1.pages.rmn.RMNPage;
import ikysil.training.ws.ui.v1.pages.rmn.impl.RMNAboutPage;
import ikysil.training.ws.ui.v1.pages.rmn.impl.RMNBlogPage;
import ikysil.training.ws.ui.v1.pages.rmn.RMNHomePage;
import ikysil.training.ws.ui.v1.pages.rmn.impl.RMNPrivatePolicyPage;
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

    private final String RMN_URL = "https://www.retailmenot.com";

    private RMNHomePage rmnPage = new RMNHomePage(cdriver);

    @Before
    public void setUp() {
        cdriver.get(RMN_URL);
    }

    @Test
    public void correctAboutHrefTest() {
        siteComplianceVerification("about", rmnPage.moveToAboutPage());
    }

    @Test
    public void correctBlogHrefTest() {
        siteComplianceVerification("blog", rmnPage.moveToBlogPage());
    }

    @Test
    public void correctPrivacyPolicyHrefTest() {
        siteComplianceVerification("private policy", rmnPage.moveToPrivacyPolicyPage());
    }

    private void siteComplianceVerification(String topic,  RMNPage page){
        test = extent.startTest("Test '" + topic +"' URL");
        test.log(LogStatus.INFO, topic + " page is open");
        assertThat(page.getTitle())
                .contains(page.getExpextedTitle());
        test.log(LogStatus.PASS, "Correct title");
        assertThat(page.getURL())
                .isEqualTo(RMN_URL + page.getExpextedURL());
        test.log(LogStatus.PASS, "Correct url");
        assertThat(page.getLogoHref())
                .isEqualTo(RMN_URL + page.getExpextedLogoHref());
        test.log(LogStatus.PASS, "Correct logo href");
        test.log(LogStatus.PASS, "Correct '" + topic + "' href");
    }

    @After
    public void close(){
        extent.endTest(test);
        cdriver.close();
        extent.flush();
    }
}
