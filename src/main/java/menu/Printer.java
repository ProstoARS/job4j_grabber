package menu;

public class Printer implements MenuPrinter {
    private final StringBuilder sb = new StringBuilder();

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            StringBuilder number = new StringBuilder(menuItemInfo.getNumber());
            int count = number.length() / 2;
            for (int i = 1; i < count; i++) {
                number.insert(0, "----");
            }
            sb.append(number.append(menuItemInfo.getName()));
            sb.append(System.lineSeparator());
        }
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
