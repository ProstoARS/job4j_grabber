package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SimpleGeneratorTest {

    @Ignore
    @Test
    public void whenTemplateIsCorrect() {
        Map<String, String> map = new HashMap<>(Map.of(
                "name", "Arseniy Sudakov", "subject", "you"
        ));
        Generator generator = new SimpleGenerator();
        String result = generator.produce("I am a ${name}, Who are ${subject}?", map);
        assertEquals("I am a Arseniy Sudakov, Who are you?", result);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenTemplateKeyIsNotExist() {
        Map<String, String> map = new HashMap<>(Map.of(
                "name", "Arseniy Sudakov", "subject", "you"
        ));
        Generator generator = new SimpleGenerator();
        generator.produce("I am a ${surname}, Who are ${subject}?", map);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenMapContainsExtraKey() {
        Map<String, String> map = new HashMap<>(Map.of(
                "name", "Arseniy", "subject", "you", "surname", "Sudakov"
        ));
        Generator generator = new SimpleGenerator();
        generator.produce("I am a ${surname}, Who are ${subject}?", map);
    }

}