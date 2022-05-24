package productstorage;


public class ControlQuality {
    Warehouse warehouse;
    Shop shop;
    Trash trash;

    public ControlQuality(Warehouse warehouse, Shop shop, Trash trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void distribution(Food food) {
        warehouse.filter(food);
        shop.filter(food);
        trash.filter(food);
    }

}

