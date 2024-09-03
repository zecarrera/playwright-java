package scenarios;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import playwright.pages.AdminPage;
import utils.TestBase;

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

        Locator logoutMenuItem = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout"));
        assertThat(logoutMenuItem).isVisible();
    }

    @Test
    void cantLoginWithInvalidCredentials(){
        adminPage.fillOutLoginAndSubmit("admin", "incorrect-password");

        Locator logoutMenuItem = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout"));
        assertThat(logoutMenuItem).isHidden();

        page.waitForCondition(() ->
                adminPage.usernameInput().evaluate("element => element.getAttribute('style')").toString()
                        .contains("border: 1px solid red;")
        );
    }
}
