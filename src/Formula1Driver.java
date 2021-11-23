import java.io.Serializable;

public class Formula1Driver extends Driver implements Serializable{
    private int totalRaces;
    private int totalPoints;
    private final int[] positionDetails = new int[10];
    private final int[] pointsGiven = {25,18,15,12,10,8,6,4,2,1};

    // Constructor that will be used while creating a new driver
    public Formula1Driver( String name, String location, Car team ){
        super(name, location, team);
    }

    public void setStatistics(int position, int numberOfPlaces){
        this.positionDetails[position] = this.positionDetails[position] + numberOfPlaces;
        this.totalPoints += pointsGiven[position] * numberOfPlaces;
    }

    public void viewDriverDetails(){
        System.out.println("Driver's Name : " + this.getName());
        System.out.println("Driver's Location: " + this.getLocation());
        System.out.println("Driver's Team: " + this.getTeam().getCarManufacturer());
        System.out.println("Total Points the driver has: "+ this.totalPoints);
        System.out.println("Total Races the driver has participated in: "+ this.totalRaces);
        System.out.println("Total first places: "+ positionDetails[0]);
        System.out.println("Total second places: "+ positionDetails[1]);
        System.out.println("Total third places: "+ positionDetails[2]);
    }

    public String[] tableDisplay(){
        // Return a string containing TeamName-DriverName-Location-Points-Races-1st-2nd-3rd
        return (new String[]{this.getTeam().getCarManufacturer(), this.getName(), this.getLocation(),
                String.valueOf(this.totalPoints), String.valueOf(this.totalRaces), String.valueOf(positionDetails[0]),
                String.valueOf(positionDetails[1]), String.valueOf(positionDetails[2])});
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
}
