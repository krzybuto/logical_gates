package org.example.logical_gates_draw;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.logical_gates_draw.backend.Scheme;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Image icon = new Image(getClass().getResource("/logo_gate.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("Bramki logiczne");
        stage.setResizable(false);

        Scene scene = mainMenu(stage);

        stage.setScene(scene);
        stage.show();

    }

    private Scene mainMenu(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root,1280,1024, Color.web("6098f9"));

        Text title = new Text();
        title.setText("MAGICAL GATES");
        title.setX(275);
        title.setY(175);
        title.setFont(Font.font("Serif",90));

        Button goToLevel = new Button("PLAY");
        goToLevel.setOnAction(e -> stage.setScene(launchLevel()));

        root.getChildren().add(goToLevel);
        root.getChildren().add(title);

        return scene;

    }

    private Scene launchLevel() {
        Group root = new Group();
        Scene scene = new Scene(root,1280,1024, Color.web("6098f9"));

        Text text = new Text();
        text.setText("WEJŚCIE UKŁADU");
        text.setX(200);
        text.setY(100);
        text.setFont(Font.font("Verdana",30));

        //ifnormacja o poziomie, punktacji i czasie gry
        Text info_level = new Text();
        info_level.setText("POZIOM:");
        info_level.setX(100);
        info_level.setY(900);
        info_level.setFont(Font.font("Verdana",30));

        Text info_points = new Text();
        info_points.setText("PUNKTY:");
        info_points.setX(350);
        info_points.setY(900);
        info_points.setFont(Font.font("Verdana",30));

        Text info_time = new Text();
        info_time.setText("CZAS GRY:");
        info_time.setX(600);
        info_time.setY(900);
        info_time.setFont(Font.font("Verdana",30));

        //tło prostokąta z ramką
        Rectangle rectangle = new Rectangle(1200, 100, Color.web("007dd6"));
        rectangle.setX(40);
        rectangle.setY(840);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.BLACK);


        Scheme scheme = new Scheme();
//        System.out.println(scheme.ProcessFile(getClass().getResource("/input.txt").toExternalForm()) ? 1 : 0);
        System.out.println(scheme.ProcessFile("C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input.txt") ? 1 : 0);

        Image g1 = scheme.getGates().get(0).draw();
        ImageView g1view = new ImageView(g1);


        //ramka
//        Line frameUp = new Line();
//        frameUp.setStartX(40);
//        frameUp.setStartY(840);
//        frameUp.setEndX(1240);
//        frameUp.setEndY(840);
//        frameUp.setStrokeWidth(5);
//
//        Line frameDown = new Line();
//        frameDown.setStartX(40);
//        frameDown.setStartY(940);
//        frameDown.setEndX(1240);
//        frameDown.setEndY(940);
//        frameDown.setStrokeWidth(5);
//
//        Line frameLeft = new Line();
//        frameLeft.setStartX(40);
//        frameLeft.setStartY(840);
//        frameLeft.setEndX(40);
//        frameLeft.setEndY(940);
//        frameLeft.setStrokeWidth(5);
//
//        Line frameRight = new Line();
//        frameRight.setStartX(1240);
//        frameRight.setStartY(840);
//        frameRight.setEndX(1240);
//        frameRight.setEndY(940);
//        frameRight.setStrokeWidth(5);




        root.getChildren().add(text);
        root.getChildren().add(rectangle);
        root.getChildren().add(g1view);
//        root.getChildren().add(frameUp);
//        root.getChildren().add(frameDown);
//        root.getChildren().add(frameLeft);
//        root.getChildren().add(frameRight);
        root.getChildren().add(info_level);
        root.getChildren().add(info_points);
        root.getChildren().add(info_time);

        return scene;
    }

}