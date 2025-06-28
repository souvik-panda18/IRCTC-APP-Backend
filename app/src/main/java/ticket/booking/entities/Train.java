package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class Train {

    @JsonProperty("train_id")
    private String trainId;

    @JsonProperty("train_no")
    private String trainNo;

    @JsonProperty("seats")
    private List<List<Integer>> seats;

    @JsonProperty("station_times")
    private Map<String, String> StationTime;

    @JsonProperty("stations")
    private List<String> Station;

    // ✅ No-arg constructor for Jackson
    public Train() {}

    // ✅ Parameterized constructor (optional)
    public Train(String trainId, List<List<Integer>> seats, Map<String, String> StationTime, List<String> Station) {
        this.trainId = trainId;
        this.seats = seats;
        this.StationTime = StationTime;
        this.Station = Station;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public Map<String, String> getStationTime() {
        return StationTime;
    }

    public void setStationTime(Map<String, String> stationTime) {
        StationTime = stationTime;
    }

    public List<String> getStation() {
        return Station;
    }

    public void setStation(List<String> station) {
        Station = station;
    }

    public String getTrainInfo() {
        return String.format("Train ID: %s Train No: %s", trainId, trainNo);
    }
}
