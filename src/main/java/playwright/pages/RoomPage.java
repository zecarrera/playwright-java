package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import playwright.models.Room;

public class RoomPage extends BasePage{

    Page page;
    public RoomPage(Page page) {
        super();
        this.page = page;
    }

    /**
     * Enters username and password
     *
     * @param room -> new room object
     */
    public void enterNewRoomDataAndSubmit(Room room){
        roomNameInput().fill(room.getRoomName());
        roomPriceInput().fill(room.getPrice());
        setRoomType(room);
        setRoomAccessibility(room);
        setRoomDetails(room);
        createRoomButton().click();
    }

    private void setRoomDetails(Room room) {
        if(room.getRoomDetails().length > 0) {
            for (int i = 0; i < room.getRoomDetails().length; i++) {
                String checkboxSelector = "#"+room.getRoomDetails()[i].toString().toLowerCase()+"Checkbox";
                page.check(checkboxSelector);
            }
        }
    }

    private void setRoomAccessibility(Room room) {
        if(room.isAccessible() != room.defaultRoomAccessibility) {
            page.selectOption("#accessible", String.valueOf(room.isAccessible()));
        }
    }

    private void setRoomType(Room room) {
        if (room.getType() != room.defaultRoomType) page.selectOption("#type", room.getType().toString());
    }

    public Locator roomNameInput() {
        return page.getByTestId("roomName");
    }
    public Locator roomPriceInput() {
        return page.locator("#roomPrice");
    }
    public Locator createRoomButton() {
        return page.locator("#createRoom");
    }
}
