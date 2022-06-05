package parking;

public class Track extends Car {

    public Track(String name, String number, int size) {
        super(name, number);
        if (size > AutomobileSizeConst.ONE_SIZE) {
            super.setSize(size);
            super.setType(TypeOfCar.TRACK);
        } else {
            throw new IllegalArgumentException("Это не грузовой автомобиль");
        }
    }

    @Override
    public TypeOfCar getType() {
        return super.getType();
    }
}
