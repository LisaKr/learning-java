import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class BookingMainFrame {
    
   
    private final Hotel hotel = new Hotel(); //created an object with the constructor from the Hotel class

    public static void greetings() {
        print("Welcome to my hotel.");
        print("Please indicate what you would like to do:");
        print("1 for adding a reservation");
        print("2 for viewing a reservation");
        print("3 for changing a reservation");
        print("4 for cancelling a reservation");

    }

    
    // Printing to the screen
    private static void print(String text) { 
        System.out.println(text);
    }

    private static void skipLine() {
        print("");
    }

    private static void printAndSkipLine(String text) {
        print(text);
        skipLine();
    }

    // Querying the user
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //6. why BR instead of scanner?
    private static String queryForString(String text) {
        print(text);

        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            skipLine();
        }
    }

    private static int queryForInt(String text) {
        return Integer.valueOf(queryForString(text));
    }


    private void makeReservation() {
        final Room freeRoom = hotel.getFreeRoom();
       
        if (freeRoom != null) {
            print("You can order a room");
            freeRoom.bookingNumber = Math.abs(ThreadLocalRandom.current().nextInt()); 
            freeRoom.name = queryForString("Please enter your name: ");
            freeRoom.checkInDate = queryForInt("When would you like to come? (Please enter the date in ddmmyy format) ");
            freeRoom.numberOfNights = queryForInt("How many nights would you like to stay? ");

            print("Thank you for your reservation! Here is an overview: ");
            printAndSkipLine("Booking number: " + freeRoom.bookingNumber + " reserved for " + freeRoom.name);
  
        } else {
            print("No free rooms");
        }
    }

    private void viewReservation(final int bookingNumber) { 
        Room r = hotel.getRoomByNumber(bookingNumber);

        if (r != null) {
            printAndSkipLine("Name: " + r.name + ", " +
                    "Date of arrival: " + r.checkInDate + ", " +
                    "Number of nights: " + r.numberOfNights);
        } else {
            printAndSkipLine("No such booking number");

        }
    }

    private void changeReservation(final int bookingNumber) { 
        Room r = hotel.getRoomByNumber(bookingNumber);

        if (r != null) {
            int change = queryForInt("Do you want to change name (type 1), date (type 2) or number of nights (type 3)?");
            if (change == 1) {
                r.name = queryForString("What name would you like to change it to?");

                printAndSkipLine("Success! You changed your name!");

            } else if (change == 2) {
                r.checkInDate = queryForInt("What date would you like to change it to? Don't forget the format ddmmyy. ");

                printAndSkipLine("Success! You changed the date!");

            } else if (change == 3) {
                r.numberOfNights = queryForInt("How many nights you wanna stay instead?");

                printAndSkipLine("Success! You changed the number of nights!");

            } else {
                printAndSkipLine("what the fuck man, there is nothing we can change for you. Try again");
            }
        } else {
            printAndSkipLine("No such reservation number!");
        }
    }

    private void cancelReservation() {
        int bn = queryForInt("Whats your booking number you dumb bitch");
        Room r = hotel.getRoomByNumber(bn);

        if (r != null) {
            r.makeFree();

            printAndSkipLine("Okay done. we didnt want you anyway bitch");
        } else {
            printAndSkipLine("No such reservation number!");
        }
    }


    
   
    public static void main(String[] args) {

        
        BookingMainFrame bookingMainFrame = new BookingMainFrame();
        greetings(); 

        int answer = queryForInt("Your answer: ");

        while (answer != 0) {
            if (answer == 1) {
                bookingMainFrame.makeReservation();

            } else if (answer == 2) {
                bookingMainFrame.viewReservation(queryForInt("Please enter your booking number. "));
            } else if (answer == 3) {
                bookingMainFrame.changeReservation(queryForInt("Please enter your booking number."));
            } else if (answer == 4) {
                bookingMainFrame.cancelReservation();
            }

            answer = queryForInt("Do you have any other wishes? If so, please indicate with an appropriate number. If no, type 0 ");
        }
    }
}





