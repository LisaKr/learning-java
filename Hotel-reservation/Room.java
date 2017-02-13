public class Room {
    int bookingNumber;
    String name;
    int checkInDate;
    int numberOfNights;
    
///setters and getters

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookingNumber() {
        return this.bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public int getDate() {
        return this.checkInDate;
    }

    public void setDate(int checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getNumberOfNights() {
        return this.numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    @Override //so when I want to print an element from the room array I don't get this weird code pointing to the object 
    public String toString() {
        return name;
    }


}

