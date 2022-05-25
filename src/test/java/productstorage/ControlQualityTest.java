package productstorage;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControlQualityTest {
    LocalDate now = LocalDate.now();
    Storage warehouse = new Warehouse();
    Storage shop = new Shop();
    Storage trash = new Trash();
    List<Storage> storages = new ArrayList<>(List.of(warehouse, shop, trash));
    ControlQuality controlQuality = new ControlQuality(storages);

    @Test
    public void whenAddToWarehouse() {
        LocalDate createDate = now.minusDays(2);
        LocalDate expireDate = now.plusDays(10);
        Food food = new Food("milk", createDate, expireDate, 83, 30);
        controlQuality.distribution(food);
        assertEquals(warehouse.getFoods().toString(), "[" + food + "]");
    }

    @Test
    public void whenAddToShop() {
        LocalDate createDate = now.minusDays(10);
        LocalDate expireDate = now.plusDays(10);
        Food food = new Food("milk", createDate, expireDate, 83, 0.3);
        controlQuality.distribution(food);
        assertEquals(shop.getFoods().toString(), "[" + food + "]");
    }

    @Test
    public void whenAddToShopWithDiscount() {
        LocalDate createDate = now.minusDays(10);
        LocalDate expireDate = now.plusDays(2);
        Food food = new Food("milk", createDate, expireDate, 83, 30);
        controlQuality.distribution(food);
        assertEquals(shop.getFoods().get(0).getPrice(), 58.1, 0.001);
    }

    @Test
    public void whenAddToTrash() {
        LocalDate createDate = now.minusDays(8);
        LocalDate expireDate = now;
        Food food = new Food("milk", createDate, expireDate, 83, 30);
        controlQuality.distribution(food);
        assertEquals(trash.getFoods().toString(), "[" + food + "]");
    }
}