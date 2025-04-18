package ticket.booking.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ticket.booking.entities.Train;

public class TrainService {

    private static final String TRAINS_PATH="src/main/java/localDb/trains.json";

    private Gson gson = new Gson();
    private List<Train> trainList;

    public TrainService() {
        loadTrains();
    }

    public List<Train> loadTrains() {
        File users = new File(TRAINS_PATH);
        try (FileReader reader = new FileReader(users)){
            Type userListType = new TypeToken<List<Train>>() {}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e){
            throw new RuntimeException("Failed to load trains from json", e);
        }
    }

    public List<Train> searchTrains(String origin, String dest) {
        return trainList.stream().filter(e -> validTrain(e, origin, dest)).toList();
    }

    private boolean validTrain(Train train, String origin, String destination) {
        List<String> stops = train.getStations();
        int originIndex = stops.indexOf(origin.toLowerCase());
        int destIndex = stops.indexOf(destination.toLowerCase());
        if (originIndex == -1 || destIndex == -1) return false;
        if (originIndex > destIndex) return false;
        return true;
    }
}
