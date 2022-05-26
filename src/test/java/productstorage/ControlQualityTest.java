package productstorage;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private final LocalDate now = LocalDate.now();
    private final Storage warehouse = new Warehouse();
    private final Storage shop = new Shop();
    private final Storage trash = new Trash();
    private final List<Storage> storages = new ArrayList<>(List.of(warehouse, shop, trash));
    private final ControlQuality controlQuality = new ControlQuality(storages);

    @Test
    public void whenAddToWarehouse() {
        LocalDate createDate = now.minusDays(2);
        LocalDate expireDate = now.plusDays(10);
        Food food = new Food("milk", createDate, expireDate, 83, 30);
        controlQuality.distribution(food);
        assertEquals(warehouse.getFoods(), List.of(food));
    }

    @Test
    public void whenAddToShop() {
        LocalDate createDate = now.minusDays(10);
        LocalDate expireDate = now.plusDays(10);
        Food food = new Food("milk", createDate, expireDate, 83, 0.3);
        controlQuality.distribution(food);
        assertEquals(shop.getFoods(), List.of(food));
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
        Food food = new Food("milk", createDate, now, 83, 30);
        controlQuality.distribution(food);
        assertEquals(trash.getFoods(), List.of(food));
    }

    @Test
    public void whenAddToAllStorage() {
        Food milk = new Food("milk", now.minusDays(2), now.plusDays(10), 92, 30);
        Food bread = new Food("bread", now.minusDays(3), now.plusDays(3), 50, 30);
        Food meat = new Food("meat", now.minusDays(7), now.plusDays(2), 450, 30);
        Food eggs = new Food("eggs", now.minusDays(20), now, 120, 30);
        controlQuality.distribution(milk);
        controlQuality.distribution(bread);
        controlQuality.distribution(meat);
        controlQuality.distribution(eggs);
        assertEquals(warehouse.getFoods(), List.of(milk));
        assertEquals(shop.getFoods(), List.of(bread, meat));
        assertEquals(shop.getFoods().get(1).getPrice(), 315, 0.001);
        assertEquals(trash.getFoods(), List.of(eggs));
    }
}