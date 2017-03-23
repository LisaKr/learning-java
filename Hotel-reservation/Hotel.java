import java.util.ArrayList;
import java.util.Collection;

public class Hotel {

    private static final int NUMBER_OF_ROOMS = 2;

    private final Collection<Room> rooms; //7. why a collection and not a List/ArrayList?

    public Hotel() {
        rooms = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ROOMS; i++) {
            rooms.add(new Room());
        }
    }

    /*8. we can use "rooms" which was filled in with stuff (new rooms) inside of a constructor in other methods?
    how does the method know what happened to the initiated variable inside of the constructor?*/
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


