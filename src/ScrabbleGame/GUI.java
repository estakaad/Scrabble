package ScrabbleGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    Label heading;
    GridPane topGrid;
    GridPane bottomGrid;
    Scene scene;

    @Override

    //Method for the primary stage
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Scrabble");
        primaryStage.setResizable(false);

        Label heading = new Label("Scrabble");
        heading.setAlignment(Pos.TOP_CENTER);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //Method for creating the middle grid
    public GridPane setTopGrid() {
        GridPane topGrid = new GridPane();
        topGrid.setAlignment(Pos.CENTER);

        TextField[][] slots = new TextField[15][15];

        for(int i = 0; i < slots.length; i++) {
            for (int j = 0; j < slots.length; j++) {

                slots[i][j] = new TextField(" ");
                slots[i][j].setPrefSize(50, 50);
                topGrid.add(slots[i][j], i, j);
            }
        } return topGrid;
    }

    //Method for creating the bottom grid
    public GridPane setBottomGrid() {
        GridPane bottomGrid = new GridPane();

        bottomGrid.setAlignment(Pos.CENTER);
        bottomGrid.setGridLinesVisible(false);
        bottomGrid.setPadding(new Insets(20, 30, 20, 30));
        bottomGrid.setVgap(12);
        bottomGrid.setHgap(12);

        TextField rack = new TextField();

        GridPane.setConstraints(rack, 1, 1);

        Button makeMove = new Button("Tee käik ära");
        GridPane.setConstraints(makeMove, 1, 2);
        makeMove.setAlignment(Pos.CENTER);
//        makeMove.setOnAction(e -> board.showBoard());

        Label totalPoints = new Label("Punktid kokku");
        GridPane.setConstraints(totalPoints, 2, 1);

        Label points = new Label("Punktid selle käigu eest");
        GridPane.setConstraints(points, 2, 2);

        Label numberTotalPoints = new Label("1000");
        GridPane.setConstraints(numberTotalPoints, 3, 1);

        Label numberPoints = new Label("1");
        GridPane.setConstraints(numberPoints, 3, 2);

        bottomGrid.getChildren().addAll(rack, makeMove, totalPoints, points, numberTotalPoints, numberPoints);

        return bottomGrid;
    }

    public VBox wholeWindow() {
        VBox wholeGame = new VBox();
        wholeGame.setSpacing(2);

        wholeGame.getChildren().addAll(heading, topGrid, bottomGrid);

        Scene scene = new Scene(wholeGame, 800, 900);
        scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm());

        return wholeGame;
    }

}
