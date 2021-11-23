public abstract class  Driver {
    private String name;
    private String location;
    private Car team;

    //Constructor that defines the driver
    public Driver(String name, String location, Car team) {
        this.name = name;
        this.location = location;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Car getTeam() {
        return team;
    }
}
