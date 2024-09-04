package scenarios;

import com.microsoft.playwright.Locator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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

    @DataProvider(name = "roomDataProvider")
    public Object[][] roomDataProvider() {
        return new Object[][] {
                { new Room("test" + Helpers.randomNumber(), RoomType.Double, true, Helpers.randomNumber(999).toString() ,  new RoomDetails[] { RoomDetails.Wifi }) },
                { new Room("test" + Helpers.randomNumber(), RoomType.Family, false, Helpers.randomNumber(999).toString(),  new RoomDetails[] { RoomDetails.TV }) },
                { new Room("test" + Helpers.randomNumber(), RoomType.Suite, true, Helpers.randomNumber(999).toString(),  new RoomDetails[] { RoomDetails.Wifi, RoomDetails.Safe }) }
        };
    }

    @Test
    void canCreateANewRoomWithDefaultValues() {
        String roomName = "test" + Helpers.randomNumber();
        String roomPrice = Helpers.randomNumber(999).toString();
        Room newRoom = new Room(roomName, roomPrice);
        roomPage.enterNewRoomDataAndSubmit(newRoom);

        Locator roomNameRowElement = page.getByText(roomName);

        assertThat(roomNameRowElement).isVisible();
    }

    @Test(dataProvider = "roomDataProvider")
    void canCreateANewRoomWithCustomValues(Room newRoom) {
        roomPage.enterNewRoomDataAndSubmit(newRoom);

        Locator roomNameRowElement = page.getByText(newRoom.getRoomName());
        assertThat(roomNameRowElement).isVisible();
    }
}
