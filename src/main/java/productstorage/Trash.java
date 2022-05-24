package productstorage;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Trash implements Storage {
    ArrayList<Food> foods = new ArrayList<>();
    ArrayList<Predicate<Food>> predicates = new ArrayList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public ArrayList<Food> getFoods() {
        return foods;
    }

    @Override
    public void filter(Food food) {
        for (Predicate<Food> pr : predicates) {
            if (pr.test(food)) {
                foods.add(food);
            }
        }
    }

    @Override
    public void setFilter(Predicate<Food> predicate) {
        this.predicates.add(predicate);
    }
}
