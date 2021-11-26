import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Race implements Serializable {

    private String raceDate;
    private ArrayList<Formula1Driver> driversInRace;
    private ArrayList<Formula1Driver> startingPositions;

    public Race( ArrayList<Formula1Driver> driversInRace, ArrayList<Formula1Driver> startingPositions) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.raceDate = now.format(format);
        this.driversInRace = driversInRace;
        this.startingPositions = startingPositions;

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

    public String[] loadRaceTable(){
        String[] raceDetails = new String[driversInRace.size() + 1];
        raceDetails[0] = raceDate;
        for (Formula1Driver thisDriver : driversInRace){
            int count = driversInRace.indexOf(thisDriver) + 1;
            raceDetails[count] = thisDriver.getName();
        }
        return raceDetails;
    }

    public String[][] displayStartPositions(){
        String[][] PositionDetails = new String[driversInRace.size()][3];
        for (Formula1Driver thisDriver : startingPositions){
            int count = startingPositions.indexOf(thisDriver);
            PositionDetails[count][0] = String.valueOf(count + 1);
            PositionDetails[count][1] = thisDriver.getName();
            PositionDetails[count][2] = String.valueOf(driversInRace.indexOf(thisDriver) + 1);
        }
        return PositionDetails;
    }

    public String getRaceDate() {
        return raceDate;
    }
}
