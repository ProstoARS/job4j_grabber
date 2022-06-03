package ru.job4j.ood.isp.exam3;

public class MongoDatabase implements Database {
    @Override
    public void connect() {
    }

    @Override
    public void read() {
    }

    @Override
    public void write() {
    }

    @Override
    public void joinTables() {
        throw new Error("У монго БД нет таблиц");
    }
}
