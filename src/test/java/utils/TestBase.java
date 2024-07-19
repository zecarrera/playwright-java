package utils;

import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import playwright.configuration.ConfigurationReader;
import playwright.configuration.PlaywrightManager;

import javax.naming.ConfigurationException;
import java.nio.file.Paths;

public class TestBase {

    protected Browser browser;
    protected Page page;
    protected String baseUrl;

    private final boolean newContextForEachTest;
    PlaywrightManager playwrightManager = new PlaywrightManager();

    public TestBase(boolean newContext) {
        newContextForEachTest = newContext;
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() throws ConfigurationException {
        ConfigurationReader configuration = playwrightManager.initialiseConfiguration();

        browser = playwrightManager.getBrowser();

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
            String screenshotName = result.getMethod().getMethodName() + ".png";
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
