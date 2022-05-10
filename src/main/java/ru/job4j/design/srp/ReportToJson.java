package ru.job4j.design.srp;

import java.util.function.Predicate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class ReportToJson implements Report {

    private final Store store;

    public ReportToJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        for (Employee em : store.findBy(filter)) {
            Gson gson = new GsonBuilder().create();
            text.append(gson.toJson(em));
        }
        return text.toString();
    }
}
