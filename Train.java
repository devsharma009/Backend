package org.TicketBooking.Service;

import java.util.List;

public class Train {
    private String trainId;
    private String trainName;
    private List<String> stations;
    public Train(){

    }
    // Getters and Setters

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainId='" + trainId + '\'' +
                ", trainName='" + trainName + '\'' +
                ", stations=" + stations +
                '}';
    }
}
