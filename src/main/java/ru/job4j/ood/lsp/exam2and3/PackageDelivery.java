package ru.job4j.ood.lsp.exam2and3;

public class PackageDelivery {

    protected Package pack;

    protected double cost;

    protected final double price;

    protected final String destinationAddress;

    protected final String shippingAddress;

    public PackageDelivery(String destinationAddress, String shippingAddress, double price, Package pack) {
        this.destinationAddress = destinationAddress;
        this.shippingAddress = shippingAddress;
        this.price = price;
        this.pack = pack;
    }

    public double distanceCalculation() {
        double dist = new Locale().convertToCoordinates(shippingAddress)
                - new Locale().convertToCoordinates(destinationAddress);
        if (dist < 1000) {
            return dist;
        } else {
            throw new IllegalStateException("Доставка не возможна: превышено расстояние");
        }
    }

    public void costCalculation() {
        double dist = distanceCalculation();
        cost = (dist + pack.getSize() + pack.getWeight()) / 100 * price;
        System.out.println("Расчитываем стоимость");
    }

    public void delivery() {
        System.out.println("Доставка продукта");
    }

}
