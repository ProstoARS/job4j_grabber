package parking;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class TrackPlace implements ParkingStore {
    private int size;
    private final Map<String, Car> cars = new HashMap<>();
    private final Predicate<Car> predicate = c -> c.getSize() > 1;

    public TrackPlace(int size) {
        this.size = size;
    }

    @Override
    public boolean addCar(Car car) {
        boolean check = false;
        if (filter(car)) {
            if (size - 1 >= 0) {
                size--;
                cars.put(car.getNumber(), car);
                check = true;
            }
        }
        return check;
    }
    @Override
    public void removeCar(Car car) {
        if (cars.containsKey(car.getNumber())) {
            size++;
            cars.remove(car.getNumber());
        } else {
            System.out.println("такой машины нет на парковке");
        }
    }

    public boolean filter(Car car) {
        return predicate.test(car);
    }

    public int getSize() {
        return size;
    }

}
