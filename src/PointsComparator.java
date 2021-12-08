import java.util.Comparator;

//Java2s.com. 2021. Use Collections.sort to sort custom class and user defined Comparator : Collections « Collections Data Structure « Java. [online] Available at: <http://www.java2s.com/Code/Java/Collections-Data-Structure/UseCollectionssorttosortcustomclassanduserdefinedComparator.htm> [Accessed 8 December 2021].
public class PointsComparator implements Comparator<Formula1Driver> {
    // Compares the total points
    public int compare(Formula1Driver driver1,Formula1Driver driver2){
        if(driver1.getTotalPoints()==driver2.getTotalPoints()) {
            // if the points are equal, then compare 1st positions
            return Integer.compare(driver2.getPositionDetails()[0], driver1.getPositionDetails()[0]);
        }
        else if(driver1.getTotalPoints() < driver2.getTotalPoints()) {
            return 1;
        }
        else{
            return -1;
        }
    }
}
