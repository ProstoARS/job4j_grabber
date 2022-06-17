package productstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Trash implements Storage {
    private final List<Food> foods = new ArrayList<>();
    private final Predicate<Food> predicate = f -> getPercentLifeExpired(f) >= FreshnessConst.EXPIRED;

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

    @Override
    public void clear() {
        this.foods.clear();
    }
}
