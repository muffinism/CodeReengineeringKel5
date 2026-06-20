package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class Train {
    private String trainId;

    private String trainNo;

    private SeatLayout seats;

    private Map<String, String> stationTimes;

    private List<String> stations;


    public Train(){}

    public Train(String trainId, String trainNo, SeatLayout seats,
                 Map<String, String> stationTimes, List<String> stations){
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }
    
    public void displayTrainDetails(int index) {
        System.out.println(index + ". Train ID: " + this.trainId + " | Train No: " + this.trainNo);
        System.out.println("   Route: " + String.join(" ➝ ", this.stations));
        System.out.println("   Timings:");
        if (this.stationTimes != null) {
            for (Map.Entry<String, String> entry : this.stationTimes.entrySet()) {
                System.out.println("     Time " + entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    public List<String> getStations(){
        return stations;
    }

    public SeatLayout getSeats() {
        return seats;
    }

    public void setSeats(SeatLayout seats){
        this.seats = seats;
    }

    public String getTrainId(){
        return trainId;
    }

    public Map<String, String> getStationTimes(){
        return stationTimes;
    }

    public String getTrainNo(){
        return trainNo;
    }

    public void setTrainNo(String trainNo){
        this.trainNo = trainNo;
    }

    public void setTrainId(String trainId){
        this.trainId = trainId;
    }

    public void setStationTimes(Map<String, String> stationTimes){
        this.stationTimes = stationTimes;
    }

    public void setStations(List<String> stations){
        this.stations = stations;
    }

    public String getTrainInfo(){
        return String.format("Train ID: %s Train No: %s", trainId, trainNo);
    }

    public String getStartStation(){
        return this.getStations().getFirst();
    }

    public String getEndStation(){
        return this.getStations().getLast();
    }
    
    public boolean isValidTrain(Route route) {

        
        int sourceIndex = this.stations.indexOf(route.getSource());
        int destinationIndex = this.stations.indexOf(route.getDestination());

        return  sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }
}
