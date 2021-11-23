public class Car {
    private String carManufacturer;
    private String carType;
    private int teamRaces;

    public Car(String carManufacturer, String carType, int teamRaces) {
        this.carManufacturer = carManufacturer;
        this.carType = carType;
        this.teamRaces = teamRaces;
    }

    public Car(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public Car(Car team) {
        this.carManufacturer = team.getCarManufacturer();
        this.carType = team.carType;
        this.teamRaces = team.teamRaces;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }
}
