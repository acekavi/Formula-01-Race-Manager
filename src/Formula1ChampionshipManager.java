import java.io.File;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager{
    private ArrayList<Formula1Driver> driversList =  new ArrayList<>();
    private ArrayList<Race> racesList = new ArrayList<>();

    public void menu(){
        menu:
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.println(
                    """
                        
                            ==========================-Menu-=========================\s
                                 To add a new driver                       : A\s
                                 To remove a driver and the relative team  : R\s
                                 To change the driver for an existing team : C\s
                                 To display stats of a driver              : V\s
                                 To display the Formula1 driver table      : T\s
                                 To add a new completed race               : N\s
                                 To view the race details                  : D\s
                                 To save details to a file                 : S\s
                                 To import details from a file             : I\s
                                 To Exit                                   : E\s
                            =========================================================
                            """);
            try {
                System.out.print("Option : ");
                String menuInput = sc.next();

                switch(menuInput.toLowerCase()){
                    case "a": addNewDriver(setTeamOfNewDriver());break;
                    case "r": removeDriver();break;
                    case "c": changeDriver();break;
                    case "v": viewDriver();break;
                    case "t": displayAllDrivers();break;
                    case "n": addNewRace();break;
                    case "d": viewRace();break;
                    case "s": saveToFile();break;
                    case "i": readFromFile();break;
                    case "e": break menu;
                }
            } catch (InputMismatchException x) {
                System.out.println("Invalid input, try again");
            }
        }
        System.exit(0);
    }

    public void readFromFile() {
        String driverDataPath = new File("src/data/driverData.ser").getAbsolutePath();
        String raceDataPath = new File("src/data/raceData.ser").getAbsolutePath();
        FileHandler readHandler = new FileHandler();

        //Reads driver related data from the driver save file
        driversList = (readHandler.readObjectFile(driverDataPath));

        //Reads race related data from the race save file
        racesList = (readHandler.readObjectFile(raceDataPath));
    }

    public void saveToFile() {
        String driverDataPath = new File("src/data/driverData.ser").getAbsolutePath();
        String raceDataPath = new File("src/data/raceData.ser").getAbsolutePath();
        FileHandler saveHandler = new FileHandler();

        //Saves driver related data to the driver save file
        saveHandler.writeObjectToFile(driverDataPath, driversList);

        //Saves race related data to the race save file
        saveHandler.writeObjectToFile(raceDataPath, racesList);
    }

    // This class was made to help me reuse the add new driver class for the change driver method as well
    private String setTeamOfNewDriver(){
        Scanner sc =  new Scanner(System.in);
        //Drivers Team
        System.out.print("Enter driver's team: ");

        return sc.nextLine();
    }

    public void addNewDriver(String teamName){
        Scanner sc =  new Scanner(System.in);
        //Drivers Name
        System.out.print("Enter driver's name: ");
        String driverName = sc.nextLine();

        //Drivers Location
        System.out.print("Enter driver's location: ");
        String driverLocation = sc.nextLine();

        // Looks for existing drivers with the same team
        boolean teamExists = false;
        for(Formula1Driver currentDriver:driversList){
            //Looks for an existing team with the user inputted name
            if (currentDriver.getTeam().getCarManufacturer().equalsIgnoreCase(teamName)){
                teamExists = true;
                break;
            }
        }
        if(teamExists){
            System.out.println("Sorry this team is already Registered!");
        }
        //If drivers with same team doesn't exist add a new driver with a team will be created
        else{
            Car driverTeam = new Car(teamName);
            Formula1Driver newDriver = new Formula1Driver(driverName, driverLocation, driverTeam);
            setStatistics(newDriver);
            driversList.add(newDriver);
        }
    }

    //SetStatistics is used inside addNewDriver class to set driver stats
    private void setStatistics(Formula1Driver driver){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of races, the driver has participated in: ");
        int numberOfRaces = input.nextInt();
        driver.setTotalRaces(numberOfRaces);

        System.out.print("Press Y to add player statistics\nPress N to go back to menu: ");
        String option = input.next();
        if (option.equalsIgnoreCase("y")) {
            // addPosition method will be running in recursion until something other than y is pressed
            addPosition(driver, option);
        }
        else if(option.equalsIgnoreCase("n")){
            return;
        }
        else{
            System.out.println("Please enter valid values");
        }
    }

    //addPosition is a recursive method used to take input from the user for positions
    private void addPosition(Formula1Driver driver, String option){
        Scanner input = new Scanner(System.in);
        if (option.equalsIgnoreCase("y")){
            System.out.print("Enter the place : ");
            int position = input.nextInt();

            if (position <= 10) {
                System.out.print("Enter the number of races in that place : ");
                int numberOfPlaces = input.nextInt();
                driver.setStatistics(position-1, numberOfPlaces);
            }else {
                System.out.println("Only the places between 1 - 10 will be recorded");
            }

            // Recursion occurs if the user press Y to add another position
            System.out.print("Press Y to add more places\nPress N to return to menu : ");
            String value = input.next();
            addPosition(driver, value);
        }
        else if(option.equalsIgnoreCase("n")){
            return;
        }
        else{
            System.out.println("Please enter valid values");
        }
    }

    public void viewDriver(){
        Scanner sc =  new Scanner(System.in);
        System.out.print("Enter driver's name to view their details: ");
        String driverName = sc.nextLine();

        boolean driverFound = false;
        for(Formula1Driver currentDriver: driversList){
            if(currentDriver.getName().equalsIgnoreCase(driverName)){
                driverFound = !driverFound;
                currentDriver.viewDriverDetails();
            }}
        if(!driverFound) {
            System.out.println("Sorry, No driver found with that name.");
        }
    }

    //Introduced this method to reduce code duplication in removing and adding drivers to a new race
    private class checkDriverExists {
        public boolean driverFound = false;
        public int foundDriverIndex = 0;

        public checkDriverExists(String driverName){
            for (Formula1Driver currentDriver : driversList){
                if (currentDriver.getName().equalsIgnoreCase(driverName)){
                    this.foundDriverIndex = driversList.indexOf(currentDriver);
                    this.driverFound = !driverFound;
                }
            }
        }
    }

    public void removeDriver(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the driver name to be removed: ");
        String nameOfDriver = sc.next();

        checkDriverExists removeDriver = new checkDriverExists(nameOfDriver);
        if(removeDriver.driverFound){
            System.out.println("Driver "+ driversList.get(removeDriver.foundDriverIndex).getName() + " has been successfully deleted");
            driversList.remove(removeDriver.foundDriverIndex);
        }else{
            System.out.println("Sorry, No driver was found with that name.");
        }
    }

    public void changeDriver(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the team name to be changed: ");
        String teamName = sc.next();

        boolean teamFound = false;
        int foundDriverIndex = 0;
        // Looping through drivers list ot find if there is a team with the user input
        for (Formula1Driver currentDriver : driversList){
            if (currentDriver.getTeam().getCarManufacturer().equalsIgnoreCase(teamName)){
                foundDriverIndex = driversList.indexOf(currentDriver);
                teamFound = !teamFound;
            }
        }
        if(teamFound){
            //Duplicates the car class itself to retain its contain in the changed F1driver class
            Car teamInfo = new Car(driversList.get(foundDriverIndex).getTeam());
            driversList.remove(foundDriverIndex);
            //Add new driver method is reused to add the driver with the original class object which was cloned
            addNewDriver(teamInfo.getCarManufacturer());
        }else{
            System.out.println("Sorry, No team was found with that name");
        }
    }

    public void displayAllDrivers(){
        // Cloned the existing driver array to a new array list to retain the original array
        ArrayList<Formula1Driver> newArrayList = new ArrayList<>(driversList);
        System.out.printf("\n| %20s | %25s | %15s | %8s | %8s | %10s | %10s | %10s |\n", "Team", "Driver", "Location",
                "Points", "Races", "1st places", "2nd places", "3rd places" );
        System.out.println("--------------------------------------------------------------------------------------------" +
                "---------------------------------------");
        //Sorting the array with the customised comparator method
        newArrayList.sort(new PointsComparator());
        for(Formula1Driver currentDriver: newArrayList) {
            //Gets the list containing the needed values from the Formula1Driver class
            String[] details = currentDriver.tableDisplay();
            System.out.printf("| %20s | %25s | %15s | %8s | %8s | %10s | %10s | %10s |\n", details[0], details[1], details[2],
                    details[3], details[4], details[5], details[6], details[7]);
        }
    }

    public void addNewRace(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Press R to randomize positions\n" +
                "Press Y to add positions of the drivers who participated in the race: ");
        String option = sc.next();

        if(option.equalsIgnoreCase("r")){
            //Cloning the original driver list to shuffle the array
            ArrayList<Formula1Driver> newArrayList = new ArrayList<>(driversList);
            Collections.shuffle(newArrayList);
            Race shuffledRace = new Race(newArrayList);
            racesList.add(shuffledRace);
        }
        else if(option.equalsIgnoreCase("y")){
            //Creating a new array to hold user entered driver positions
            ArrayList<Formula1Driver> newArrayList = new ArrayList<>();
            int count = 0;
            while(count < 10 && count < driversList.size()){
                // Lets user input the driver positions until #10 or to the size of driversList
                System.out.print("Enter #" +(count+1)+" drivers name: ");
                String nameOfDriver = sc.next();

                checkDriverExists addDriver = new checkDriverExists(nameOfDriver);
                if(addDriver.driverFound){
                    newArrayList.add(driversList.get(addDriver.foundDriverIndex));
                    count++;
                }
                else {
                    System.out.println("Sorry, no driver was found with that name");
                }
            }
            //Add the remaining drivers to the array without duplication
            Set<Formula1Driver> set = new LinkedHashSet<>(newArrayList);
            set.addAll(driversList);
            ArrayList<Formula1Driver> combinedList = new ArrayList<>(set);

            Race userInputRace = new Race(combinedList);
            racesList.add(userInputRace);
        }
        else {
            System.out.println("Please enter a valid value");
        }
    }

    public void viewRace(){
        for( Race thisRace: racesList){
            System.out.println(thisRace.viewRaceDetails());
        }
    }
}