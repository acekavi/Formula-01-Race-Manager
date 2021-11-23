import java.io.Serializable;

public class Car implements Serializable {
    // Team name is final because it will not be changed inside the program
    private final String carManufacturer;

    public Car(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public Car(Car team) {
        this.carManufacturer = team.getCarManufacturer();
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }
}
