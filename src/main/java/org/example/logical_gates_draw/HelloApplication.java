package org.example.logical_gates_draw;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.logical_gates_draw.backend.Gate;
import org.example.logical_gates_draw.backend.GateType;
import org.example.logical_gates_draw.backend.Scheme;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Klasa główna aplikacji służącej do rysowania i symulacji bramek logicznych.
 * Aplikacja wykorzystuje bibliotekę JavaFX do tworzenia interfejsu użytkownika,
 * umożliwiając wybór poziomów, rozpoczęcie gry i wyświetlanie wyników.
 */
public class HelloApplication extends Application {
    /**
     * Lista ścieżek do plików z poziomami gry.
     */
    private final List<String> levelPaths = new ArrayList<>(List.of(
            "C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input1.txt",
            "C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input2.txt",
            "C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input3.txt",
            "C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input4.txt",
            "C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input5.txt",
            "C:\\Users\\Jacek\\IdeaProjects\\logical_gates_draw\\src\\main\\resources\\input6.txt"
    ));

    /**
     * Szerokość okna gry.
     */
    private int width;

    /**
     * Wysokość okna gry.
     */
    private int height;

    /**
     * Wynik gry.
     */
    private int score;

    /**
     * Obiekt losowy wykorzystywany do generowania przypadkowych elementów.
     */
    private Random random;

    /**
     * Zmienna przechowująca wybraną wartość logiczną.
     */
    private boolean chosenValue;

    /**
     * Czas gry w sekundach.
     */
    private int gameTimeInSeconds = 0;

    /**
     * Całkowity czas gry w sekundach.
     */
    private int totalGameTimeInSeconds = 0;

    /**
     * Zmienna informująca o wygranej w grze.
     */
    private boolean hasWon = false;

    /**
     * Zegar do odliczania czasu gry.
     */
    private Timeline timer;

    /**
     * Obiekt tekstowy wyświetlający czas gry.
     */
    Text infoTime;

    /**
     * Typ zadania w grze.
     */
    private TaskType taskType = TaskType.GATE;

    /**
     * Główna metoda uruchamiająca aplikację.
     *
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Inicjalizuje i wyświetla główne okno aplikacji.
     *
     * @param stage główny etap aplikacji
     * @throws Exception wyjątek, który może zostać zgłoszony podczas inicjalizacji aplikacji
     */
    @Override
    public void start(Stage stage) throws Exception {
        random = new Random();
        Image icon = new Image(getClass().getResource("/logo_gate.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("Magical Gates");
        stage.setResizable(false);

        infoTime = new Text();
        infoTime.setText("CZAS GRY: " + gameTimeInSeconds + " sekund");
        infoTime.setX(600);
        infoTime.setY(900);
        infoTime.setFont(Font.font("Verdana",30));
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            gameTimeInSeconds++;
            infoTime.setText("CZAS GRY: " + gameTimeInSeconds + " sekund");
        }));
        timer.setCycleCount(Timeline.INDEFINITE);

        Scene scene = mainMenu(stage);

        stage.setScene(scene);
        stage.show();

    }

    /**
     * Tworzy główne menu gry.
     *
     * @param stage główny etap aplikacji
     * @return scena głównego menu
     */
    private Scene mainMenu(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root,1280,1024, Color.web("6098f9"));

        Text title = new Text();
        title.setText("MAGICAL GATES");
        title.setX(275);
        title.setY(175);
        title.setFont(Font.font("Serif",90));

        totalGameTimeInSeconds=0;
        score=0;

        Button goToLevel = new Button("Graj");
        goToLevel.setOnAction(e -> {
            gameTimeInSeconds = 0;
            stage.setScene(launchLevel(stage, 1));
        });
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

        Button goToChooseLevel = new Button("Poziomy");
        goToChooseLevel.setOnAction(e -> stage.setScene(chooseLevel(stage)));
        goToChooseLevel.setLayoutX(515);
        goToChooseLevel.setLayoutY(405);
        goToChooseLevel.setPrefWidth(250);
        goToChooseLevel.setPrefHeight(100);
        goToChooseLevel.setFont(Font.font("Arial",30));
        goToChooseLevel.setStyle(
                "-fx-background-color: #006ab5;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 5px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-fill: black;"
        );

        Button goToSimulation = new Button("Symulacja");
        goToSimulation.setOnAction(e -> stage.setScene(startSimulation(stage)));
        goToSimulation.setLayoutX(515);
        goToSimulation.setLayoutY(510);
        goToSimulation.setPrefWidth(250);
        goToSimulation.setPrefHeight(100);
        goToSimulation.setFont(Font.font("Arial",30));
        goToSimulation.setStyle(
                "-fx-background-color: #006ab5;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 5px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-fill: black;"
        );

        Button goToExit = new Button("Wyjdź");
        goToExit.setOnAction(e -> System.exit(0));
        goToExit.setLayoutX(515);
        goToExit.setLayoutY(615);
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
        root.getChildren().add(goToChooseLevel);
        root.getChildren().add(goToSimulation);
        root.getChildren().add(goToExit);
        root.getChildren().add(title);

        return scene;
    }

    /**
     * Inicjalizuje i wyświetla scenę symulacji bramek logicznych.
     * Scena ta pozwala na wprowadzenie danych wejściowych dla bramek logicznych,
     * obliczenie wyników oraz ich wizualizację.
     *
     * @param stage główny etap aplikacji
     * @return scena symulacji bramek logicznych
     */
    private Scene startSimulation(Stage stage) {
        width = 1280;
        height = 1024;

        VBox leftColumn = new VBox(10);
        leftColumn.setPrefWidth(width * 0.3); // 30% of scene width
        leftColumn.setStyle("-fx-background-color: lightgray; -fx-padding: 10;");

        Pane rightColumn = new Pane();
        rightColumn.setPrefWidth(width * 0.7); // 70% of scene width
        rightColumn.setStyle("-fx-padding: 10;");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(leftColumn, rightColumn);

        HBox.setHgrow(leftColumn, Priority.ALWAYS);
        HBox.setHgrow(rightColumn, Priority.ALWAYS);

        Group root = new Group(hbox);
        Scene scene = new Scene(root, width, height, Color.web("6098f9"));

        Scheme scheme = new Scheme();
        TextArea schemeField = new TextArea();
        schemeField.setPrefWidth(width * 0.3);
        schemeField.setPrefHeight(height - 180);
        schemeField.setOnKeyPressed(e -> {
            if (Objects.requireNonNull(e.getCode()) == KeyCode.ENTER) {
                if (!schemeField.getText().isBlank()) {
                    rightColumn.getChildren().clear();
                    scheme.InitGatesFromString(schemeField.getText());
                    scheme.Calculate();
                    displayScheme(rightColumn, scheme, height, (int) (width * 0.3));
                }
            }
        });

        Button submitButton = new Button("Symuluj");
        submitButton.setPrefWidth(width * 0.3);
        submitButton.setPrefHeight(40);
        submitButton.setOnAction(e -> {
            if(!schemeField.getText().isBlank()) {
                rightColumn.getChildren().clear();
                scheme.InitGatesFromString(schemeField.getText());
                scheme.Calculate();
                displayScheme(rightColumn, scheme, height, (int) (width * 0.3));
            }
        });

        Button helpButton = new Button("Pomoc");
        helpButton.setPrefWidth(width * 0.3);
        helpButton.setPrefHeight(40);
        helpButton.setOnAction(e -> {
            stage.setScene(displaySchemeHelp(stage));
        });

        Button returnButton = new Button("Powrót");
        returnButton.setPrefWidth(width * 0.3);
        returnButton.setPrefHeight(40);
        returnButton.setOnAction(e -> {
            stage.setScene(mainMenu(stage));
        });


        leftColumn.getChildren().add(schemeField);
        leftColumn.getChildren().add(submitButton);
        leftColumn.getChildren().add(helpButton);
        leftColumn.getChildren().add(returnButton);

        return scene;
    }

    /**
     * Wyświetla scenę z pomocą dotyczącą wprowadzania schematów bramek logicznych.
     * Zawiera instrukcje dotyczące formatowania danych wejściowych i opis działania aplikacji.
     *
     * @param stage główny etap aplikacji
     * @return scena pomocy
     */
    private Scene displaySchemeHelp(Stage stage) {
        VBox helpLayout = new VBox();
        helpLayout.setPrefWidth(width/2);
        helpLayout.setPrefHeight(height);
        helpLayout.setAlignment(Pos.CENTER);
        helpLayout.setLayoutX(width/4);

        Group root = new Group(helpLayout);
        Scene scene = new Scene(root, width, height, Color.web("6098f9"));

        TextArea helpField = new TextArea();
        helpField.setText("""
                Aby zainicjować bramkę, proszę podać ciąg wejściowy w następującym formacie:
                                
                typ;poziom;wejścia;następneBramki
                            
                Gdzie:
                - typ: Liczba całkowita reprezentująca typ bramki:
                    0 - AND
                    1 - NAND
                    2 - OR
                    3 - NOR
                    4 - XOR
                    5 - XNOR
                    6 - NOT
                            
                - poziom: Liczba całkowita reprezentująca poziom bramki w układzie (np. 0, 1, 2 itd.)
                            
                - wejścia: Lista wartości wejściowych rozdzielona spacjami (np. "1 0 1") lub "-" jeśli brak wejść.
                            
                - następneBramki: Lista ID bramek, do których ta bramka prowadzi, rozdzielona spacjami, lub "-" jeśli brak następnych bramek.
                            
                Przykład:
                "0;1;1 0 1;2 3" zainicjuje bramkę AND na poziomie 1 z wejściami 1, 0 i 1, prowadzącą do bramek o ID 2 i 3.
                            
                Proszę podać kolejne bramki w kolejnych wierszach.
                ID bramki to numer linii, w której jest ona opisana, zaczynając od 0.
                """);
        helpField.setEditable(false);
        helpField.setWrapText(true);
        helpField.setPrefHeight((double) height / 2);

        Button returnButton = new Button("Powrót");
        returnButton.setPrefWidth(width * 0.3);
        returnButton.setPrefHeight(40);
        returnButton.setOnAction(e -> {
            stage.setScene(startSimulation(stage));
        });

        helpLayout.getChildren().add(helpField);
        helpLayout.getChildren().add(returnButton);

        return scene;
    }

    /**
     * Tworzy scenę gry na podstawie wybranego poziomu i inicjalizuje odpowiednie elementy interfejsu użytkownika.
     *
     * @param stage   Etap, na którym zostanie wyświetlona scena.
     * @param chosenLevel Poziom, który został wybrany przez użytkownika.
     * @return Scena z odpowiednimi elementami, zależnie od wybranego poziomu.
     */
    private Scene launchLevel(Stage stage, int chosenLevel) {
        String fileName = levelPaths.get(chosenLevel - 1);
        Scheme scheme = new Scheme();
        System.out.println(scheme.ProcessFile(fileName) ? 1 : 0);

        if(chosenLevel < 3)
            taskType = TaskType.OUTPUT;
        else if(chosenLevel < 5)
            taskType = TaskType.INPUT;
        else
            taskType = TaskType.GATE;

        Scheme copyScheme = new Scheme();
        copyScheme.ProcessFile(fileName);

        Group root = new Group();
        width = 1280;
        height = 1024;
        Scene scene = new Scene(root, width, height, Color.web("6098f9"));

        MenuBar menuBar = new MenuBar();

        Menu menuOptions = new Menu("Opcje");
        MenuItem goToMenu = new MenuItem("Wyjdź do menu");
        goToMenu.setOnAction(e -> stage.setScene(mainMenu(stage)));

        MenuItem exitGame = new MenuItem("Zakończ grę");
        exitGame.setOnAction(e -> System.exit(0));


        menuOptions.getItems().addAll(goToMenu, exitGame);

        // Dodanie menu do paska menu
        menuBar.getMenus().add(menuOptions);

        // Dodanie paska menu do VBox
        root.getChildren().add(menuBar);

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

        //informacja o poziomie, punktacji i czasie gry
        Text infoLevel = new Text();
        infoLevel.setText(MessageFormat.format("POZIOM: {0}", chosenLevel));
        infoLevel.setX(100);
        infoLevel.setY(900);
        infoLevel.setFont(Font.font("Verdana",30));

        Text infoPoints = new Text();
        infoPoints.setText(MessageFormat.format("PUNKTY: {0}", score));
        infoPoints.setX(350);
        infoPoints.setY(900);
        infoPoints.setFont(Font.font("Verdana",30));

        //tło prostokąta z ramką
        Rectangle rectangle = new Rectangle(1200, 100, Color.web("007dd6"));
        rectangle.setX(40);
        rectangle.setY(840);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.BLACK);

        Button submitButton = new Button("SPRAWDŹ");
        submitButton.setStyle(
                "-fx-background-color: #006ab5;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 5px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-fill: black;"
        );
        submitButton.setOnAction(e -> {
            timer.stop();
            totalGameTimeInSeconds+=gameTimeInSeconds;
            System.out.println(chosenValue == scheme.Calculate() ? "WIN" : "LOSE");
            if(chosenValue == scheme.Calculate()){
                score+=100 - gameTimeInSeconds;
                hasWon = true;
            }else{
                hasWon = false;
            }
            stage.setScene(scoreMenu(stage, chosenLevel));
        });
        submitButton.setLayoutX(1000);
        submitButton.setLayoutY(860);
        submitButton.setPrefWidth(200);
        submitButton.setPrefHeight(50);
        submitButton.setFont(Font.font("Arial",30));

        int gateId = random.nextInt(scheme.getGates().size());
        int gateLeafId = random.nextInt(scheme.getGates().size() - scheme.GetNumberOfGatesForLevel(0), scheme.getGates().size());

        for(Gate gate : scheme.getGates()) {
            int gatesInLevel = scheme.GetNumberOfGatesForLevel(gate.getLevel());
            int levels = scheme.GetMaxLevel() + 1;
            gate.setPosY(gate.getNumberInLevel() * ((height - 100 - gatesInLevel * Gate.sizeY)/(gatesInLevel + 1) + Gate.sizeY) - Gate.sizeY/2);
            gate.setPosX((gate.getLevel()+1) * ((width - levels * Gate.sizeX)/(levels + 1) + Gate.sizeX) - Gate.sizeX/2);

            if (taskType != TaskType.GATE) {
                Image g = gate.draw();
                ImageView gView = new ImageView(g);

                gView.setX(gate.getPosX());
                gView.setY(gate.getPosY());
                root.getChildren().add(gView);
            } else {
                if (gate.getId() != gateId) {
                    Image g = gate.draw();
                    ImageView gView = new ImageView(g);

                    gView.setX(gate.getPosX());
                    gView.setY(gate.getPosY());
                    root.getChildren().add(gView);
                } else {
                    ComboBox<String> comboBox = new ComboBox<>();

                    if(gate.getInputs().size() > 1)
                        comboBox.getItems().addAll("AND", "OR", "XOR", "NAND", "NOR", "XNOR");
                    else
                        comboBox.getItems().addAll("AND", "OR", "NOT", "XOR", "NAND", "NOR", "XNOR");
                    comboBox.setLayoutX(gate.getPosX());
                    comboBox.setLayoutY(gate.getPosY());
                    comboBox.setPrefWidth(200);
                    comboBox.setOnAction(e -> {
                        GateType gateType = GateType.fromString(comboBox.getValue());
                        chosenValue = copyScheme.ChangeGateTypeAndCalculate(gateId, gateType);
                    });
                    comboBox.setStyle("-fx-font-size: 30px; -fx-background-color: lightblue;");
                    root.getChildren().add(comboBox);
                }
            }

            if (gate.getLevel() == 0) {
                int sizeYPerInput = Gate.sizeY / gate.getInputs().size();
                if(taskType != TaskType.INPUT || gate.getId() != gateLeafId) {
                    int k = 1;
                    for (boolean input : gate.getInputs()) {
                        Text inputText = new Text();
                        inputText.setText(MessageFormat.format("{0}", input ? 1 : 0));
                        inputText.setX(gate.getPosX() - Gate.sizeX / gate.getInputs().size());
                        inputText.setY(gate.getPosY() + sizeYPerInput * k);
                        k++;
                        inputText.setFont(Font.font("Verdana", sizeYPerInput));
                        root.getChildren().add(inputText);
                    }
                }
                else {
                    int inputId = random.nextInt(gate.getInputs().size()) + 1;
                    int k = 1;
                    for (boolean input : gate.getInputs()) {
                        if(inputId != k) {
                            Text inputText = new Text();
                            inputText.setText(MessageFormat.format("{0}", input ? 1 : 0));
                            inputText.setX(gate.getPosX() - Gate.sizeX / gate.getInputs().size());
                            inputText.setY(gate.getPosY() + sizeYPerInput * k);
                            k++;
                            inputText.setFont(Font.font("Verdana", sizeYPerInput));
                            root.getChildren().add(inputText);
                        } else {
                            ComboBox<String> comboBox = new ComboBox<>();
                            comboBox.getItems().addAll("1", "0");
                            comboBox.setLayoutY(gate.getPosY());
                            comboBox.setPrefHeight(sizeYPerInput);
                            comboBox.setOnAction(e -> {
                                chosenValue = copyScheme.ChangeGateInputAndCalculate(gateLeafId, inputId, comboBox.getValue().equals("1"));
                            });
                            comboBox.setStyle("-fx-font-size: 15px; -fx-background-color: lightblue;");
                            comboBox.setPrefWidth(65);
                            comboBox.setLayoutX(gate.getPosX() - 100);
                            comboBox.setPrefHeight(30);
                            root.getChildren().add(comboBox);
                            k++;
                        }
                    }
                }
            }

            if(gate.getLevel() == levels - 1) {
                if(taskType == TaskType.OUTPUT) {
                    ComboBox<String> comboBox = new ComboBox<>();
                    comboBox.getItems().addAll("1", "0");
                    comboBox.setLayoutX(gate.getPosX() + Gate.sizeX + 10);
                    comboBox.setLayoutY(gate.getPosY());
                    comboBox.setPrefWidth(150);
                    comboBox.setOnAction(e -> {
                        chosenValue = comboBox.getValue().equals("1");
                    });
                    comboBox.setStyle("-fx-font-size: 30px; -fx-background-color: lightblue;");
                    root.getChildren().add(comboBox);
                } else {

                    Text inputText = new Text();
                    inputText.setText(MessageFormat.format("{0}", gate.calculate() ? 1 : 0));
                    inputText.setX(gate.getPosX() + Gate.sizeX + 10);
                    inputText.setY(gate.getPosY() + Gate.sizeY / 2);
                    inputText.setFont(Font.font("Verdana", 30));
                    root.getChildren().add(inputText);
                }

            }
        }

        for(Gate gate : scheme.getGates()) {
            for(Gate next : gate.getNext()) {
                Line line = new Line(gate.getPosX() + Gate.sizeX, gate.getPosY() + Gate.sizeY/2,
                        next.getPosX(), next.getPosY() + Gate.sizeY/2);
                root.getChildren().add(line);
            }
        }

        gameTimeInSeconds=0;
        timer.play();
        root.getChildren().add(textInput);
        root.getChildren().add(textOutput);
        root.getChildren().add(rectangle);
        root.getChildren().add(infoLevel);
        root.getChildren().add(infoPoints);
        root.getChildren().add(infoTime);
        root.getChildren().add(submitButton);

        return scene;
    }

    /**
     * Wyświetla schemat układu logicznego na ekranie, rysując bramki logiczne i łączące je linie.
     *
     * @param root   Korzeń (Pane) na którym będą wyświetlane elementy wizualne.
     * @param scheme Obiekt reprezentujący schemat układu logicznego.
     * @param vBoxHeight Wysokość obszaru, w którym będą rysowane elementy.
     * @param vBoxWidth Szerokość obszaru, w którym będą rysowane elementy.
     */
    private void displayScheme(Pane root, Scheme scheme, int vBoxHeight, int vBoxWidth) {
        for(Gate gate : scheme.getGates()) {
            int gatesInLevel = scheme.GetNumberOfGatesForLevel(gate.getLevel());
            int levels = scheme.GetMaxLevel() + 1;
            gate.setPosY(gate.getNumberInLevel() * ((height - 100 - gatesInLevel * Gate.sizeY)/(gatesInLevel + 1) + Gate.sizeY) - Gate.sizeY/2);
            gate.setPosX((gate.getLevel()+1) * ((width - vBoxWidth - levels * Gate.sizeX)/(levels + 1) + Gate.sizeX) - Gate.sizeX/2);

            Image g = gate.draw();
            ImageView gView = new ImageView(g);

            gView.setX(gate.getPosX());
            gView.setY(gate.getPosY());
            root.getChildren().add(gView);

            if (gate.getLevel() == 0) {
                int numberOfGateInputs = gate.getInputs().isEmpty() ? 1 : gate.getInputs().size();
                int sizeYPerInput = Gate.sizeY / numberOfGateInputs;
                int k = 1;
                for (boolean input : gate.getInputs()) {
                    Text inputText = new Text();
                    inputText.setText(MessageFormat.format("{0}", input ? 1 : 0));
                    inputText.setX(gate.getPosX() - Gate.sizeX / numberOfGateInputs);
                    inputText.setY(gate.getPosY() + sizeYPerInput * k);
                    k++;
                    inputText.setFont(Font.font("Verdana", sizeYPerInput));
                    root.getChildren().add(inputText);
                }
            }

            if(gate.getLevel() == levels - 1) {
                Text inputText = new Text();
                inputText.setText(MessageFormat.format("{0}", gate.calculate() ? 1 : 0));
                inputText.setX(gate.getPosX() + Gate.sizeX + 10);
                inputText.setY(gate.getPosY() + Gate.sizeY / 2);
                inputText.setFont(Font.font("Verdana", 30));
                root.getChildren().add(inputText);

            }
        }

        for(Gate gate : scheme.getGates()) {
            for(Gate next : gate.getNext()) {
                Line line = new Line(gate.getPosX() + Gate.sizeX, gate.getPosY() + Gate.sizeY/2,
                        next.getPosX(), next.getPosY() + Gate.sizeY/2);
                root.getChildren().add(line);
            }
        }
    }

    /**
     * Tworzy scenę, na której użytkownik może wybrać poziom gry.
     *
     * @param stage Etap, na którym zostanie wyświetlona scena.
     * @return Scena z przyciskami umożliwiającymi wybór poziomu.
     */
    private Scene chooseLevel(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root,1280,1024, Color.web("6098f9"));

        Text chooseLevelText = new Text();
        chooseLevelText.setText("WYBIERZ POZIOM");
        chooseLevelText.setX(375);
        chooseLevelText.setY(175);
        chooseLevelText.setFont(Font.font("Serif",60));

        for(int i = 0; i < levelPaths.size(); i++) {
            int level = i + 1;
            Button goToLevel = new Button(MessageFormat.format("POZIOM {0}", level));
            goToLevel.setOnAction(e -> {
                gameTimeInSeconds = 0;
                stage.setScene(launchLevel(stage, level));
            });
            goToLevel.setLayoutX(515);
            goToLevel.setLayoutY(250 + i * 105);
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
            root.getChildren().add(goToLevel);
        }

        Button goBack = new Button("POWRÓT");
        goBack.setOnAction(e -> {
            stage.setScene(mainMenu(stage));
        });
        goBack.setLayoutX(900);
        goBack.setLayoutY(900);
        goBack.setPrefWidth(150);
        goBack.setPrefHeight(75);
        goBack.setFont(Font.font("Arial",20));
        goBack.setStyle(
                "-fx-background-color: #006ab5;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 5px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-fill: black;"
        );



        root.getChildren().add(chooseLevelText);
        root.getChildren().add(goBack);

        return scene;
    }

    /**
     * Tworzy scenę z wynikami po zakończeniu poziomu gry.
     *
     * @param stage Etap, na którym zostanie wyświetlona scena.
     * @param chosenLevel Poziom, który został zakończony.
     * @return Scena z wynikami, przyciskami umożliwiającymi przejście do następnego poziomu, powtórzenie poziomu lub powrót do menu głównego.
     */
    private Scene scoreMenu (Stage stage, int chosenLevel) {

        Group root = new Group();
        Scene scene = new Scene(root,1280,1024, Color.web("6098f9"));

        Text titleOfLevel = new Text();
        titleOfLevel.setText(MessageFormat.format("POZIOM {0}", chosenLevel));
        titleOfLevel.setX(525);
        titleOfLevel.setY(175);
        titleOfLevel.setFont(Font.font("Serif",50));

        Text endText = new Text();
        endText.setText(hasWon ? "Dobrze" : "Źle");
        endText.setX(565);
        endText.setY(275);
        endText.setFont(Font.font("Serif",50));

        Text resultPoints = new Text();
        resultPoints.setText(MessageFormat.format("Twój całkowity wynik to: {0}", score));
        resultPoints.setX(380);
        resultPoints.setY(375);
        resultPoints.setFont(Font.font("Verdana",30));

        Text resultTime = new Text();
        resultTime.setText(MessageFormat.format("Twój całkowity czas to: {0} sekund", totalGameTimeInSeconds));
        resultTime.setX(380);
        resultTime.setY(500);
        resultTime.setFont(Font.font("Verdana",30));

        Button nextLevel = new Button("NASTĘPNY POZIOM");
        nextLevel.setOnAction(e -> stage.setScene(launchLevel(stage, chosenLevel+1))); // Powrót do menu głównego
        nextLevel.setLayoutX(475);
        nextLevel.setLayoutY(650);
        nextLevel.setFont(Font.font("Arial",30));

        Button repeatLevel = new Button("POWTÓRZ POZIOM");
        repeatLevel.setOnAction(e -> stage.setScene(launchLevel(stage, chosenLevel))); // Powrót do menu głównego
        repeatLevel.setLayoutX(475);
        repeatLevel.setLayoutY(650);
        repeatLevel.setFont(Font.font("Arial",30));

        Button exitToMenuButton = new Button("Wyjdź do Menu");
        exitToMenuButton.setOnAction(e -> stage.setScene(mainMenu(stage))); // Powrót do menu głównego
        exitToMenuButton.setLayoutX(width/2 - 150);
        exitToMenuButton.setPrefWidth(300);
        exitToMenuButton.setLayoutY(650);
        exitToMenuButton.setFont(Font.font("Arial",30));


        root.getChildren().add(titleOfLevel);
        root.getChildren().add(endText);
        root.getChildren().add(resultPoints);
        root.getChildren().add(resultTime);
        if (hasWon){
            if (chosenLevel < levelPaths.size()) {
                root.getChildren().add(nextLevel);
            }
            else{
                root.getChildren().add(exitToMenuButton);
            }
        }
        else {
            root.getChildren().add(repeatLevel);
        }

        return scene;
    }

}