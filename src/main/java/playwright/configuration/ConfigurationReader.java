package playwright.configuration;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {
    private final Properties properties;

    public ConfigurationReader() {
        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("configuration.properties");
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("configuration.properties file not found in the classpath");
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser", "chromium");
    }

    public String getEnvironment() {
        return properties.getProperty("environment", "development");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "true"));
    }

    public String getBaseUrl() throws ConfigurationException {

        switch (this.getEnvironment()){
            case "development": return "https://automationintesting.online/";
            case "test": return "https://automationintesting.test.online/";
            case "local": return "http://localhost:3000/";
            default: throw new ConfigurationException("Couldn't retrieve baseUrl for the selected environment:" + this.getEnvironment());
        }

    }
}
