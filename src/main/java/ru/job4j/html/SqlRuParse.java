package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        int count = 0;
        for (int i = 1; i <= 2; i++) {
            String page = String.valueOf(i);
            String url = String.format("https://www.sql.ru/forum/job-offers/%s", page);
            Document doc = Jsoup.connect(url).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                System.out.println("------" + count++);
                Element parent = td.parent();
                String desc = parent.child(1).child(0).attr("href");
                System.out.println(desc);
                System.out.println(getDescription(desc).toString());
            }
        }
    }

    public static Post getDescription(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements row = doc.select(".messageHeader");
        String desc;
        String title;
        int id = 1;
        LocalDateTime date;
        Element parent = row.get(0).parent().parent();
        title = parent.child(0).child(0).text();
        desc = parent.child(1).child(1).text();
        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
        String str = parent.child(2).child(0).text();
        int index = str.indexOf("[");
        str = str.substring(0, index);
        date = parser.parse(str);
        return new Post(id, title, url, desc, date);
    }
}