import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Race {
    private String raceDate;
    private ArrayList<Formula1Driver> driversInRace = new ArrayList<>();

    public Race( ArrayList<Formula1Driver> driversInRace) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);
        this.raceDate = formatDateTime;
        this.driversInRace = driversInRace;

        //Running the increment functions everytime a race is created
        incrementRace();
        addPositions();
    }

    private void incrementRace(){
        for(Formula1Driver  thisDriver : driversInRace){
            thisDriver.setTotalRaces(thisDriver.getTotalRaces() + 1);
        }
    }

    private void addPositions(){
        for(int count = 0; count < driversInRace.size(); count++){
            if (count < 10){
                driversInRace.get(count).setStatistics(count, 1);
            }
        }
    }
}
