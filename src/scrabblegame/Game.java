package scrabblegame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {

    Scanner input = new Scanner(System.in);
    Board board = new Board();
    Bag bag = new Bag();
    Player player = new Player();
    Word word = new Word();

    public Game() {
        List generatedRack = bag.getLetters(7);
        player.addLettersToRack(generatedRack);
        System.out.println("käivitus");
    }

    public String getPlayersRackString() {
        List playersRack = player.getPlayersRack();
        String text = Arrays.toString(playersRack.toArray(new Character[playersRack.size()])).replace("[", "").replace("]", "").replace(", ", "");
        return text;
    }

    public void run() {


    /*while (bag.tilesInBag.size() > 0 ) {

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
               *//**//* if (!board.checkNewWordsLegality(nextWord, player.getPlayersRack())) {
                    System.out.println("Proovi uuesti");
                } else {
                    List<Character> nextWordArray = nextWord.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
                    player.getTilesRemovedFromRack(nextWord);
                    numberOfCharsNeeded = player.getAmountOfTilesToAdd();
                    generatedRack = bag.getLetters(numberOfCharsNeeded);
                    player.addLettersToRack(generatedRack);*//**//*

            }
        }*/
    }
}