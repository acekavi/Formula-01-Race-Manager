import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Race implements Serializable {
    private String raceDate;
    private ArrayList<Formula1Driver> driversInRace;

    public Race( ArrayList<Formula1Driver> driversInRace) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.raceDate = now.format(format);
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

    public String viewRaceDetails(){
        StringBuilder driverDetails = new StringBuilder();
        int count = 1;
        for (Formula1Driver thisDriver:driversInRace) {
            //Append a string like "#1 - Avishka; "
            driverDetails.append("#").append(count).append(" - ").append(thisDriver.getName()).append("; ");
            count++;
        }
        return ("Date : " + this.raceDate + " Participants : " + driverDetails);
    }
}
