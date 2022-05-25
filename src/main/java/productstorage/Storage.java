package productstorage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Storage {
    default double getPercentLifeExpired(Food food) {
        LocalDate createDate = food.getCreateDate();
        LocalDate expireDate = food.getExpireDate();
        LocalDate now = LocalDate.now();
        double shelfLife = ChronoUnit.DAYS.between(createDate, expireDate);
        double lifeTime = ChronoUnit.DAYS.between(createDate, now);
        return lifeTime * 100 / shelfLife;
    }

    boolean add(Food food);
    List<Food> getFoods();
    boolean filter(Food food);
}
