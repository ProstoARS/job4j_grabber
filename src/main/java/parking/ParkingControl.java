package parking;
public class ParkingControl {
    private final ParkingStore automobilePlace;
    private final ParkingStore trackPlace;

    public ParkingControl(ParkingStore automobilePlace, ParkingStore trackPlace) {
        this.automobilePlace = automobilePlace;
        this.trackPlace = trackPlace;
    }

    public void distribution(Car car) {
        boolean check;
        if (car.getSize() > 1) {
            if (!trackPlace.addCar(car)) {
                check = automobilePlace.addCar(car);
            } else {
                check = true;
            }
        } else {
            check = automobilePlace.addCar(car);
        }
        if (!check) {
            throw new IllegalStateException("На парковке места нет");
        }
    }

    public ParkingStore getAutomobilePlace() {
        return automobilePlace;
    }

    public ParkingStore getTrackPlace() {
        return trackPlace;
    }
}
