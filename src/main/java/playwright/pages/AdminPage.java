package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AdminPage extends BasePage{

    Page page;
    private Locator usernameInput() {
        return page.locator("#username");
    }
    private Locator passwordInput() {
        return page.locator("#password");
    }
    private Locator loginButton() {
        return page.locator("role=button[name='Login']");
    }

    public Locator loginErrorMessage() {return page.locator(".alert.alert-danger[role='alert']");}
    public Locator logoutButton() {return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout"));}

    public AdminPage(Page page) {
        super();
        this.page = page;
    }

    /**
     * Enters username and password
     *
     * @param username -> test account username
     * @param password -> test account password
     */
    public void fillOutLoginAndSubmit(String username, String password){
        usernameInput().fill(username);
        passwordInput().fill(password);
        loginButton().click();
    }

    public void navigateToLoginPage(){
        page.navigate(baseUrl+"admin");
    }
}
