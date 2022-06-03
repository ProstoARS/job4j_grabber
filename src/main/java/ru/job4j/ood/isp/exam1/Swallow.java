package ru.job4j.ood.isp.exam1;

public class Swallow implements Bird {

    @Override
    public void run() {
        throw new IllegalStateException("Эта прица не бегает");
    }

    @Override
    public void fly() {
        System.out.println("Я летаю");
    }

    @Override
    public void swim() {
        throw new IllegalStateException("Эта прица не плавает");
    }
}
