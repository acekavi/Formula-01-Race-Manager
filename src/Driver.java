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

    //Default constructor with no values
    public Driver() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Car getTeam() {
        return team;
    }

    public void setTeam(Car team) {
        this.team = team;
    }
}
