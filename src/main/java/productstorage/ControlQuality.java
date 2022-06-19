package productstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControlQuality {
    private final List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void distribution(Food food) {
        for (Storage s : storages) {
            if (s.filter(food)) {
                s.add(food);
                break;
            }
        }
    }

    public void resort() {
        List<Food> foods = storages
                .stream()
                .flatMap(storage -> storage.getFoods().stream())
                .collect(Collectors.toList());
        storages.forEach(Storage::clear);
        for (Food f : foods) {
            distribution(f);
        }
    }
}

