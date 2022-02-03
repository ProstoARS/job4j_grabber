package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

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
                System.out.println(parent.child(1).child(0).attr("href"));
                System.out.println(parent.child(1).child(0).text());
                SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
                System.out.println(parser.parse(parent.child(5).text()).toString());
                System.out.println(parent.child(5).text());
            }
        }
    }
}