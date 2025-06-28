package ticket.booking.entities;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.util.UserServiceUtil;

import java.io.File;

public class UserBooking {
    private User user;
    private List<User> userList;
    private static ObjectMapper ObjectMapper = new ObjectMapper();
    private static String USER_PATH = "src/main/java/ticket/booking/LocalDB/Users/users.json";

    public UserBooking(User user1) throws IOException {
        this.user = user;
        LoadUsers();
    }

    public UserBooking() throws IOException {
        LoadUsers();
    }

    private void LoadUsers() throws IOException {
        File file = new File(USER_PATH);
        System.out.println("Looking for users at: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("User file not found. Creating...");
            file.getParentFile().mkdirs();
            file.createNewFile();
            ObjectMapper.writeValue(file, new ArrayList<User>());
        }

        try {
            userList = ObjectMapper.readValue(file, new TypeReference<List<User>>() {});
            System.out.println("User data loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error parsing user file: " + e.getMessage());
            throw e;
        }
    }



    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 ->
                user1.getName().equals(user.getName()) &&
                        UserServiceUtil.checkPassword(user.getPassword(), user1.getHashed_password())
        ).findFirst();

        return foundUser.isPresent();
    }


    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    //  STORES Users list to local db
// ObjectMapper.writeValue(usersFile, userList); serialization happens and stores the json file to db
    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USER_PATH);
        ObjectMapper.writeValue(usersFile, userList);
    }

    //  json-> object (De -serialization)
//    object-> json (serialization)
    public void fetchBooking() {
        user.printTicket();
    }

    public Boolean cancelBooking(String ticketId) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticket id to cancel");
        ticketId = s.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }

        String finalTicketId1 = ticketId;  //Because strings are immutable
        boolean removed = user.getTicketsbooked().removeIf(ticket -> ticket.getTicketID().equals(finalTicketId1));

        String finalTicketId = ticketId;
        user.getTicketsbooked().removeIf(Ticket -> Ticket.getTicketID().equals(finalTicketId));
        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        } else {
            System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }
}


