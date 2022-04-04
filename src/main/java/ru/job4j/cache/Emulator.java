package ru.job4j.cache;

public class Emulator {
    public static void main(String[] args) throws InterruptedException {
        String dir = "doc";
        String file1 = "address.txt";
        String file2 = "Names.txt";
        DirFileCache dfc = new DirFileCache(dir);
        System.out.println(dfc.load(file1));
        System.out.println("-----------------");
        System.gc();
        System.out.println(dfc.get(file1));
        System.out.println("------------------");
        System.out.println(dfc.load(file2));
        System.gc();
        System.out.println("------------------");
        System.out.println(dfc.get(file2));
    }
}
