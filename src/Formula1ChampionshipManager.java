import java.lang.reflect.Array;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager{
    static Scanner sc = new Scanner(System.in);
    ArrayList<Formula1Driver> driversList =  new ArrayList<>();

    public void menu(){
        menu:
        while (true){
            System.out.println("Enter A to add new player");
            System.out.println("Enter V to view an existing player");
            System.out.println("Enter Q to Exit");
            String menuInput = sc.nextLine().toLowerCase();

            switch(menuInput){
                case "a":
                    addNewDriver();
                    break;
                case "v":
                    viewDriver();
                    break;
                case "q":
                    break menu;
            }
        }
        System.exit(0);
    }

    public void addNewDriver(){
        int driverID = 1;

        //Drivers Name
        System.out.println("Enter driver's name: ");
        String driverName = sc.next();

        //Drivers Location
        System.out.println("Enter driver's location: ");
        String driverLocation = sc.next();

        //Drivers Team
        System.out.println("Enter driver's team: ");
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
        Scanner input =  new Scanner(System.in);
        System.out.print("Enter the number of races, the driver has participated in : ");
        int numberOfRaces = input.nextInt();
        driver.setTotalRaces(numberOfRaces);

        System.out.print("Press Y to add player statistics\nPress N to go back to menu: ");
        while(true) {
            String value = input.nextLine().toLowerCase();
            if (value.equals("y")) {
                System.out.print("Enter the place : ");
                int position = input.nextInt();
                if (position <= 10){
                    System.out.print("Enter the number of races in that place : ");
                    int numberOfPlaces = input.nextInt();
                    driver.setStatistics(position, numberOfPlaces);

                    System.out.println("Press Y to add more places\nPress N to return to menu");
                }
                else{
                    System.out.println("Only the places between 1 - 10 will be recorded.");
                }
            }else if (value.equals("n")){
                break;
            }
            else{
                System.out.println("Please enter Y or N to continue...");
            }
        }
    }

    public void deleteDriver(int driverID){

    }
    public void changeDriver(int driverID, int teamID) {

    }
    public void viewDriver(){
        System.out.println("Enter driver's name to view their details: ");
        String driverName = sc.next();

        for(Formula1Driver currentDriver: driversList){
            if(currentDriver.getName().equalsIgnoreCase(driverName)){
                System.out.println("Driver's Name :" + currentDriver.getName());
                System.out.println("Driver's Location:" + currentDriver.getLocation());
                System.out.println("Driver's Team:" + currentDriver.getTeam().getCarManufacturer());
                System.out.println("Total Points the driver has: "+currentDriver.getTotalPoints());
                System.out.println("Total Races the driver has participated in: "+currentDriver.getTotalRaces());
                System.out.println(Arrays.toString(currentDriver.getPositionDetails()));
            }
        }
    }
}
