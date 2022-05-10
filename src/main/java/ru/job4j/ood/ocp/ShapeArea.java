package ru.job4j.ood.ocp;


public class ShapeArea {
    public double area(Rectangle rect) {
        return rect.getHeight() * rect.getWeight();
    }

    public double area(Circle circle) {
        return Math.pow(circle.getRadius(), 2) * Math.PI;
    }

    public Circle areaIncrease(Circle circle, double a) {
        double radius = Math.sqrt((a * area(circle)) / Math.PI);
        System.out.println(radius);
        return new Circle(radius);
    }

    public double areaSum(Object... shapes) {
        double sum = 0;
        for (Object obg : shapes) {
            if (obg.getClass() == Rectangle.class) {
                sum += area((Rectangle) obg);
            } else {
                sum += area((Circle) obg);
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
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
