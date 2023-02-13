package javafx.effect.spider;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

class Line {
    private double maxdist;
    private double width;
    private Color color;

    public Line(double maxdist, double width, Color color) {
        this.maxdist = maxdist;
        this.width = width;
        this.color = color;
    }

    private double distance(final Point lhs, final Point rhs) {
        double x = lhs.getX();
        double y = lhs.getY();
        double tx = rhs.getX();
        double ty = rhs.getY();
        return Math.sqrt(Math.pow(x - tx, 2) + Math.pow(y - ty, 2));
    }

    public void draw(Canvas canvas, final PointGraphics lhs, final PointGraphics rhs) {
        var llife = lhs.getOpacity();
        var rlife = rhs.getOpacity();
        var dist = distance(lhs.getPoint(), rhs.getPoint());

        double maxwidth = Math.min(lhs.getPoint().getR(), rhs.getPoint().getR());
        double k = 1 - dist / maxdist;
        this.width = maxwidth * k;

        draw(canvas, lhs.getPoint(), rhs.getPoint(), Math.min(llife, rlife) / 4);
    }

    public void draw(Canvas canvas, final Point lhs, final Point rhs, final double opacity) {
        if (distance(lhs, rhs) > maxdist) {
            return;
        }
        var ctx = canvas.getGraphicsContext2D();
        double x = lhs.getX();
        double y = lhs.getY();
        double tx = rhs.getX();
        double ty = rhs.getY();

        ctx.setStroke(color.deriveColor(0, 1, 1, opacity));
        ctx.setLineWidth(width);
        ctx.strokeLine(x, y, tx, ty);
    }

}
