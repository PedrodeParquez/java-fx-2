package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Figure {
    double area();
    String toString();
    void draw(GraphicsContext gc);
    boolean contains(double px, double py);
    void setColor(Color color);
    double getX();
    double getY();
    void setPosition(double x, double y);
}
