package productstorage;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface Storage {
    void add(Food food);
    ArrayList<Food> getFoods();
    void filter(Food food);
    void setFilter(Predicate<Food> predicate);
}
