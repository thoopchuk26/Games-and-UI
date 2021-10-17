package play;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

    private double x;
    private double y;
    private double dx;
    private double dy;
    private double radius;
    private Circle c;
    private Pane pane;

    public Ball(double radius, Circle c, Pane pane) {
        x = radius + (Math.random() * (200 - (2 * radius)));
        y = radius + (Math.random() * (200 - (2 * radius)));
        dx = Math.random() * 5 - 1;
        dy = -7;
        this.radius = radius;
        this.c = c;
        this.pane = pane;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void move() {
        x += dx;
        double over = Math.max(0, x + radius - pane.getWidth());
        over = Math.max(over, radius - x);
        if (over > 0) {
            dx *= -1;
            x += Math.signum(dx) * over;
        }

        y += dy;
        dy+=0.25;
        over = Math.max(0, y + radius - pane.getHeight());
        over = Math.max(over, radius - y);
        if (over > 0) {
            dy *= -1;
            y += Math.signum(dy) * over;
        }
    }

    public void setColor(Color c) {
        this.c.setFill(c);
    }

    public void draw() {
        c.setRadius(radius);
        c.setTranslateX(x);
        c.setTranslateY(y);
    }
}


