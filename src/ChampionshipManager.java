public interface ChampionshipManager {

    // Methods that will be available in all championshipManager classes
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
