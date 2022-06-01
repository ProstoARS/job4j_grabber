package parking;

public class Track extends Car {

    public Track(String name, String number, int size) {
        super(name, number);
        super.setSize(size);
    }
}
