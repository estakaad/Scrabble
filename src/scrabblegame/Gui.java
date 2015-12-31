package scrabblegame;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gui {

    TextField rack = new TextField();
    Button makeMove = new Button();
    Label totalLabel = new Label();
    TextField pointsTotal = new TextField();
    Label moveLabel = new Label();
    TextField pointsMove = new TextField();
    TextField messages = new TextField();
    ArrayList generatedRack = new ArrayList<>();
    Game game = new Game();

    public void createScene() {

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Scrabble");

        VBox heading = new VBox();
        Label topHeading = new Label("Scrabble");
        heading.getChildren().add(topHeading);
        heading.setAlignment(Pos.CENTER);
        heading.setPadding(new Insets (50, 10, 10, 10));

        GridPane topGrid = new GridPane();
        topGrid.setId("board");
        topGrid.setAlignment(Pos.CENTER);
        topGrid.setGridLinesVisible(false);

        final TextField[][] squares = new TextField[15][15];

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {

                squares[i][j] = new TextField("");
                squares[i][j].setPrefSize(30, 30);

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
        bottomGrid.setPadding(new Insets(20, 30, 20, 30));
        bottomGrid.setVgap(12);
        bottomGrid.setHgap(12);

        TextField rack = new TextField();
        GridPane.setConstraints(rack, 1, 1);
        rack.setText(game.getPlayersRackString());

        Button makeMove = new Button("Tee käik ära");
        GridPane.setConstraints(makeMove, 1, 2);

        TextField messages = new TextField();
        GridPane.setConstraints(messages, 1, 3);

        makeMove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Check validity

                for (int i = 0; i < squares.length; i++) {
                    for (int j = 0; j < squares.length; j++) {
                        if (!squares[i][j].getText().equals("")) {
                            System.out.printf(squares[i][j].getText());
                            //System.out.println(i + ", " + j);
                        }
                    }

                }
            }
        });

        Label totalLabel = new Label("Punktid kokku");
        GridPane.setConstraints(totalLabel, 2, 1);

        Label moveLabel = new Label("Punktid selle käigu eest");
        GridPane.setConstraints(moveLabel, 2, 2);

        TextField pointsTotal = new TextField("1000");
        GridPane.setConstraints(pointsTotal, 3, 1);

        TextField pointsMove = new TextField();
        GridPane.setConstraints(pointsMove, 3, 2);

        bottomGrid.getChildren().addAll(rack, messages, makeMove, totalLabel, moveLabel, pointsTotal, pointsMove);

        VBox layout = new VBox();
        layout.setSpacing(25);
        layout.getChildren().addAll(heading, topGrid, bottomGrid);

        Scene scene = new Scene(layout, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
