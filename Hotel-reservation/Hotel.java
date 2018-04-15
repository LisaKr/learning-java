import java.util.ArrayList;
import java.util.Collection;

public class Hotel {

    private static final int NUMBER_OF_ROOMS = 2;

    private final Collection<Room> rooms; 
    public Hotel() {
        rooms = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ROOMS; i++) {
            rooms.add(new Room());
        }
    }

    
    public Room getFreeRoom() {
        for (Room room : rooms) {
            if (room.isFree()) {
                return room;
            }
        }
        return null;
    }


    public Room getRoomByNumber(int bookingNumber) {
        for (Room room : rooms) {
            if (room.bookingNumber == bookingNumber) {
                return room;
            }
        }
        return null;
    }

}


