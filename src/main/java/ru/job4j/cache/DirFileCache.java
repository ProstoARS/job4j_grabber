package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String result = get(key);
        if (result != null) {
            return result;
        } else {
            try (BufferedReader in = new BufferedReader(new FileReader(cachingDir + "/" + key))) {
                StringBuffer sb = new StringBuffer();
                for (String line = in.readLine(); line != null; line = in.readLine()) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
                result = sb.toString();
                put(key, result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}