package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements Figure {
    private double width, height;
    private double x, y;
    private Color color;

    public Rectangle(double width, double height, double x, double y, Color color) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle\nwidth = " + width + "\nheight = " + height + "\narea = " + area();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public boolean contains(double px, double py) {
        return px >= x - width / 2 && px <= x + width / 2 && py >= y - height / 2 && py <= y + height / 2;
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
