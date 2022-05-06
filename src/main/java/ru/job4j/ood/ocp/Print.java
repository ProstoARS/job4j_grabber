package ru.job4j.ood.ocp;

import java.util.Scanner;


public class Print {

    Scanner scanner;

    public Print() {
        this.scanner = new Scanner(System.in);
    }

    public void inConsole() {
        System.out.println(scanner.nextLine());
    }
}
