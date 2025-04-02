# playwright-java

## Summary
This repo contains an example end-to-end test framework using:
- Playwright - e2e test framework
- Testng - Test runner
- Java
- Maven
- GitHub actions

## Running tests 

### Locally

Running from the terminal:

```shell
# Set Environment variable:  
export ENVIRONMENT=local 
# Runs tests with firefox
mvn test -Dbrowser=firefox
```

If you want to run on headless mode, modify the `.local.env` file, so that `HEADLESS` is set to `true`

Screenshots are captured to `target/screenshots` if any tests fail.

### Pipeline

Tests are automatically triggered using GitHub actions when:
- PR is raised/updated
- Changes are pushed to the main branch
- Tests are configured to run in parallel and on firefox, chromium and webkit
- Static code validation is executed

## Static code analysis

PMD source code analyzer is configured to run and produce a report on CI

It can also be run from the terminal:

`mvn pmd:check`

## Application under test  
Tests are written against the [Restful-booker-platform](https://github.com/mwinteringham/restful-booker-platform), from [Mark Winteringham](https://github.com/mwinteringham)

## Structure overview

### Test Scenarios
Location: `test/java/scenarios`  
Description: Test files per feature extending from [TestBase Class](`test/java/utils/TestBase`)  

**Example:**
```java
    @Test
    void canLoginWithValidCredentials() {
        adminPage.fillOutLoginAndSubmit("admin", "password");

        assertThat(adminPage.logoutButton()).isVisible();

        assertThat(page).hasURL(Pattern.compile(".*/admin/rooms.*"));
    }
```

### Test Base
Location: `test/java/helpers/TestBase`  
Description: Implements initialisation and tear down behaviour to be applied to all tests.  
- BeforeClass: Initialises playwright manager and browser
- BeforeMethod: Creates a new PageContext when requested
- AfterMethod: Captures screenshot when test fails
- AfterClass: Ensures browser is closed

### Page Model
Location: `main/java/playwright/pages`  
Description: Implements logic to interact with elements and specifies the required locators.

Example:

```java
   private Locator usernameInput() {
        return page.locator("#username");
    }
    private Locator passwordInput() {
        return page.locator("#password");
    }
    private Locator loginButton() {
        return page.locator("role=button[name='Login']");
    }
    
    public void fillOutLoginAndSubmit(String username, String password){
        usernameInput().fill(username);
        passwordInput().fill(password);
        loginButton().click();
    }
```

### Models and Enums
Location: `main/java/playwright/[enums|models]`  
Description: Contains object models to define some components in a structured manner. 

### Playwright Configuration
Location: `main/java/playwright/configuration`  
Description: Implements playwright's initialisation, providing methods to read the specified configuration, launch the selected browser and manage page/context.
- ConfigurationReader: Uses dotenv to load the environment variables from `/resources/*.env` file
- PlaywrightManager: Initialises configuration and browser.




