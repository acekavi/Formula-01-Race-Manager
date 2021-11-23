import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager{
    private ArrayList<Formula1Driver> driversList =  new ArrayList<>();
    private ArrayList<Race> allRaces = new ArrayList<>();

    public void menu(){
        menu:
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.println(
                    """
                    ==========================-Menu-=========================\s
                     To add a participant (team with a driver) : A\s
                     To remove a driver and the relative team  : R\s
                     To change the driver for an existing team : C\s
                     To display stats of a driver              : v\s
                     To display the Formula1 driver table      : T\s
                     To Exit                                   : Q\s
                    =========================================================""");
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
                    case "q": break menu;
                }
            } catch (InputMismatchException x) {
                System.out.println("Invalid input, try again");
            }
        }
        System.exit(0);
    }

    public ArrayList<Formula1Driver> getDriversList() {
        return driversList;
    }

    // This class was made to help me reuse the add new driver class for the change driver method as well
    private Car setTeamOfNewDriver(){
        Scanner sc =  new Scanner(System.in);
        //Drivers Team
        System.out.print("Enter driver's team: ");
        String driverTeam = sc.next();

        return new Car(driverTeam);
    }

    private void addNewDriver(Car driverTeam){
        Scanner sc =  new Scanner(System.in);

        //Drivers Name
        System.out.print("Enter driver's name: ");
        String driverName = sc.next();

        //Drivers Location
        System.out.print("Enter driver's location: ");
        String driverLocation = sc.next();

        Formula1Driver currentDriver = new Formula1Driver(driverName, driverLocation, driverTeam);

        // Looks for existing drivers with the same team
        boolean teamExists = false;
        for(Driver thisDriver:driversList){
            if (Objects.equals(
                    thisDriver.getTeam().getCarManufacturer(), currentDriver.getTeam().getCarManufacturer()
            )) {
                teamExists = true;
                break;
            }
        }
        if(teamExists){
            System.out.println("Sorry this team is already Registered!");
        }
        //If drivers with same team doesn't exist add a new driver with a team will be created
        else{
            driversList.add(currentDriver);
            setStatistics(currentDriver);
        }
    }

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

    private void addPosition(Formula1Driver driver, String option){
        Scanner input = new Scanner(System.in);
        if (option.equalsIgnoreCase("y")){
            System.out.print("Enter the place : ");
            int position = input.nextInt();

            if (position <= 10) {
                System.out.print("Enter the number of races in that place : ");
                int numberOfPlaces = input.nextInt();
                driver.setStatistics(position, numberOfPlaces);
            }else {
                System.out.println("Only the places between 1 - 10 will be recorded");
            }

            // Go in recursive manner using the driver object and the option given
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

    private void viewDriver(){
        Scanner sc =  new Scanner(System.in);
        boolean driverFound = false;

        System.out.print("Enter driver's name to view their details: ");
        String driverName = sc.next();

        for(Formula1Driver currentDriver: driversList){
            if(currentDriver.getName().equalsIgnoreCase(driverName)){
                driverFound = !driverFound;
                currentDriver.viewDriverDetails();
            }}
        if(!driverFound) {
            System.out.println("Sorry, No driver found with that name.");
        }
    }

    private void removeDriver(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the driver name to be removed: ");
        String removeDriver = sc.next();

        boolean driverFound = false;
        int foundDriverIndex = 0;
        for (Formula1Driver currentDriver : driversList){
            if (currentDriver.getName().equalsIgnoreCase(removeDriver)){
                foundDriverIndex = driversList.indexOf(currentDriver);
                driverFound = !driverFound;
            }
        }
        if(driverFound){
            System.out.println("Driver "+ driversList.get(foundDriverIndex).getName() + " has been successfully deleted");
            driversList.remove(foundDriverIndex);
        }else{
            System.out.println("Sorry, No driver was found with that name.");
        }
    }

    private void changeDriver() {
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
            Car teamInfo = new Car(driversList.get(foundDriverIndex).getTeam().getCarManufacturer());

            //Drivers Name
            System.out.print("Enter new driver's name: ");
            String driverName = sc.next();

            //Drivers Location
            System.out.print("Enter new driver's location: ");
            String driverLocation = sc.next();

            //Creates a new driver object containing the new driver info
            Formula1Driver newDriver = new Formula1Driver(driverName, driverLocation, teamInfo);

            driversList.set(foundDriverIndex, newDriver);
            setStatistics(newDriver);

            System.out.println("Driver for the team "+ teamInfo.getCarManufacturer()
                    + " has been successfully changed");
        }else{
            System.out.println("Sorry, No team was found with that name");
        }
    }

    private void displayAllDrivers(){
        // Cloned the existing driver array to a new array list to retain the original array
        ArrayList<Formula1Driver> newArrayList = new ArrayList<>(driversList);
        System.out.printf("%12s %12s %17s %10s %10s %10s %10s %10s \n", "Team", "| Driver", "| Location",
                "| Points", "| Races", "| 1st places", "| 2nd places", "| 3rd places |" );

        //Sorting the array with the customised comparator method
        Collections.sort(newArrayList, new PointsComparator());
        for(Formula1Driver currentDriver: newArrayList){
            //Gets the list containing the needed values from the Formula1Driver class
            String[] details = currentDriver.tableDisplay();
            System.out.printf("%12s %12s %15s %11s %11s %11s %11s %11s \n", details[0], details[1], details[2],
                    details[3], details[4], details[5], details[6], details[7]);
        }
    }

    private void addNewRace(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Press R to randomize positions\n" +
                "Press Y to add positions of the drivers who participated in the race: ");
        String option = sc.next();

        //Cloning the original driver list to shuffle the array
        ArrayList<Formula1Driver> newArrayList = new ArrayList<>(driversList);
        Collections.shuffle(newArrayList);

        if(option.equalsIgnoreCase("r")){
            Race shuffledRace = new Race(newArrayList);
        }
        else if(option.equalsIgnoreCase("r")){
            int count = 0;
            while(count < 10){

            }
        }
    }
}