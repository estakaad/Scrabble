package ScrabbleGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Scrabble");

        Label heading = new Label("Scrabble");
        heading.setAlignment(Pos.TOP_CENTER);

        GridPane topGrid = new GridPane();
        topGrid.setAlignment(Pos.CENTER);
        topGrid.setStyle("-fx-background-color: darkgreen;");
        topGrid.setId("board-grid");
        TextField[][] slots = new TextField[15][15];

        for(int i = 0; i < slots.length; i++){
            for(int j = 0; j < slots.length;j++){

                slots[i][j] = new TextField(" ");
                slots[i][j].setPrefSize(50, 50);
                topGrid.add(slots[i][j], i, j);
            }

        }

        GridPane bottomGrid = new GridPane();
        bottomGrid.setGridLinesVisible(true);
        bottomGrid.setPadding(new Insets(10, 10, 10, 10));
        bottomGrid.setVgap(8);
        bottomGrid.setHgap(10);

        Label rack = new Label("Siia tulevad kasutaja käes olevad tähed");
        GridPane.setConstraints(rack, 1, 1);

        Button makeMove = new Button("Tee käik ära");
        GridPane.setConstraints(makeMove, 1, 2);
        makeMove.setOnAction(e -> board.showBoard());

        Label totalPoints = new Label("Punktid kokku");
        GridPane.setConstraints(totalPoints, 2, 1);

        Label points = new Label("Punktid selle käigu eest");
        GridPane.setConstraints(points, 2, 2);

        bottomGrid.getChildren().addAll(rack, makeMove, totalPoints, points);

        VBox wholeGame = new VBox();
        wholeGame.setSpacing(2);

        wholeGame.getChildren().addAll(heading, topGrid, bottomGrid);

        Scene scene = new Scene(wholeGame, 700, 900);
        scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    Scanner input = new Scanner(System.in);
    Board board = new Board();
    Bag bag = new Bag();
    Player player = new Player();
    Word word = new Word();

    public void run() {

        List generatedRack = bag.getLetters(7);
        player.addLettersToRack(generatedRack);
        int numberOfCharsNeeded = player.getAmountOfTilesToAdd();
        bag.getLetters(numberOfCharsNeeded);

        int i = 0;
        i++;
        while (i < 5) {

            player.printRack();

            String nextWord = askForWord();
            List<Character> nextWordArray = nextWord.chars().mapToObj(e->(char)e).collect(Collectors.toList());

            int[] firstCoordinates = askForFirstCoordinates();
            String direction = askForDirection();
            input.nextLine();

            while (!board.checkNewWordsLegality(nextWord, firstCoordinates, direction, player.getPlayersRack(), nextWordArray)) {
                nextWord = askForWord();
                nextWordArray = nextWord.chars().mapToObj(e->(char)e).collect(Collectors.toList());
                firstCoordinates = askForFirstCoordinates();
                direction = askForDirection();
                input.nextLine();
            }

            bag.getValue(nextWord);

            player.printRack();

            board.makeMove(firstCoordinates, direction, nextWord);
            player.getTilesRemovedFromRack(nextWord);

            numberOfCharsNeeded = player.getAmountOfTilesToAdd();
            generatedRack = bag.getLetters(numberOfCharsNeeded);
            player.addLettersToRack(generatedRack);
            board.showBoard();
        }

    }

    //Ask for input until the input is legal.
    private void askForInput() {

        String nextWord = askForWord();
        List<Character> nextWordArray = nextWord.chars().mapToObj(e->(char)e).collect(Collectors.toList());

        int[] firstCoordinates = askForFirstCoordinates();
        String direction = askForDirection();
        input.nextLine();

        while (!board.checkNewWordsLegality(nextWord, firstCoordinates, direction, player.getPlayersRack(), nextWordArray)) {
            nextWord = askForWord();
            nextWordArray = nextWord.chars().mapToObj(e->(char)e).collect(Collectors.toList());
            firstCoordinates = askForFirstCoordinates();
            direction = askForDirection();
            input.nextLine();
        }
    }

    //Get input for word
    public String askForWord() {

        System.out.println("Mis sõna tahad lauale panna?");

        String nextWord = input.nextLine();

        nextWord = nextWord.toUpperCase();

        Word word = new Word();

        word.setWord(nextWord);

        return nextWord;

    }

    //Get input for coordinates of the first letter
    public int[] askForFirstCoordinates() {

        System.out.println("Kuhu tahad sõna panna? Kirjuta esimese tähe koordinaadid kujul x y.");

        int coordinateX = input.nextInt();
        int coordinateY = input.nextInt();

        while ( ( coordinateX > 14 ) || ( coordinateY > 14 ) ) {
            System.out.println("Koordinaadid peavad olema vahemikus 0-14. Proovi uuesti.");
            coordinateX = input.nextInt();
            coordinateY = input.nextInt();
        }

        int[] firstCoordinates = {coordinateX, coordinateY};

        return firstCoordinates;

    }

    //Get input for the direction of the word
    private String askForDirection() {

        System.out.println("Kas sa tahad sõna sisestada vasakult paremale ehk horisontaalselt või ülalt alla ehk vertikaalselt? Kirjuta H või V.");

        String direction = input.next();

        direction = direction.toUpperCase();

        return direction;
    }

}