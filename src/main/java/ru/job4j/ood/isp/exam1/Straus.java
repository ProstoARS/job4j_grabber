package ru.job4j.ood.isp.exam1;

public class Straus implements Bird {
    @Override
    public void run() {
        System.out.println("Я бегаю");
    }

    @Override
    public void fly() {
        throw new IllegalStateException("Эта птица не летает");
    }

    @Override
    public void swim() {
        throw new IllegalStateException("Эта птица не плавает");
    }
}
