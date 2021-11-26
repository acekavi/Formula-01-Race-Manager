import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ChampionshipManager {
    void readFromFile();
    void addNewRace(ArrayList<Formula1Driver> driversListInRace, ArrayList<Formula1Driver> driversPositionsInRace);
}
