package productstorage;

import java.util.List;

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
}

