package parking;

import java.util.HashMap;
import java.util.Map;

public class AutomobilePlace implements ParkingStore {
    private int size;
    private final Map<String, Car> cars = new HashMap<>();

    public AutomobilePlace(int size) {
        this.size = size;
    }

    @Override
    public boolean addCar(Car car) {
        boolean check = false;
        if (size - car.getSize() >= 0) {
            size = size - car.getSize();
            cars.put(car.getNumber(), car);
            check = true;
        }
        return check;
    }

    @Override
    public boolean removeCar(Car car) {
        boolean check = false;
        if (cars.containsKey(car.getNumber())) {
            size = size + car.getSize();
            cars.remove(car.getNumber());
            check = true;
        } else {
            System.out.println("такой машины нет на парковке");
        }
        return check;
    }

    @Override
    public int getFreePlaces() {
        return size;
    }
}
