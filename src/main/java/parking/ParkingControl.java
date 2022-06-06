package parking;
public class ParkingControl {
    private final ParkingStore automobilePlace;
    private final ParkingStore trackPlace;

    public ParkingControl(ParkingStore automobilePlace, ParkingStore trackPlace) {
        this.automobilePlace = automobilePlace;
        this.trackPlace = trackPlace;
    }

    public boolean distribution(Car car) {
        boolean check = false;
        if (car.getSize() > AutomobileSizeConst.ONE_SIZE && trackPlace.getFreePlaces() > 0) {
                check = trackPlace.addCar(car);
        } else if (car.getSize() > AutomobileSizeConst.ONE_SIZE && trackPlace.getFreePlaces() == 0) {
            check = automobilePlace.addCar(car);
        } else if (car.getSize() == AutomobileSizeConst.ONE_SIZE) {
            check = automobilePlace.addCar(car);
        }
        return check;
    }

    public ParkingStore getAutomobilePlace() {
        return automobilePlace;
    }

    public ParkingStore getTrackPlace() {
        return trackPlace;
    }
}
