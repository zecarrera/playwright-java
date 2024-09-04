package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.testng.ITestResult;
import org.testng.annotations.*;
import playwright.configuration.ConfigurationReader;
import playwright.configuration.PlaywrightManager;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBase {

    protected Browser browser;
    protected Page page;
    protected String baseUrl;

    private final boolean newContextForEachTest;
    PlaywrightManager playwrightManager = new PlaywrightManager();

    public TestBase(boolean newContext) {
        newContextForEachTest = newContext;
    }

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String browserName)  {
        ConfigurationReader configuration = playwrightManager.initialiseConfiguration();

        browser = playwrightManager.getBrowser(browserName);

        if (!newContextForEachTest) {
            page = playwrightManager.getPage();
        }

        baseUrl = configuration.getBaseUrl();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        if (newContextForEachTest) {
            page = playwrightManager.newPageContext();
        }
    }


    @AfterMethod(alwaysRun = true)
    public void captureScreenshotOnFailure(ITestResult result) {
        if (!result.isSuccess()) {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String screenshotName = result.getMethod().getMethodName() + "_" + timestamp + ".png";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target", "screenshots", screenshotName)).setFullPage(true));
            System.out.println("Screenshot taken for failed test: " + screenshotName);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
    }
}
