package menu;

import java.util.Scanner;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    private final Scanner scanner;

    public TODOApp(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TODOApp todoApp = new TODOApp(scanner);
        MenuPrinter printer = new Printer();
        Menu menu = new SimpleMenu();
        todoApp.runMenu(menu, printer);
    }

    public void runMenu(Menu menu, MenuPrinter menuPrinter) {
        boolean run = true;
        while (run) {
            showMenu();
            int paragraph = scanner.nextInt();
            scanner.nextLine();
            switch (paragraph) {
                case 1:
                    addTask(menu, Menu.ROOT);
                    break;
                case 2:
                    String parentName = askStr("Введите название задачи в которую хотите добавить пункт:");
                    addTask(menu, parentName);
                    break;
                case 3:
                    System.out.println("Этот пункт находиться в разроботке");
                    break;
                case 4:
                    menuPrinter.print(menu);
                    System.out.println(menuPrinter);
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Такого пункта нет");
            }
        }
    }

    private void addTask(Menu menu, String root) {
        String taskName = askStr("Введите название задачи:");
        menu.add(root, taskName, STUB_ACTION);
    }

    private void showMenu() {
        System.out.println("Выберите пункт меню:");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Добавить подзадачу");
        System.out.println("3. Выполнить действие");
        System.out.println("4. Вывести меню");
        System.out.println("5. Выйти");
    }

    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
