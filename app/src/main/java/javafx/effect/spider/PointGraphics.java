package javafx.effect.spider;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;

class PointGraphics {
    Point point;
    Color color;
    final int timelife;
    int restlife;

    public PointGraphics(Point point, Color color, int timelife) {
        this.point = point;
        this.color = color;
        this.timelife = timelife;
        this.restlife = timelife;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTimelife() {
        return timelife;
    }

    public int getRestlife() {
        return restlife;
    }

    public void setRestlife(int restlife) {
        this.restlife = restlife;
    }

    public void draw(Canvas canvas) {
        var ctx = canvas.getGraphicsContext2D();
        ctx.setFill(color);

        ctx.fillOval(point.getX() - point.getR(), point.getY() - point.getR(), point.getR() * 2, point.getR() * 2);
        restlife--;
        if (restlife < 0) {
            respawn(canvas.getWidth(), canvas.getHeight());
        }
    }

    public void respawn(double width, double height) {
        this.restlife = timelife;
        point.setX(Random.getRange(0, width));
        point.setY(Random.getRange(0, height));
    }
}
