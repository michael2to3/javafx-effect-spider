package javafx.effect.spider;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

class Effect {
    private final Canvas canvas;
    private final GraphicsContext ctx;
    private final int count;
    private List<PointGraphics> points;
    private Line line;
    private double maxmousedist;
    private double minmousedist;
    private double maxspeed;
    private int statusClick = 0;

    public Effect(final Canvas canvas, final int count) {
        this.canvas = canvas;
        this.ctx = canvas.getGraphicsContext2D();
        this.count = count;
        genPoints();
        this.line = new Line(100d, 1d, Color.RED);
        this.maxmousedist = 200;
        this.minmousedist = 5;
        this.maxspeed = 3;
    }

    private void genPoints() {
        points = new ArrayList<>();
        Point point;
        PointGraphics pg;
        for (int i = 0; i < count; ++i) {
            double spawnX = Random.getRange(0, canvas.getWidth());
            double spawnY = Random.getRange(0, canvas.getHeight());
            point = new Point(spawnX, spawnY, 0d, 0d, 2d);
            pg = new PointGraphics(point, Color.RED, (int) Random.getRange(100, 10000));
            points.add(pg);
        }
    }

    private void gravity(final double ex, final double ey, final double k) {
        for (var point : points) {
            var x = point.getPoint().getX();
            var y = point.getPoint().getY();
            var r = EMath.distance(ex, ey, x, y);
            if (r < minmousedist) {
                point.getPoint().setDx(0);
                point.getPoint().setDy(0);
            } else if (r < maxmousedist) {
                double f = 100000 / (r * r);
                var theta = Math.atan2(y - ey, x - ex);
                var fx = f * Math.cos(theta);
                var fy = f * Math.sin(theta);

                if (fx > maxspeed) {
                    fx = maxspeed;
                }
                if (fy > maxspeed) {
                    fy = maxspeed;
                }

                point.getPoint().setDx(fx * k);
                point.getPoint().setDy(fy * k);

            }
        }
    }

    private void handlerMouse(Event event) {
        if (event instanceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent) event;
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_MOVED && statusClick != 0) {
                gravity(mouseEvent.getSceneX(), mouseEvent.getSceneY(), statusClick);
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    statusClick = 1;
                } else if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    statusClick = -1;
                }
            }
        }
    }

    public void run() {
        final double width = canvas.getWidth();
        final double height = canvas.getHeight();
        final int speed = 1;
        for (var point : points) {
            point.getPoint().updateVelocity(-speed, speed);
        }

        canvas.addEventFilter(Event.ANY, this::handlerMouse);

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
