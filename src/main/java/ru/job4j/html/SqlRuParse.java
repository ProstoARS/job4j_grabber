package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        int count = 0;
        for (int i = 1; i <= 5; i++) {
            String page = String.valueOf(i);
            String url = String.format("https://www.sql.ru/forum/job-offers/%s", page);
            Document doc = Jsoup.connect(url).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                System.out.println("------" + count++);
                Element parent = td.parent();
                String desc = parent.child(1).child(0).attr("href");
                System.out.println(desc);
                System.out.println(parent.child(1).child(0).text());
                SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
                System.out.println(parser.parse(parent.child(5).text()).toString());
                System.out.println(parent.child(5).text());
                System.out.println(getDescription(desc));
            }
        }
    }

    public static String getDescription(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements row = doc.select(".msgBody");
        Element desc = null;
        for (Element td : row) {
            Element parent = td.parent();
            desc = parent.child(1);
            return desc.text();
            }
        return desc.text();
        }
    }