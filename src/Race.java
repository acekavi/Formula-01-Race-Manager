import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Race implements Serializable {

    private final String raceDate;

    private final ArrayList<Formula1Driver> driversInRace;
    private final ArrayList<Formula1Driver> startingPositions;

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
            //Append a string like "#1 - Max; "
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
        for (Formula1Driver thisDriver : driversInRace){
            int count = startingPositions.indexOf(thisDriver);
            PositionDetails[count][0] = String.valueOf(count + 1);
            PositionDetails[count][1] = thisDriver.getName();
            PositionDetails[count][2] = String.valueOf(driversInRace.indexOf(thisDriver) + 1);
        }
        return PositionDetails;
    }

    public Boolean searchDriver(String driverName) {
        boolean driverExists = false;
        for (Formula1Driver thisDriver:driversInRace){
            if (thisDriver.getName().equalsIgnoreCase(driverName)) {
                driverExists = true;
                break;
            }
        }
        return driverExists;
    }

    public Object[] driverRaceStats(String driverName){
        Object[] driverRaceDetails = new Object[4];
        for (Formula1Driver thisDriver : startingPositions){
            if(thisDriver.getName().equalsIgnoreCase(driverName)){
                driverRaceDetails[0] = raceDate;
                driverRaceDetails[1] = thisDriver.getName();
                driverRaceDetails[2] = startingPositions.indexOf(thisDriver) + 1;
                driverRaceDetails[3] = driversInRace.indexOf(thisDriver) + 1;
            }
        }
        return driverRaceDetails;
    }

}
