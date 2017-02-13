import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final int NUMBER_OF_ROOMS = 20; //how many rooms there are in my hotel?

    //takes an empty array of potential rooms and fills it with empty rooms
    public static void createHotel(ArrayList<Room> rooms) {
        for (int i = 0; i < NUMBER_OF_ROOMS; i++) {
            Room r = new Room();
            r.setName("null");
            r.setDate(000000);
            r.setNumberOfNights(0);
            rooms.add(r);

        }
    }

    //prints out the greetings
    public static void greetings() {
        System.out.println("Welcome to my hotel.");
        System.out.println("Please indicate what you would like to do:");
        System.out.println("1 for adding a reservation");
        System.out.println("2 for viewing a reservation");
        System.out.println("3 for changing a reservation");
        System.out.println("4 for cancelling a reservation");
        System.out.println("Your answer: ");

    }

    //returns true if at least one room has null instead of a real name
    public static boolean checkForFreeRooms(ArrayList<Room> rooms) {
        int count = 0;
        for (int i = 0; i < NUMBER_OF_ROOMS; i++) {
            if (rooms.get(i).getName() == "null") {
                count = count + 1;
            }
        }
        if (count > 0) {
            System.out.println("You can order a room. Number of free rooms: " + count + ".");
            return true;
        } else {
            System.out.println("No free rooms");
            return false;
        }
    }

    //creates a room object based on the user input and adds it to the first room in the array where the field name is not occupied (==free)
    public static void addRoom(ArrayList<Room> rooms) {
        Scanner reader = new Scanner(System.in);
        Room newRoom = new Room();
        newRoom.setBookingNumber((int) (Math.random() * 100 + 1));
        System.out.println("Please enter your name: ");
        String name = reader.next();
        newRoom.setName(name);
        System.out.println();
        System.out.println("When would you like to come? (Please enter the date in ddmmyy format) ");
        int date = reader.nextInt();
        newRoom.setDate(date);
        System.out.println("How many nights would you like to stay? ");
        int nights = reader.nextInt();
        newRoom.setNumberOfNights(nights);
        for (int i = 0; i < NUMBER_OF_ROOMS; i++) {
            if (rooms.get(i).getName() == "null") {
                Collections.replaceAll(rooms, rooms.get(i), newRoom);
                break;
            }
        }
        System.out.println("Thank you for your reservation! Here is an overview: ");
        System.out.println("Booking number: " + newRoom.getBookingNumber() + " reserved for " + newRoom.getName());
        System.out.println();
    }

    //checks whether the booking number exists at all
    public static boolean checkBookingNumber(ArrayList<Room> rooms, int bookingNumber) {
        List<Integer> bn = new ArrayList<>();
        for (Room room : rooms) {
            bn.add(room.bookingNumber);
        }
        if (bn.contains(bookingNumber)) {
            return true;
        } else {
            return false;
        }
    }

    //shows you the reservation if your booking number is existant
    public static void viewReservation(ArrayList<Room> rooms, int bookingNumber) {
        if (checkBookingNumber(rooms, bookingNumber)) {
            for (Room r : rooms) {
                if (r.getBookingNumber() == bookingNumber) {
                    System.out.println("Name: " + r.getName() + ", Date of arrival: " + r.getDate() + ", Number of nights: " + r.getNumberOfNights());
                    System.out.println();
                }
            }
        } else {
            System.out.println("No such booking number");
            System.out.println();

        }
    }

    //if your booking number exists then takes your input on what to change and changes it
    public static void changeReservation(ArrayList<Room> rooms, int bookingNumber) {
        Scanner reader = new Scanner(System.in);
        if (checkBookingNumber(rooms, bookingNumber)) {
            System.out.println("Do you want to change name (type 1), date (type 2) or number of nights (type 3)?");
            int change = reader.nextInt();
            for (Room r : rooms) {
                if (r.getBookingNumber() == bookingNumber) { //finding the right room
                    if (change == 1) {
                        System.out.println("What name would you like to change it to?");
                        String newName = reader.next();
                        r.setName(newName);
                        System.out.println("Success! You changed your name!");
                        System.out.println();

                    } else if (change == 2) {
                        System.out.println("What date would you like to change it to? Don't forget the format ddmmyy. ");
                        int newDate = reader.nextInt();
                        r.setDate(newDate);
                        System.out.println("Success! You changed tha date!");
                        System.out.println();

                    } else if (change == 3) {
                        System.out.println("How many nights you wanna stay instead?");
                        int newNights = reader.nextInt();
                        r.setNumberOfNights(newNights);
                        System.out.println("Success! You changed the number of nights!");
                        System.out.println();

                    } else {
                        System.out.println("what the fuck man, there is nothing we can change for you. Try again");
                        System.out.println();
                    }
                }
            }

        } else {
            System.out.println("No such reservation number!");
            System.out.println();
        }
    }

    //cancels reservation if such number exists and insults you
    public static void cancelReservation(ArrayList<Room> rooms) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Whats your booking number you dumb bitch");
        int bn = reader.nextInt();
        if (checkBookingNumber(rooms, bn)) {
            for (Room r : rooms) {
                if (r.getBookingNumber() == bn) {
                    rooms.remove(r);
                    break;
                }
            }
            System.out.println("Okay done. we didnt want you anyway bitch");
            System.out.println();
        } else {
            System.out.println("No such reservation number!");
            System.out.println();
        }
    }


    /////////MAIN METHOD
    public static void main(String[] args) {
        ArrayList<Room> rooms = new ArrayList<>();
        createHotel(rooms);
        greetings();

        Scanner reader = new Scanner(System.in);
        int answer = reader.nextInt();

        while (answer != 0) {
            if (answer == 1) {
                if (checkForFreeRooms(rooms)) {
                    addRoom(rooms);
                }

            } else if (answer == 2) {
                System.out.println("Please enter your booking number. ");
                int bookingNumber = reader.nextInt();
                viewReservation(rooms, bookingNumber);
            } else if (answer == 3) {
                System.out.println("Please enter your booking number.");
                int bookingNumber = reader.nextInt();
                changeReservation(rooms, bookingNumber);
            } else if (answer == 4) {
                cancelReservation(rooms);
            }

            System.out.println("Do you have any other wishes? If so, please indicate with an appropriate number. If no, type 0 ");
            answer = reader.nextInt(); //it should overwrite the answer, return to the while and continue from there
        }
    }
}






