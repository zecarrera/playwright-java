package playwright.configuration;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigurationReader {
    Dotenv dotenv;

    public ConfigurationReader() {
        String ENVIRONMENT = System.getenv("ENVIRONMENT");
        String pathToEnvFile = "./src/main/resources/";

        dotenv = Dotenv.configure()
                .directory(pathToEnvFile)
                .filename(String.format(".%s.env", ENVIRONMENT))
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

    }

    public String getBrowser() { return dotenv.get("BROWSER");}

    public boolean isHeadless() { return Boolean.parseBoolean(dotenv.get("HEADLESS"));}

    public String getBaseUrl() { return dotenv.get("BASE_URL");}
}
