package scrabblegame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Gui {

    Label rack = new Label();
    Button makeMove = new Button();
    Label totalLabel = new Label();
    Label pointsTotal = new Label();
    Label moveLabel = new Label();
    Label pointsMove = new Label();
    ArrayList generatedRack = new ArrayList<>();
    VBox mostBottom = new VBox();
    Game game = new Game();
    Label errors = new Label();
    TextField[][] squares = new TextField[15][15];

    public void createScene() {

        Stage primaryStage = new Stage();
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Scrabble");
        System.setProperty("prism.text", "t2k");

        VBox heading = new VBox();
        Label topHeading = new Label("Scrabble");
        heading.getChildren().add(topHeading);
        heading.setAlignment(Pos.CENTER);
        heading.setPadding(new Insets (15, 10, 5, 10));

        GridPane topGrid = new GridPane();
        topGrid.setId("board");
        topGrid.setAlignment(Pos.CENTER);
        topGrid.setGridLinesVisible(false);

        final TextField[][] squares = new TextField[15][15];

        //Textfields as 15*15 grid
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {

                squares[i][j] = new TextField("");
                squares[i][j].setPrefSize(30, 30);
                final int finalI = i;
                final int finalJ = j;
                squares[i][j].textProperty().addListener((ov, oldValue, newValue) -> {
                    squares[finalI][finalJ].setText(newValue.toUpperCase());
                });
                topGrid.add(squares[i][j], i, j);

            }

        }

        // James_D http://stackoverflow.com/questions/34407694/javafx-textfield-allow-only-one-letter-to-be-typed?lq=1
        //Allow only one letter to be typed:
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                squares[i][j].setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
                    String newText = change.getControlNewText();
                    if (newText.length() > 1) {
                        return null ;
                    } else {
                        return change ;
                    }
                }));

            }
        }

        GridPane bottomGrid = new GridPane();

        bottomGrid.setAlignment(Pos.CENTER);
        bottomGrid.setGridLinesVisible(false);
        bottomGrid.setPadding(new Insets (5, 20, 5, 20));
        bottomGrid.setVgap(12);
        bottomGrid.setHgap(15);

        GridPane.setConstraints(rack, 1, 1);
        rack.setText(game.getPlayersRackString());
        rack.setAlignment(Pos.CENTER_LEFT);

        Button makeMove = new Button("Tee käik ära");
        GridPane.setConstraints(makeMove, 1, 2);
        makeMove.setAlignment(Pos.CENTER_LEFT);

        //Make the move if the player has followed rules
        makeMove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                char[][] wholeBoard = new char[15][15];

                for (int row = 0; row < squares.length; row++) {
                    for (int column = 0; column < squares.length; column++) {

                        if (squares[row][column].getText().isEmpty()) {
                            wholeBoard[row][column] = ' ';
                        } else {
                            wholeBoard[row][column] = squares[row][column].getText().charAt(0);
                        }
                    }
                }

                if (game.makeMoveOnBoard(wholeBoard) == true) {
                    rack.setText(game.getPlayersRackString());
                    pointsMove.setText(String.valueOf(game.getPointsLastMove()));
                    pointsTotal.setText(String.valueOf(game.player.getPlayersPoints()));
                    errors.setText("");

                    for (int i = 0; i < 15; i++) {
                        for (int j = 0; j < 15; j++) {
                            if (!squares[i][j].getText().isEmpty()) {
                                squares[i][j].setDisable(true);
                            } else {
                                squares[i][j].setDisable(false);
                            }
                        }
                    }
                } else {
                    errors.setText(game.getMessagesToStrings());
                }

            }
        });

        Label moveLabel = new Label("Punktid selle käigu eest");
        GridPane.setConstraints(moveLabel, 2, 1);

        Label totalLabel = new Label("Punktid kokku");
        GridPane.setConstraints(totalLabel, 2, 2);

        GridPane.setConstraints(pointsMove, 3, 1);
        pointsMove.setText(String.valueOf(game.getPointsLastMove()));

        GridPane.setConstraints(pointsTotal, 3, 2);
        pointsTotal.setText(String.valueOf(game.player.getPlayersPoints()));

        bottomGrid.getChildren().addAll(rack, makeMove, moveLabel, totalLabel, pointsMove, pointsTotal);

        mostBottom.setAlignment(Pos.CENTER);
        mostBottom.setId("errors");
        mostBottom.getChildren().add(errors);
        mostBottom.setPadding(new Insets(10, 10, 10, 30));

        errors.setTextFill(Color.web("#DC2E24"));
        errors.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.setSpacing(15);
        layout.getChildren().addAll(heading, topGrid, bottomGrid, mostBottom);

        Scene scene = new Scene(layout, 700, 750);
        scene.getStylesheets().add("scrabblegame/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
