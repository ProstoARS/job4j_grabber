package parking;

public interface ParkingStore {
    boolean addCar(Car car);
    void removeCar(Car car);
    int getSize();
}
