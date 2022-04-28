package ru.job4j.ood.srp;

public interface Phone {

    void dial(String phoneNumber);
    void disconnect();

    void send(String message);
    int receive();
}

