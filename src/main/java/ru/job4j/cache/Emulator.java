package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {

    public String start() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите директорию: ");
            return sc.nextLine();
    }

    public String file() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите имя файла с расширением: ");
            return sc.nextLine();
    }

    public static void main(String[] args) throws InterruptedException {
        Emulator emulator = new Emulator();
        String dir = emulator.start();
        String file = emulator.file();
        DirFileCache dfc = new DirFileCache(dir);
        System.out.println(dfc.get(file));
        System.out.println("-----------------");
        System.out.println(dfc.get(file));
    }
}
