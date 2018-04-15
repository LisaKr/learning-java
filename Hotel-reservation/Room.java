public class Room {
    int bookingNumber;
    String name;
    int checkInDate;
    int numberOfNights;


    @Override
    public String toString() {
        return name;
    }

    public boolean isFree() {
        return name == null;
    }

    public void makeFree() {
        this.name = null;
        this.bookingNumber = 0;
        this.checkInDate = 0;
        this.numberOfNights = 0;
    }
}



