import java.lang.reflect.Array;
import java.util.Arrays;

public class Formula1Driver extends Driver {
    private int totalRaces;
    private int totalPoints;
    private final int[] positionDetails = new int[10];
    private final int[] pointsGiven = {25,18,15,12,10,8,6,4,2,1};
    

    //    Default constructor with 0 values
    public Formula1Driver(){
        super();
    }

    //    Constructor with all the parameters passed in
    public Formula1Driver(int ID, String name, String location, Car team, int totalRaces, int totalPoints) {
        super(ID, name, location, team);
        this.totalRaces = totalRaces;
        this.totalPoints = totalPoints;
    }

    // Constructor that will be used while creating a new driver
    public Formula1Driver(int ID, String name, String location, Car team ){
        super(ID, name, location, team);
    }

    public void setStatistics(int position, int numberOfPlaces){
        this.positionDetails[position-1] = numberOfPlaces;
        this.totalPoints += pointsGiven[position-1] * numberOfPlaces;
    }

    public void setTotalRaces(int totalRaces) {
        this.totalRaces = totalRaces;
    }

    public int getTotalRaces() {
        return totalRaces;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int[] getPositionDetails() {
        return positionDetails;
    }

    public void viewDriverDetails(){
        System.out.println("Driver's Name : " + this.getName());
        System.out.println("Driver's Location: " + this.getLocation());
        System.out.println("Driver's Team: " + this.getTeam().getCarManufacturer());
        System.out.println("Total Points the driver has: "+ this.getTotalPoints());
        System.out.println("Total Races the driver has participated in: "+ this.getTotalRaces());
        System.out.println("Total first places: "+positionDetails[0]);
        System.out.println("Total second places: "+positionDetails[1]);
        System.out.println("Total third places: "+positionDetails[2]);
    }
}
