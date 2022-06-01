package parking;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class TrackPlace implements ParkingStore {
    private int size;
    private final Map<String, Car> cars = new HashMap<>();
    private final Predicate<Car> predicate = c -> c.getSize() > AutomobileSizeConst.ONE_SIZE;

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
    public boolean removeCar(Car car) {
        boolean check = false;
        if (cars.containsKey(car.getNumber())) {
            size++;
            cars.remove(car.getNumber());
            check = true;
        } else {
            System.out.println("такой машины нет на парковке");
        }
        return check;
    }

    public boolean filter(Car car) {
        return predicate.test(car);
    }

    @Override
    public int getFreePlaces() {
        return size;
    }
}
