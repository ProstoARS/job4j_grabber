package parking;

public class Automobile extends Car {

    public Automobile(String name, String number) {
        super(name, number);
        super.setSize(AutomobileSizeConst.ONE_SIZE);
    }
}
