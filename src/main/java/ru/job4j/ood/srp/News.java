package ru.job4j.ood.srp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class News {

    String title;
    String text;

    public News(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public void addToBD() throws SQLException {
        Connection connection = DriverManager.getConnection("someURL");
        System.out.println("Новость добавлена в БД");
    }

    public void convertToXML() {
        System.out.println("Новость сконвертирована в формат XML");
        System.out.println("Новость записана в файл XML");
    }

    public void convertToJSON() {
        System.out.println("Новость сконвертирована в формат JSON");
        System.out.println("Новость записана в файл JSON");
    }
}
