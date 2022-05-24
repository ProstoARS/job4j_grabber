package productstorage;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class ControlQualityTest {
    LocalDate now = LocalDate.of(2022, 5, 24);
    Warehouse warehouse = new Warehouse();
    Shop shop = new Shop();
    Trash trash = new Trash();

    private void setPredicates(long shelfLife, long lifeTime) {
        warehouse.setFilter(f -> lifeTime < shelfLife * 0.25);
        shop.setFilter(f -> lifeTime >= shelfLife * 0.25 && lifeTime <= shelfLife * 0.75);
        shop.setFilter(f -> {
            if (lifeTime > shelfLife * 0.75 && lifeTime < shelfLife) {
                f.setPrice(f.getPrice() * f.getDiscount());
                return true;
            }
            return false;
        });
        trash.setFilter(f -> lifeTime > shelfLife);
    }

    @Test
    public void whenAddToWarehouse() {
        LocalDate createDate = LocalDate.of(2022, 5, 20);
        LocalDate expireDate = LocalDate.of(2022, 6, 15);
        long shelfLife = ChronoUnit.DAYS.between(createDate, expireDate);
        long lifeTime = ChronoUnit.DAYS.between(createDate, now);
        setPredicates(shelfLife, lifeTime);
        Food food = new Food("milk", createDate, expireDate, 83, 0.3);
        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.distribution(food);
        assertEquals(warehouse.getFoods().toString(), "[" + food + "]");
    }

    @Test
    public void whenAddToShop() {
        LocalDate createDate = LocalDate.of(2022, 5, 15);
        LocalDate expireDate = LocalDate.of(2022, 5, 30);
        long shelfLife = ChronoUnit.DAYS.between(createDate, expireDate);
        long lifeTime = ChronoUnit.DAYS.between(createDate, now);
        setPredicates(shelfLife, lifeTime);
        Food food = new Food("milk", createDate, expireDate, 83, 0.3);
        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.distribution(food);
        assertEquals(shop.getFoods().toString(), "[" + food + "]");
    }

    @Test
    public void whenAddToShopWithDiscount() {
        LocalDate createDate = LocalDate.of(2022, 5, 5);
        LocalDate expireDate = LocalDate.of(2022, 5, 26);
        long shelfLife = ChronoUnit.DAYS.between(createDate, expireDate);
        long lifeTime = ChronoUnit.DAYS.between(createDate, now);
        setPredicates(shelfLife, lifeTime);
        Food food = new Food("milk", createDate, expireDate, 83, 0.75);
        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.distribution(food);
        assertEquals(shop.getFoods().get(0).getPrice(), 62.25, 0.001);
    }

    @Test
    public void whenAddToTrash() {
        LocalDate createDate = LocalDate.of(2022, 5, 15);
        LocalDate expireDate = LocalDate.of(2022, 5, 22);
        long shelfLife = ChronoUnit.DAYS.between(createDate, expireDate);
        long lifeTime = ChronoUnit.DAYS.between(createDate, now);
        setPredicates(shelfLife, lifeTime);
        Food food = new Food("milk", createDate, expireDate, 83, 0.3);
        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.distribution(food);
        assertEquals(trash.getFoods().toString(), "[" + food + "]");
    }
}