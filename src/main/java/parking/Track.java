package parking;

public class Track extends Car {

    public Track(String name, String number) {
        super(name, number);
        super.setSize(2);
    }
}
