package ru.job4j.ood.isp.exam1;

public class Penguin implements Bird {
    @Override
    public void run() {
        throw new IllegalStateException("Эта прица не бегает");
    }

    @Override
    public void fly() {
        throw new IllegalStateException("Эта птица не летает");
    }

    @Override
    public void swim() {
        System.out.println("Я плаваю");
    }
}
