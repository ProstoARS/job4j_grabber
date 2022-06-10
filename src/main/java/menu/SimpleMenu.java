package menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        if (findItem(childName).isPresent()) {
            return false;
        }
        boolean check = false;
        MenuItem mi = new SimpleMenuItem(childName, actionDelegate);
        if (Objects.equals(parentName, Menu.ROOT)) {
            check = rootElements.add(mi);
        } else {
            Optional<ItemInfo> item = findItem(parentName);
            if (item.isPresent()) {
                check = item.get().menuItem.addChildren(mi);
            }
        }
        return check;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(item -> new MenuItemInfo(item.menuItem, item.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
       return new Iterator<>() {
           final DFSIterator iterator = new DFSIterator();

           @Override
           public boolean hasNext() {
               return iterator.hasNext();
           }

           @Override
           public MenuItemInfo next() {
               if (!hasNext()) {
                   throw new NoSuchElementException();
               }
               ItemInfo ii = iterator.next();
               return new MenuItemInfo(ii.menuItem, ii.number);
           }
       };
    }

    private Optional<ItemInfo> findItem(String name) {
        Iterator<ItemInfo> dfs = new DFSIterator();
        while (dfs.hasNext()) {
            ItemInfo mi = dfs.next();
            if (name.equals(mi.menuItem.getName())) {
                return Optional.of(mi);
            }
        }
        return Optional.empty();
    }

    private static class SimpleMenuItem implements MenuItem {

        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }

        @Override
        public boolean addChildren(MenuItem menuItem) {
            return children.add(menuItem);
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
