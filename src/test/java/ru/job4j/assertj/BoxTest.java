package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere").endsWith("re");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object").startsWith("Un");
    }

    @Test
    void getNumberOfVerticesSphere() {
        Box box = new Box(0, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(0).isLessThan(1);
    }

    @Test
    void getNumberOfVerticesCube() {
        Box box = new Box(8, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(8).isGreaterThan(7);
    }

    @Test
    void isExist() {
        Box box = new Box(0, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isNotNull().isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(1, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isNotNull().isFalse();
    }

    @Test
    void getAreaSphere() {
        Box box = new Box(0, 3);
        double area = box.getArea();
        assertThat(area).isEqualTo(113.09d, withPrecision(0.01d)).isPositive();
    }

    @Test
    void getAreaCube() {
        Box box = new Box(8, 3);
        double area = box.getArea();
        assertThat(area).isEqualTo(54d, withPrecision(0.01d)).isGreaterThan(50d);
    }
}