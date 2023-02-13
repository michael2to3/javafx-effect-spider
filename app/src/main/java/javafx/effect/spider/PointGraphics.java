package javafx.effect.spider;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class PointGraphics {
    private Point point;
    private Color color;
    private final double timelife;
    private double restlife;
    private double opacity;

    public PointGraphics(Point point, Color color, double timelife) {
        this.point = point;
        this.color = color;
        this.timelife = timelife;
        this.restlife = timelife;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
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

    public double getTimelife() {
        return timelife;
    }

    public double getRestlife() {
        return restlife;
    }

    public void setRestlife(int restlife) {
        this.restlife = restlife;
    }

    public void updateOpacity() {
        this.opacity = 1 - (timelife - restlife) / timelife;
    }

    public void draw(Canvas canvas) {
        updateOpacity();
        var ctx = canvas.getGraphicsContext2D();
        var ncolor = color.deriveColor(0, 1, 1, this.opacity);
        ctx.setFill(ncolor);

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
        point.setDx(Random.getRange(0, 3));
        point.setDy(Random.getRange(0, 3));
    }
}
