package playwright.models;

import playwright.enums.RoomDetails;
import playwright.enums.RoomType;

public class Room {
    private String roomName;
    private RoomType type;
    private boolean accessible;
    private String price;
    private RoomDetails[] roomDetails;
    public RoomType defaultRoomType = RoomType.Single;
    public boolean defaultRoomAccessibility = false;

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

    public void setType(RoomType type) {
        this.type = type;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public String getPrice() {
        return price;
    }

    public RoomDetails[] getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(RoomDetails[] roomDetails) {
        this.roomDetails = roomDetails;
    }
}
