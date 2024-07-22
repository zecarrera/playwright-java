package playwright.configuration;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightManager {
    protected Browser browser;
    protected Page page;
    private static ConfigurationReader configuration;


    public ConfigurationReader initialiseConfiguration(){
        if (configuration == null) {
            configuration = new ConfigurationReader();
        }
        return configuration;
    }

    /**
     * Identifies configuration browserType and launches a new browser
     *
     */
    public Browser getBrowser(String browserName) {
        if (browser == null) {
            BrowserType browserType;
            switch (browserName.toLowerCase()) {
                case "firefox":
                    browserType = Playwright.create().firefox();
                    break;
                case "webkit":
                    browserType = Playwright.create().webkit();
                    break;
                case "chromium":
                default:
                    browserType = Playwright.create().chromium();
            }

            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.headless = configuration.isHeadless();
            browser = browserType.launch(options);
        }
        return browser;
    }

    /**
     * Returns existing Page or if null creates a new page context
     *
     */
    public Page getPage() {
        if (page == null) {
            Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
            page = browser.newContext(contextOptions).newPage();
        }
        return page;
    }

    /**
     * Instantiates a new page context
     *
     */
    public Page newPageContext(){
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        page = browser.newContext(contextOptions).newPage();
        return page;
    }
}
