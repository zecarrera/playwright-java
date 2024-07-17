package playwright.pages;

import playwright.configuration.ConfigurationReader;

import javax.naming.ConfigurationException;

public class BasePage {

    protected String baseUrl;
    public BasePage() throws ConfigurationException {
        baseUrl = new ConfigurationReader().getBaseUrl();
    }
}
