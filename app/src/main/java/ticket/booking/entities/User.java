package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    private String name;
    private String password;

    @JsonProperty("hashed_password")
    private String hashed_password;

    @JsonProperty("tickets_booked")
    private List<Ticket> tickets_booked;

    @JsonProperty("user_id")
    private String userId;

    public User() {
        // No-arg constructor for Jackson
    }

    public User(String name, String password, String hashPassword, List<Ticket> tickets_booked, String userId) {
        this.name = name;
        this.password = password;
        this.hashed_password = hashPassword;
        this.tickets_booked = tickets_booked;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public List<Ticket> getTicketsbooked() {
        return tickets_booked;
    }

    public void setTicketsbooked(List<Ticket> ticketsbooked) {
        this.tickets_booked = ticketsbooked;
    }

    public void printTicket() {
        for (Ticket ticket : tickets_booked) {
            System.out.println(ticket.getTicketInfo());
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
