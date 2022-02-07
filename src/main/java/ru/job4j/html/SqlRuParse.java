package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlRuParse implements Parse {
    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) {
        List<Post> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String page = String.valueOf(i);
            String url = String.format("https://www.sql.ru/forum/job-offers/%s", page);
            SqlRuParse sqlRuParse = new SqlRuParse(new SqlRuDateTimeParser());
            list = sqlRuParse.list(url);
        }
        for (Post p : list) {
            System.out.println(p.toString());
        }
    }

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element parent = td.parent();
                String header = parent.child(1).child(0).text().toLowerCase();
                Pattern pattern = Pattern.compile("\\bjava\\b");
                Matcher matcher = pattern.matcher(header);
                if (matcher.find()) {
                    Post post = detail(parent.child(1).child(0).attr("href"));
                    list.add(post);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Post detail(String link) {
        Post post = null;
        try {
            Document doc = Jsoup.connect(link).get();
            Elements row = doc.select(".messageHeader");
            String desc;
            String title;
            LocalDateTime date;
            Element parent = row.get(0).parent().parent();
            title = parent.child(0).child(0).text();
            desc = parent.child(1).child(1).text();
            String str = parent.child(2).child(0).text();
            int index = str.indexOf("[");
            str = str.substring(0, index);
            date = dateTimeParser.parse(str);
            post = new Post(title, link, desc, date);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }
}