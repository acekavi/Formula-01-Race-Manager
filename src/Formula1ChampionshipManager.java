import java.lang.reflect.Array;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager{
    ArrayList<Formula1Driver> driversList =  new ArrayList<>();

    public void menu(){
        menu:
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.println(
                    "==========================-Menu-========================= \n" +
                    " To add a participant (team with a driver) : A \n" +
                    " To remove a driver and the relative team  : R \n" +
                    " To change the driver for an existing team : C \n" +
                    " To display stats of a driver              : v \n" +
                    " To display the Formula1 driver table      : T \n" +
                    " To Exit                                   : Q \n" +
                    "=========================================================");

            try {
                System.out.print("Option : ");
                String menuInput = sc.next();

                switch(menuInput.toLowerCase()){
                    case "a": addNewDriver();break;
                    case "r": removeDriver();break;
//                    case "c": change();break;
                    case "v": viewDriver();break;
//                    case "t": tableDisplay();break;
                    case "q": break menu;
                }
            } catch (InputMismatchException x) {
                System.out.println("Invalid input, try again");
            }
        }
        System.exit(0);
    }

    public void addNewDriver(){
        Scanner sc =  new Scanner(System.in);
        int driverID = 1;

        //Drivers Name
        System.out.print("Enter driver's name: ");
        String driverName = sc.next();

        //Drivers Location
        System.out.print("Enter driver's location: ");
        String driverLocation = sc.next();

        //Drivers Team
        System.out.print("Enter driver's team: ");
        String driverTeam = sc.next();

        Car car = new Car(driverTeam);
        Formula1Driver currentDriver = new Formula1Driver(driverID, driverName, driverLocation, car);

        // Looks for existing drivers with the same team
        boolean teamExists = false;
        for(Driver thisDriver:driversList){
            if (Objects.equals(
                    thisDriver.getTeam().getCarManufacturer(), currentDriver.getTeam().getCarManufacturer()
                )){
                    teamExists = true;
                }
        }
        if(teamExists){
            System.out.println("Sorry this team is already Registered!");
        }
        //If drivers with same team doesn't exist add a new driver with a team
        else{
            driversList.add(currentDriver);
            driverID++;
            setStatistics(currentDriver);
        }
    }

    public void setStatistics(Formula1Driver driver){
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

    public void addPosition(Formula1Driver driver, String option){
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

    public void viewDriver(){
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

    public void removeDriver(){
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
            System.out.println("Sorry, No driver found with that name.");
        }
    }
}
