package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void thenUsingMaxMethodWithInt() {
        List<Integer> list = new ArrayList<>(List.of(3, 5, 1, 4));
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        MaxMin maxMin = new MaxMin();
        int rsl = maxMin.max(list, comparator);
        assertEquals(5, rsl);
    }

    @Test
    public void thenUsingMinMethodWithInt() {
        List<Integer> list = new ArrayList<>(List.of(3, 5, 1, 4));
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        MaxMin maxMin = new MaxMin();
        int rsl = maxMin.min(list, comparator);
        assertEquals(1, rsl);
    }

    @Test
    public void thenUsingMaxMethodWithString() {
        List<String> list = new ArrayList<>(List.of("abc", "aba", "abb", "abx"));
        Comparator<String> comparator = String::compareTo;
        MaxMin maxMin = new MaxMin();
        String rsl = maxMin.max(list, comparator);
        assertEquals("abx", rsl);
    }

    @Test
    public void thenUsingMinMethodWithString() {
        List<String> list = new ArrayList<>(List.of("abc", "aba", "abb", "abx"));
        Comparator<String> comparator = String::compareTo;
        MaxMin maxMin = new MaxMin();
        String rsl = maxMin.min(list, comparator);
        assertEquals("aba", rsl);
    }
}