package scenarios;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import playwright.pages.AdminPage;
import utils.TestBase;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Given user navigates to login page
 * When entering credentials
 * Then only valid users successfully login
 */
public class LoginTests extends TestBase {

    AdminPage adminPage;

    public LoginTests() {
        super(true);
    }

    @BeforeMethod
    void beforeEach() {
        adminPage = new AdminPage(page);
        adminPage.navigateToLoginPage();
    }

    @Test
    void canLoginWithValidCredentials() {
        adminPage.fillOutLoginAndSubmit("admin", "password");

        assertThat(adminPage.logoutButton()).isVisible();

        assertThat(page).hasURL(Pattern.compile(".*/admin/rooms.*"));
    }

    @Test
    void cantLoginWithInvalidCredentials(){
        adminPage.fillOutLoginAndSubmit("admin", "incorrect-password");

        assertThat(adminPage.loginErrorMessage()).containsText("Invalid credentials-FAIL");
    }
}
