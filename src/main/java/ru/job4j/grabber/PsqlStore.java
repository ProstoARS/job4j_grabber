package ru.job4j.grabber;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private final Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            String url = cfg.getProperty("jdbc.url");
            String user = cfg.getProperty("jdbc.username");
            String password = cfg.getProperty("jdbc.password");
            cnn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement ps = cnn.prepareStatement("INSERT INTO post ("
                + "name, text, link, created) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getLink());
            ps.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement("SELECT * FROM post")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(createPost(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (PreparedStatement ps = cnn.prepareStatement("SELECT * FROM post WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                post = createPost(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    private Post createPost(ResultSet resultSet) throws SQLException {
        return new Post(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("link"),
                resultSet.getString("text"),
                resultSet.getTimestamp("created").toLocalDateTime());
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}