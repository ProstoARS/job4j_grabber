package menu;

public class InMemoryPrinter implements MenuPrinter {

    private static final String INDENT = "----";

    private final StringBuilder sb = new StringBuilder();

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int count = (menuItemInfo.getNumber().length() / 2) - 1;
            sb.append(INDENT.repeat(count)
                    .concat(menuItemInfo.getNumber())
                    .concat(menuItemInfo.getName()));
            sb.append(System.lineSeparator());
        }
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
