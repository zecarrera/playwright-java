package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AdminPage extends BasePage{

    Page page;
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
        page.getByTestId("password").fill(password);
        page.getByTestId("submit").click();
    }

    public void navigateToLoginPage(){
        page.navigate(baseUrl+"#/admin");
    }

    public Locator usernameInput() {
        return page.getByTestId("username");
    }
}
