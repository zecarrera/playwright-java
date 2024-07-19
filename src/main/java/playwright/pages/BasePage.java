package playwright.pages;

import playwright.configuration.ConfigurationReader;

public class BasePage {

    protected String baseUrl;
    public BasePage()  {
        baseUrl = new ConfigurationReader().getBaseUrl();
    }
}
