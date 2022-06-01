package parking;

public interface ParkingStore {
    boolean addCar(Car car);
    boolean removeCar(Car car);
    int getFreePlaces();
}
