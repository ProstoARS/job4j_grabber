package productstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Warehouse implements Storage {
    List<Food> foods = new ArrayList<>();
    Predicate<Food> predicate = f -> getPercentLifeExpired(f) < FreshnessConst.FRESH;

    @Override
    public boolean add(Food food) {
        boolean check = false;
        if (filter(food)) {
            check = foods.add(food);
        }
        return check;
    }

    @Override
    public List<Food> getFoods() {
        return new ArrayList<>(foods);
    }

    @Override
    public boolean filter(Food food) {
        return predicate.test(food);
    }
}
