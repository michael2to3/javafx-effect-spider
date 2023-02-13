package javafx.effect.spider;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

class Effect {
    private final Canvas canvas;
    private final GraphicsContext ctx;
    private final int count;
    private List<PointGraphics> points;
    private Line line;

    public Effect(final Canvas canvas, final int count) {
        this.canvas = canvas;
        this.ctx = canvas.getGraphicsContext2D();
        this.count = count;
        genPoints();
        this.line = new Line(100d, 1d, Color.RED);
    }

    private void genPoints() {
        points = new ArrayList<>();
        Point point;
        PointGraphics pg;
        for (int i = 0; i < count; ++i) {
            point = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2, 0d, 0d, 20d);
            pg = new PointGraphics(point, Color.RED, (int) Random.getRange(100, 10000));
            points.add(pg);
        }
    }

    public void run() {
        final double width = canvas.getWidth();
        final double height = canvas.getHeight();
        final int speed = 2;
        for (var point : points) {
            point.getPoint().updateVelocity(-speed, speed);
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            ctx.setFill(Color.BLACK);
            ctx.fillRect(0, 0, width, height);
            drawLines();
            drawPoints();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void drawPoints() {
        final double width = canvas.getWidth();
        final double height = canvas.getHeight();
        for (var point : points) {
            point.draw(canvas);
            point.getPoint().updatePosition(width, height, 1);
        }
    }

    private void drawLines() {
        for (var ipoint : points) {
            for (var jpoint : points) {
                if (ipoint != jpoint) {
                    line.draw(canvas, ipoint, jpoint);
                }
            }
        }
    }
}
