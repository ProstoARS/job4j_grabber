package productstorage;

import java.util.List;

public class ControlQuality {
    List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void distribution(Food food) {
        for (Storage s : storages) {
            s.add(food);
        }
    }
}

