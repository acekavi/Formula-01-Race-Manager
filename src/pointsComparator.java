import java.util.Comparator;

public class pointsComparator implements Comparator<Formula1Driver> {
    public int compare(Formula1Driver driver1,Formula1Driver driver2){
        return Integer.compare(driver1.getTotalPoints(), driver2.getTotalPoints());
    }
}
