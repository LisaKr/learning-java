import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class BookingMainFrame {
    
    /*1.why is it private?
    2. Just to clarify it, how would you describe this hotel thing in correct terms? Like, it is an instance of a Hotel class used in this main class?
    Maybe it is a silly question but is it an instance variable (as every object of the main class would have its own copy of the hotel?
    I am a bit confused about it.*/
    private final Hotel hotel = new Hotel(); //created an object with the constructor from the Hotel class

    //it stays static because it belongs to the class, not its objects, and can be used even if no objects are created
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


    //it is not static because it belongs to an instance of the class, not the class itself. again, why private?
    private void makeReservation() {
        final Room freeRoom = hotel.getFreeRoom();
        /*gets an empty room object (or a null) by applying a method on the hotel variable (which is probably
         an instance variable after all, if every object of the mainframe will use it in its methods independently from each other?*/
        if (freeRoom != null) {
            print("You can order a room");
            freeRoom.bookingNumber = Math.abs(ThreadLocalRandom.current().nextInt()); //changed to abs so that no negative numbers are given
            freeRoom.name = queryForString("Please enter your name: ");//okay, i grudgingly admit it is better then getters and setters
            freeRoom.checkInDate = queryForInt("When would you like to come? (Please enter the date in ddmmyy format) ");
            freeRoom.numberOfNights = queryForInt("How many nights would you like to stay? ");

            print("Thank you for your reservation! Here is an overview: ");
            printAndSkipLine("Booking number: " + freeRoom.bookingNumber + " reserved for " + freeRoom.name);
            /*3. how does it know to re-write an empty room in the hotel with this new "free Room"? it seems to me that it just takes
            an empty room returned by the method and changes it, but where is the part to return it back to the hotel/arrayList of
            rooms of the hotel?*/
        } else {
            print("No free rooms");
        }
    }

    private void viewReservation(final int bookingNumber) { //4. why final?
        Room r = hotel.getRoomByNumber(bookingNumber);

        if (r != null) {
            printAndSkipLine("Name: " + r.name + ", " +
                    "Date of arrival: " + r.checkInDate + ", " +
                    "Number of nights: " + r.numberOfNights);
        } else {
            printAndSkipLine("No such booking number");

        }
    }

    private void changeReservation(final int bookingNumber) { //5.why private?
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

        /*6. I know that a default constructor is generated by the compiler if we dont specify one.
        but, what is this object? what exactly are we creating with it? Just a placeholder to invoke our methods on?*/
        BookingMainFrame bookingMainFrame = new BookingMainFrame();
        greetings(); //i liked it more like this

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




/*Okay, now my thoughts about your changes that канули в лету during the video.
1. did you put private final static Scanner reader = new Scanner(System.in); to the constants.
is it because it will be re-assigned in every method, so that’s it’s easier to declare once and assign when needed?
2. did you change my random thing to threadlocalrandom just because it provided with more elaorate numbers?
I read that it is mostly used when you have multiple threads
3. the solution to the name problem was nextLine() instead of next()
 */




