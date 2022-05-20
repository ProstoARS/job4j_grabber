package ru.job4j.ood.lsp.exam2and3;

public class DeliveryAnywhere extends PackageDelivery {

    public DeliveryAnywhere(String destinationAddress, String shippingAddress, double price, Package pack) {
        super(destinationAddress, shippingAddress, price, pack);
    }
    @Override
    public double distanceCalculation() {
        double dist = new Locale().convertToCoordinates(shippingAddress)
                - new Locale().convertToCoordinates(destinationAddress);
        return dist;
    }
}
