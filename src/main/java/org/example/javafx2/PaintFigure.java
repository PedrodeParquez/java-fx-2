package org.example.javafx2;

import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaintFigure extends Application {

    private final List<Figure> figures = new ArrayList<>();
    private Figure selectedFigure = null;
    private double offsetX, offsetY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        VBox root = new VBox();
        Pane canvasPane = new Pane(canvas);
        Button drawCircleButton = new Button("Draw Circle");
        Button drawRectangleButton = new Button("Draw Rectangle");

        drawCircleButton.setOnAction(e -> drawRandomCircle(gc));
        drawRectangleButton.setOnAction(e -> drawRandomRectangle(gc));

        root.getChildren().addAll(canvasPane, drawCircleButton, drawRectangleButton);

        Scene scene = new Scene(root);

        canvas.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                for (Figure figure : figures) {
                    if (figure.contains(e.getX(), e.getY())) {
                        selectedFigure = figure;
                        offsetX = e.getX() - figure.getX();
                        offsetY = e.getY() - figure.getY();
                        figures.remove(figure);
                        figures.add(figure);
                        redraw(gc);
                        break;
                    }
                }
            }
        });

        canvas.setOnMouseDragged(e -> {
            if (e.isPrimaryButtonDown() && selectedFigure != null) {
                selectedFigure.setPosition(e.getX() - offsetX, e.getY() - offsetY);
                redraw(gc);
            }
        });

        canvas.setOnMouseReleased(e -> {
            if (e.isPrimaryButtonDown()) {
                selectedFigure = null;
            }
        });

        canvas.setOnMouseClicked(e -> {
            if (e.isSecondaryButtonDown()) {
                for (Figure figure : figures) {
                    if (figure.contains(e.getX(), e.getY())) {
                        figure.setColor(new Color(new Random().nextDouble(), new Random().nextDouble(), new Random().nextDouble(), 1));
                        redraw(gc);
                        break;
                    }
                }
            }
        });

        primaryStage.setTitle("Geometry App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawRandomCircle(GraphicsContext gc) {
        Random rand = new Random();
        double radius = 20 + rand.nextDouble() * 30;
        double x = rand.nextDouble() * 800;
        double y = rand.nextDouble() * 600;
        Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
        Circle circle = new Circle(radius, x, y, color);
        figures.add(circle);
        circle.draw(gc);
    }

    private void drawRandomRectangle(GraphicsContext gc) {
        Random rand = new Random();
        double width = 40 + rand.nextDouble() * 60;
        double height = 20 + rand.nextDouble() * 30;
        double x = rand.nextDouble() * 800;
        double y = rand.nextDouble() * 600;
        Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
        Rectangle rectangle = new Rectangle(width, height, x, y, color);
        figures.add(rectangle);
        rectangle.draw(gc);
    }

    private void redraw(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 600);
        for (Figure figure : figures) {
            figure.draw(gc);
        }
    }
}