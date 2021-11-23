import java.lang.reflect.Array;

public interface ChampionshipManager {
    // Methods that can be publicly called is put here
    void menu();
    void addNewDriver(String driverTeam);
    void viewDriver();
    void removeDriver();
    void changeDriver();
    void displayAllDrivers();
    void addNewRace();
    void viewRace();
    void readFromFile();
    void saveToFile();
}
