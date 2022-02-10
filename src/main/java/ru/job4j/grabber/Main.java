package ru.job4j.grabber;

import ru.job4j.grabber.utils.SqlRuDateTimeParser;
import ru.job4j.html.SqlRuParse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try (InputStream in = PsqlStore.class.getClassLoader()
                .getResourceAsStream("grabber.properties")) {
            Properties config = new Properties();
            config.load(in);
            Store psqlStore = new PsqlStore(config);
            SqlRuParse sqlRuParse = new SqlRuParse(new SqlRuDateTimeParser(), 5);
            List<Post> listFromHtml = sqlRuParse.list(config.getProperty("link"));
            for (Post p : listFromHtml) {
                psqlStore.save(p);
            }
            List<Post> listFromSql = psqlStore.getAll();
            for (Post p : listFromSql) {
                System.out.println(p);
            }
            System.out.println("---------------");
            System.out.println(psqlStore.findById(3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
