package ru.job4j.ood.srp;

import java.util.Random;

public class SimpleNumberGenerator implements NumberGenerator<Integer> {
    @Override
    public Integer generate() {
        return new Random().nextInt();
    }
}
