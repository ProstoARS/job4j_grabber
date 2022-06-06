package parking;

public class Track extends Car {

    public Track(String name, String number, int size) {
        super(name, number);
        if (size > AutomobileSizeConst.ONE_SIZE) {
            super.setSize(size);
        } else {
            throw new IllegalArgumentException("Это не грузовой автомобиль");
        }
    }
}
