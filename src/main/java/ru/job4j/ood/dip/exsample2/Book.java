package ru.job4j.ood.dip.exsample2;


public class Book {

    private final String text;

    public Book(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
    public ConsolePrinter printer() {
        return new ConsolePrinter();
    }

    public void print() {
        printer().print(text);
    }
}

