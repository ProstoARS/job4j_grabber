package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");
        for (Element td : row) {
            Element parent = td.parent();
            System.out.println(parent.child(1).child(0).attr("href"));
            System.out.println(parent.child(1).child(0).text());
            SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
            System.out.println(parser.parse(parent.child(5).text()).toString());
            System.out.println(parent.child(5).text());
        }
    }
}