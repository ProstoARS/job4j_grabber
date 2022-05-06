package ru.job4j.ood.ocp;


public class ShapeArea {

    public double areaSum(Object... shapes) {
        double sum = 0;
        for (Object obg : shapes) {
            if (obg.getClass() == Rectangle.class) {
                sum += ((Rectangle) obg).getHeight() * ((Rectangle) obg).getWeight();
            } else {
                sum += ((Circle) obg).getRadius() * Math.PI;
            }
        }
        return sum;
    }
}

class Rectangle {
    private final int height;
    private final int weight;

    public Rectangle(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }
}

class Circle {
    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
