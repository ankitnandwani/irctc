package ticket.booking.entities;

import lombok.Getter;

import java.sql.Time;
import java.util.List;
import java.util.Map;

@Getter
public class Train {
    private String trainId;
    private String trainNumber;
    private List<List<Integer>> seats;
    private Map<String, String> stationTimes;
    private List<String> stations;

    public String getTrainInfo(){
        return String.format("Train ID: %s Train No: %s", trainId, trainNumber);
    }
}
