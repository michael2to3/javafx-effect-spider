package javafx.effect.spider;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    final static int WIDTH = 1000;
    final static int HEIGHT = 1000;
    final static String nameProject = "Spider effect";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        var box = new VBox();
        var canvas = new Canvas(WIDTH, HEIGHT);
        var count = 500;
        var effect = new Effect(canvas, count);

        effect.run();

        box.getChildren().add(canvas);
        var scene = new Scene(box);

        primaryStage.setScene(scene);
        primaryStage.setTitle(nameProject);
        primaryStage.show();
    }
}
