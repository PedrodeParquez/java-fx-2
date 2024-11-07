package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Figure {
    private double radius;
    private double x, y;
    private Color color;

    public Circle(double radius, double x, double y, Color color) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle\nradius = " + radius + "\narea = " + area();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public boolean contains(double px, double py) {
        double dx = px - x;
        double dy = py - y;
        return dx * dx + dy * dy <= radius * radius;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
