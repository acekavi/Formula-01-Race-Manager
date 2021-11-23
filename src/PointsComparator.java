import java.util.Comparator;

public class PointsComparator implements Comparator<Formula1Driver> {
    // Compares the total points
    public int compare(Formula1Driver driver1,Formula1Driver driver2){
        if(driver1.getTotalPoints()==driver2.getTotalPoints()) {
            // if the points are equal, then compare 1st positions
            if (driver1.getPositionDetails()[0] < driver2.getPositionDetails()[0]) {
                return 1;
            }
            else if (driver1.getPositionDetails()[0] == driver2.getPositionDetails()[0]){
                return 0;
            }
            else{
                return -1;
            }
        }
        else if(driver1.getTotalPoints() < driver2.getTotalPoints()) {
            return 1;
        }
        else{
            return -1;
        }
    }
}
