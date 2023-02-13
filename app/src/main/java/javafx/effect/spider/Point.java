package javafx.effect.spider;

public class Point {
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double r;

    public Point(
            double x,
            double y,
            double dx,
            double dy,
            double r) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.r = r;
    }

    public void updateVelocity(final double a, final double b) {
        dx += Random.getRange(a, b);
        dy += Random.getRange(a, b);
    }

    public void updatePosition(final double width, final double height, final double k) {
        if (x < 0) {
            dx = -dx;
        }
        if (y < 0) {
            dy = -dy;
        }
        if (x > width) {
            dx = -dx;
        }
        if (y > height) {
            dy = -dy;
        }
        x += dx * k;
        y += dy * k;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
}
