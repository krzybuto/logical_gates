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
import org.example.logical_gates_draw.backend.Gate;
import org.example.logical_gates_draw.backend.Scheme;

public class HelloApplication extends Application {

    private String level1Path = "C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input1.txt";
    private String level2Path = "C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input2.txt";
    private String level3Path = "C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input3.txt";
    private String level4Path = "C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input4.txt";
    private int width;
    private int height;

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

        Button goToLevel = new Button("Graj");
        goToLevel.setOnAction(e -> stage.setScene(launchLevel(stage, level1Path)));
        goToLevel.setLayoutX(515);
        goToLevel.setLayoutY(300);
        goToLevel.setPrefWidth(250);
        goToLevel.setPrefHeight(100);
        goToLevel.setFont(Font.font("Arial",30));
        goToLevel.setStyle(
                "-fx-background-color: #006ab5;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 5px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-fill: black;"
        );

        Button goToExit = new Button("Wyjdź");
        goToExit.setOnAction(e -> System.exit(0));
        goToExit.setLayoutX(515);
        goToExit.setLayoutY(405);
        goToExit.setPrefWidth(250);
        goToExit.setPrefHeight(100);
        goToExit.setFont(Font.font("Arial",30));
        goToExit.setStyle(
                "-fx-background-color: #006ab5;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 5px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-fill: black;"
        );

        root.getChildren().add(goToLevel);
        root.getChildren().add(goToExit);
        root.getChildren().add(title);

        return scene;

    }

    private Scene launchLevel(Stage stage, String fileName) {
        Group root = new Group();
        width = 1280;
        height = 1024;
        Scene scene = new Scene(root, width, height, Color.web("6098f9"));

        Text textInput = new Text();
        textInput.setText("WEJŚCIE UKŁADU");
        textInput.setX(200);
        textInput.setY(100);
        textInput.setFont(Font.font("Verdana",30));

        Text textOutput = new Text();
        textOutput.setText("WEJŚCIE UKŁADU");
        textOutput.setX(900);
        textOutput.setY(100);
        textOutput.setFont(Font.font("Verdana",30));

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

        // Przycisk Pauza
        Button pauseButton = new Button("PAUZA");
        pauseButton.setStyle(
                "-fx-background-color: #006ab5;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 5px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-fill: black;"
        );
        pauseButton.setOnAction(e -> stage.setScene(pauseMenu(stage))); // Przechodzi do menu pauzy
        pauseButton.setLayoutX(1000);
        pauseButton.setLayoutY(860);
        pauseButton.setPrefWidth(200);
        pauseButton.setPrefHeight(50);
        pauseButton.setFont(Font.font("Arial",30));




        Scheme scheme = new Scheme();
//        System.out.println(scheme.ProcessFile(getClass().getResource(fileName).toExternalForm()) ? 1 : 0);
        System.out.println(scheme.ProcessFile(fileName) ? 1 : 0);

        for(Gate gate : scheme.getGates()) {
            Image g = gate.draw();
            ImageView gView = new ImageView(g);

            int gatesInLevel = scheme.GetNumberOfGatesForLevel(gate.getLevel());
            int levels = scheme.GetMaxLevel() + 1;
            gate.setPosY(gate.getNumberInLevel() * ((height - gatesInLevel * Gate.sizeY)/(gatesInLevel + 1) + Gate.sizeY) - Gate.sizeY/2);
            gate.setPosX((gate.getLevel()+1) * ((width - levels * Gate.sizeX)/(levels + 1) + Gate.sizeX) - Gate.sizeX/2);

//            gate.setPosX(100 + gate.getLevel() * (Gate.sizeX+60));
//            gate.setPosY(100 + gate.getNumberInLevel() * (Gate.sizeY+30));
            gView.setX(gate.getPosX());
            gView.setY(gate.getPosY());
            root.getChildren().add(gView);
        }


        root.getChildren().add(textInput);
        root.getChildren().add(textOutput);
        root.getChildren().add(rectangle);
        root.getChildren().add(info_level);
        root.getChildren().add(info_points);
        root.getChildren().add(info_time);
        root.getChildren().add(pauseButton);

        return scene;
    }

    private Scene chooseLevel(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root,1280,1024, Color.web("6098f9"));






        return scene;
    }








    // Tworzenie sceny menu pauzy
    private Scene pauseMenu(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root,1280,1024, Color.web("6098f9"));

        Text title = new Text();
        title.setText("PAUZA");
        title.setX(550);
        title.setY(175);
        title.setFont(Font.font("Serif",50));

        // Przyciski w menu pauzy
        Button resumeButton = new Button("Wznów Grę");
        resumeButton.setOnAction(e -> stage.setScene(launchLevel(stage, level1Path))); // Wznawia grę
        resumeButton.setLayoutX(515);
        resumeButton.setLayoutY(300);
        resumeButton.setPrefWidth(250);
        resumeButton.setPrefHeight(100);
        resumeButton.setFont(Font.font("Arial",30));
        resumeButton.setStyle(
                "-fx-background-color: #006ab5;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 5px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-fill: black;"
        );

        Button exitToMenuButton = new Button("Wyjdź do Menu");
        exitToMenuButton.setOnAction(e -> stage.setScene(mainMenu(stage))); // Powrót do menu głównego
        exitToMenuButton.setLayoutX(465);
        exitToMenuButton.setLayoutY(405);
        exitToMenuButton.setPrefWidth(350);
        exitToMenuButton.setPrefHeight(100);
        exitToMenuButton.setFont(Font.font("Arial",30));
        exitToMenuButton.setStyle(
                "-fx-background-color: #006ab5;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 5px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-fill: black;"
        );

        root.getChildren().add(title);
        root.getChildren().add(resumeButton);
        root.getChildren().add(exitToMenuButton);

        return scene;
    }

}