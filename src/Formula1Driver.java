import java.lang.reflect.Array;

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
    }

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
}
