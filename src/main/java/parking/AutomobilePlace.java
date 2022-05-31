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
    public void removeCar(Car car) {
        if (cars.containsKey(car.getNumber())) {
            size = size + car.getSize();
            cars.remove(car.getNumber());
        } else {
            System.out.println("такой машины нет на парковке");
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
