package scrabblegame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui {

    public void createScene() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Scrabble");

        GridPane topGrid = new GridPane();
        topGrid.setAlignment(Pos.CENTER);
        TextField text = new TextField("siia tekst");
        topGrid.getChildren().addAll(text);

        GridPane bottomGrid = new GridPane();

        bottomGrid.setAlignment(Pos.CENTER);
        bottomGrid.setGridLinesVisible(false);
        bottomGrid.setPadding(new Insets(20, 30, 20, 30));
        bottomGrid.setVgap(12);
        bottomGrid.setHgap(12);

        TextField rack = new TextField("Siia tulevad tähed");
        GridPane.setConstraints(rack, 1, 1);

        Button makeMove = new Button("Tee käik ära");
        GridPane.setConstraints(makeMove, 1, 2);

        Label totalPoints = new Label("Punktid kokku");
        GridPane.setConstraints(totalPoints, 2, 1);

        Label points = new Label("Punktid selle käigu eest");
        GridPane.setConstraints(points, 2, 2);

        Label numberTotalPoints = new Label("1000");
        GridPane.setConstraints(numberTotalPoints, 3, 1);

        Label numberPoints = new Label("1");
        GridPane.setConstraints(numberPoints, 3, 2);

        bottomGrid.getChildren().addAll(rack, makeMove, totalPoints, points, numberTotalPoints, numberPoints);

        BorderPane layout = new BorderPane();
        layout.setTop(topGrid);
        layout.setBottom(bottomGrid);

        Scene scene = new Scene(layout, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
