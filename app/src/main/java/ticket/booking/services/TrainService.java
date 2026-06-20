package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ticket.booking.entities.Route;
import ticket.booking.entities.SeatLayout;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {

    private List<Train> trainList;
    private final ObjectMapper objectMapper;
    private static final String TRAIN_DB_PATH = "src/main/java/ticket/booking/localDb/trains.json";

    public TrainService() throws IOException{
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        loadTrains();
    }

    public void loadTrains() throws IOException{
        trainList = objectMapper.readValue(new File(TRAIN_DB_PATH), new TypeReference<List<Train>>() {});

    }

    public List<Train> searchTrains(Route route){

       
        try{
            return trainList.stream()
                    .filter(train -> train.isValidTrain(route))
                    .collect(Collectors.toList());
        }catch (Exception ex){
            System.out.println("Error in searchTrains: " + ex.getMessage());
            return null;
        }
    }

    public void addTrain(Train newTrain) {
       
        Optional<Train> existingTrain = trainList.stream()
                .filter(train -> train.getTrainId().equalsIgnoreCase(newTrain.getTrainId()))
                .findFirst();

        if (existingTrain.isPresent()) {
           
            updateTrain(newTrain);
        } else {
          
            trainList.add(newTrain);
            saveTrainListToFile();
        }
    }

    private void saveTrainListToFile() {
        try {
            objectMapper.writeValue(new File(TRAIN_DB_PATH), trainList);
        } catch (IOException e) {
            System.out.println("Failed to save train list to file: " + e.getMessage());
        }
    }

    public void updateTrain(Train updatedTrain) {
        
        OptionalInt index = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(updatedTrain.getTrainId()))
                .findFirst();

        if (index.isPresent()) {
            
            trainList.set(index.getAsInt(), updatedTrain);
            saveTrainListToFile();
        } else {
            
            addTrain(updatedTrain);
        }
    }

    public boolean reserveSeat(Train train, int row, int seat) {
        
        SeatLayout seats = train.getSeats();
        try{
            if (seats.bookSeat(row, seat)) {
                updateTrain(train);
                return true;
            }
            return false;
        }catch (Exception e){
            System.out.println("Error in bookTickets: " + e.getMessage());
            return false;
        }
    }

}
