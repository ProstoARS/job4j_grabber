package parking;
public class ParkingControl {
    private final ParkingStore automobilePlace;
    private final ParkingStore trackPlace;

    public ParkingControl(ParkingStore automobilePlace, ParkingStore trackPlace) {
        this.automobilePlace = automobilePlace;
        this.trackPlace = trackPlace;
    }

    public boolean distribution(Car car) {
        boolean check;
        if (car.getSize() > AutomobileSizeConst.ONE_SIZE) {
            if (!trackPlace.addCar(car)) {
                check = automobilePlace.addCar(car);
            } else {
                check = true;
            }
        } else {
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
