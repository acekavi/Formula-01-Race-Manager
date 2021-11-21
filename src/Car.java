public class Car {
    private String carManufacturer;
    private String carType;
    private int carNumber;

    public Car(String carManufacturer, String carType, int carNumber) {
        this.carManufacturer = carManufacturer;
        this.carType = carType;
        this.carNumber = carNumber;
    }

    public Car(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }
}
