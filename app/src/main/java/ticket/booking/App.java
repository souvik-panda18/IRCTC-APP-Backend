package ticket.booking;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.entities.UserBooking;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

import static java.util.UUID.randomUUID;

public class App {
    public static void main(String[] args) {
        System.out.println("Running Train booking system");

        Scanner sc = new Scanner(System.in);
        int option = 0;
        UserBooking userBooking;

        // This variable will hold the selected train for booking
        Train trainSelectedForBooking = null;

        try {
            userBooking = new UserBooking();
        } catch (IOException ex) {
            System.out.println("Please enter a valid option");
            return;
        }

        while (option != 7) {
            System.out.println("Please enter an option:");
            System.out.println("1 - Sign Up");
            System.out.println("2 - Log In");
            System.out.println("3 - Fetch Booking");
            System.out.println("4 - Search Train");
            System.out.println("5 - Book a Seat");
            System.out.println("6 - Cancel Booking");
            System.out.println("7 - Exit");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter the username to sign up:");
                    String nameToSignUp = sc.next();
                    System.out.println("Enter the password to sign up:");
                    String passwordToSignUp = sc.next();
                    User userToSignup = new User(
                            nameToSignUp,
                            passwordToSignUp,
                            UserServiceUtil.hashPasssword(passwordToSignUp),
                            new ArrayList<>(),
                            randomUUID().toString()
                    );
                    userBooking.signUp(userToSignup);
                    break;

                case 2:
                    System.out.println("Enter the username to log in:");
                    String nameToLogin = sc.next();
                    System.out.println("Enter the password to log in:");
                    String passwordToLogin = sc.next();
                    User userToLogin = new User(
                            nameToLogin,
                            passwordToLogin,
                            UserServiceUtil.hashPasssword(passwordToLogin),
                            new ArrayList<>(),
                            randomUUID().toString()
                    );
                    userBooking.loginUser(); // Consider passing userToLogin here if needed

                    try {
                        userBooking = new UserBooking(userToLogin);
                    } catch (IOException ex) {
                        System.out.println("Login failed.");
                        return;
                    }
                    break;

                case 3:
                    System.out.println("Fetching your bookings...");
                    userBooking.fetchBooking();
                    break;

                case 4:
                    System.out.println("Type your source station:");
                    String source = sc.next();
                    System.out.println("Type your destination station:");
                    String dest = sc.next();
                    List<Train> trains = userBooking.getTrains(source, dest);
                    int index = 1;

                    for (Train t : trains) {
                        System.out.println(index + " - Train ID: " + t.getTrainId());
                        for (Map.Entry<String, String> entry : t.getStationTime().entrySet()) {
                            System.out.println("Station: " + entry.getKey() + ", Time: " + entry.getValue());
                        }
                        index++;
                    }

                    System.out.println("Select a train by typing 1, 2, 3...");
                    int selected = sc.nextInt();
                    if (selected >= 1 && selected <= trains.size()) {
                        trainSelectedForBooking = trains.get(selected - 1);
                    } else {
                        System.out.println("Invalid selection.");
                    }
                    break;

                case 5:
                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = userBooking.fetchSeats(trainSelectedForBooking);
                    for (List<Integer> row: seats){
                        for (Integer val: row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row");
                    int row = sc.nextInt();
                    System.out.println("Enter the column");
                    int col = sc.nextInt();
                    System.out.println("Booking your seat....");
                    Boolean booked = userBooking.bookTrainSeat(trainSelectedForBooking, row, col);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Booked! Enjoy your journey");
                    }else{
                        System.out.println("Can't book this seat");
                    }
                    break;


                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Unexpected value: " + option);
                    break;
            }
        }
    }
}
