package productstorage;


import java.util.ArrayList;

public class ControlQuality {
    ArrayList<Storage> storages;

    public ControlQuality(ArrayList<Storage> storages) {
        this.storages = storages;
    }

    public void distribution(Food food) {
        for (Storage s : storages) {
            s.filter(food);
        }
    }

    public void addStorage(Storage storage) {
        storages.add(storage);
    }

}

