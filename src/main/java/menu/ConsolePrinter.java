package menu;

public class ConsolePrinter implements MenuPrinter {

    private static final String INDENT = "----";

    private final StringBuilder sb = new StringBuilder();

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int count = (menuItemInfo.getNumber().length() / 2) - 1;
                System.out.println(INDENT.repeat(count)
                        .concat(menuItemInfo.getNumber())
                        .concat(menuItemInfo.getName()));
        }
    }

}
