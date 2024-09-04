package playwright.models;

import playwright.enums.RoomDetails;
import playwright.enums.RoomType;

public class Room {
    private final String roomName;
    private final RoomType type;
    private boolean accessible;
    private final String price;
    private final RoomDetails[] roomDetails;
    public RoomType defaultRoomType = RoomType.Single;
    public boolean defaultRoomAccessibility = false;

    public Room(String roomName, RoomType type, boolean accessible, String price, RoomDetails[] roomDetails) {
        this.roomName = roomName;
        this.type = type;
        this.accessible = accessible;
        this.price = price;
        this.roomDetails = roomDetails;
    }

    public Room(String roomName, String price) {
        this.roomName = roomName;
        this.type = defaultRoomType;
        this.price = price;
        this.roomDetails = new RoomDetails[0];
    }

    public String getRoomName() {
        return roomName;
    }

    public RoomType getType() {
        return type;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public String getPrice() {
        return price;
    }

    public RoomDetails[] getRoomDetails() {
        return roomDetails;
    }
}
