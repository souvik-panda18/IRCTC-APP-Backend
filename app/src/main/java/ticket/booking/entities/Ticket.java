package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class Ticket {

    @JsonProperty("ticket_id")
    private String TicketID;

    @JsonProperty("user_id")
    private String UserId;

    @JsonProperty("source")
    private String Source;

    @JsonProperty("destination")
    private String Destination;

    @JsonProperty("train")
    private Train train;

    @JsonProperty("date_of_travel")
    private String date_of_Travel;

    // ✅ Default constructor
    public Ticket() {}

    public String getTicketInfo() {
        return String.format(
                "Ticket ID: %s belongs to User %s from %s to %s on %s",
                TicketID, UserId, Source, Destination, date_of_Travel
        );
    }

    public String getTicketID() {
        return TicketID;
    }

    public void setTicketID(String ticketID) {
        TicketID = ticketID;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getDate_of_Travel() {
        return date_of_Travel;
    }

    public void setDate_of_Travel(String date_of_Travel) {
        this.date_of_Travel = date_of_Travel;
    }
}
