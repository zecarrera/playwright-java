package scenarios;

import com.microsoft.playwright.Locator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import playwright.enums.RoomDetails;
import playwright.enums.RoomType;
import playwright.models.Room;
import playwright.pages.AdminPage;
import playwright.pages.RoomPage;
import utils.Helpers;
import utils.TestBase;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Given admin user is logged in
 * When creating a new room
 * Then only valid rooms are successfully created
 */
public class CreateRoomTests extends TestBase {

    AdminPage adminPage;
    RoomPage roomPage;

    private String roomName;
    private Room newRoom;

    public CreateRoomTests() {
        super(false);
    }

    @BeforeClass
    void beforeAll(){
        roomPage = new RoomPage(page);
        adminPage = new AdminPage(page);
        adminPage.navigateToLoginPage();
        adminPage.fillOutLoginAndSubmit("admin", "password");
    }
    @BeforeMethod
    void beforeEach() {
        roomName = "test" + Helpers.randomNumber();
        newRoom = new Room(roomName, "99");
    }

    @Test
    void canCreateANewRoomWithDefaultValues() {
        roomPage.enterNewRoomDataAndSubmit(newRoom);

        Locator roomNameRowElement = page.getByText(roomName);

        assertThat(roomNameRowElement).isVisible();
    }

    @Test
    void canCreateANewRoomWithCustomValues() {
        RoomDetails[] roomDetails = {RoomDetails.Wifi};
        newRoom.setType(RoomType.Double);
        newRoom.setAccessible(true);
        newRoom.setRoomDetails(roomDetails);
        roomPage.enterNewRoomDataAndSubmit(newRoom);

        Locator roomNameRowElement = page.getByText(roomName);
        assertThat(roomNameRowElement).isVisible();
    }
}
