package parking;

public abstract class Car {
    private int size;
    private final String name;
    private final String number;

    public Car(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
