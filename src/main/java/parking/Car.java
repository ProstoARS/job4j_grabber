package parking;

public abstract class Car {
    private int size;
    private final String name;
    private final String number;
    private TypeOfCar type;

    public Car(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public void setType(TypeOfCar type) {
        this.type = type;
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

    public TypeOfCar getType() {
        return type;
    }
}
