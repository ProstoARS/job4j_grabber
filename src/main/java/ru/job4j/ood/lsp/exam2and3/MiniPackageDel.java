package ru.job4j.ood.lsp.exam2and3;

public class MiniPackageDel extends PackageDelivery {

    public MiniPackageDel(String destinationAddress, String shippingAddress, double price, Package pack) {
        super(destinationAddress, shippingAddress, price, pack);
    }

    @Override
    public void costCalculation() {
        if (pack.getSize() > 15 || pack.getWeight() > 5) {
            throw new IllegalStateException("Посылка не проходит по параметрам");
        } else {
            double dist = distanceCalculation();
            cost = (dist + pack.getSize() + pack.getWeight()) / 100 * price;
            System.out.println("Расчитываем стоимость");
        }
    }

}
