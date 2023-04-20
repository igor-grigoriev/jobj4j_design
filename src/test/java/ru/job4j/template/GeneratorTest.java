package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {
    @Test
    public void whenInvalidTemplateThenGetException() {
        Generator generator = new SimpleGenerator();
        String template = null;
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .hasMessageContaining("Не задан шаблон генератора");
    }

    @Test
    public void whenInvalidSubjectThenGetException() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        assertThatThrownBy(() -> generator.produce(template, args))
                .hasMessageContaining("Карта должна содержать ключ subject");
    }

    @Test
    public void whenInvalidNameThenGetException() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .hasMessageContaining("Карта должна содержать ключ name");
    }

    @Test
    public void whenInvalidKeyThenGetException() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "you");
        args.put("test", "test");
        assertThatThrownBy(() -> generator.produce(template, args))
                .hasMessageContaining("Карта содержит лишний ключ");
    }

    @Test
    public void whenValidArgsThenProduce() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "you");
        String result = generator.produce(template, args);
        assertThat(result).isEqualTo("I am a Petr, Who are you?");
    }
}