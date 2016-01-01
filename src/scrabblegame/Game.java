package scrabblegame;

import com.sun.xml.internal.fastinfoset.util.CharArray;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.ArrayList;
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

    }

    public String getPlayersRackString() {
        List playersRack = player.getPlayersRack();
        String text = Arrays.toString(playersRack.toArray(new Character[playersRack.size()])).replace("[", "").replace("]", "").replace(", ", "");
        return text;
    }

    public void printBoard(char[][] wholeBoard) {

        char[][] previousBoardState = board.getBoard();

        ArrayList <CoordinatePairs> listOfCoordinatePairs = new ArrayList<CoordinatePairs>(0);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (previousBoardState[i][j] != wholeBoard[i][j]) {
                    CoordinatePairs<Integer> pair = new CoordinatePairs<>(j,i);
                    listOfCoordinatePairs.add(pair);
                }

            }

        }
        for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
            System.out.println(listOfCoordinatePairs.get(i));
        }


        board.setBoard(wholeBoard);
    }

/*
    public boolean checkNewWordsLegality(String newWord, int[] firstLetterCoordinates, String wordDirection, List playersRack, List nextWordArray) {

        if (isWordAdjacent(firstLetterCoordinates, wordDirection, newWord) == false)
            return false;

        if (checkRackContainsWord(playersRack, nextWordArray) == false)
            return false;

        if (checkWordFromDictionary(newWord) == false)
            return false;

        if (checkForTwoLetterLength(newWord) == false)
            return false;

        if (isWordInOneLine(newWord) == false)
            return false;

        return true;
    }*/

/*    //Is the input adjacent to an already set word.
    public boolean isWordAdjacent(int[] firstCoordinates, String directionOfInput, String inputWord) {

        if (!isBoardEmpty()) {
            if (directionOfInput.equals("H")) {
                for (int i = 0; i < inputWord.length(); i++) {
                    if ((board[(firstCoordinates[0] - 1)][(firstCoordinates[1] + i)] != '?') ||
                            (board[(firstCoordinates[0] + 1)][(firstCoordinates[1] + i)] != '?') ||
                            (board[firstCoordinates[0]][(firstCoordinates[1] + i - 1)] != '?') ||
                            (board[firstCoordinates[0]][(firstCoordinates[1] + i + 1)] != '?')
                            ) {
                        return true; // Returns true, if there is at least one letter adjacent to a letter on the board
                    }
                }
            } else {
                for (int j = 0; j < inputWord.length(); j++) {

                    if ((board[(firstCoordinates[0] + j - 1)][firstCoordinates[1]] != '?') ||
                            (board[(firstCoordinates[0] + j + 1)][firstCoordinates[1]] != '?') ||
                            (board[(firstCoordinates[0] + j)][(firstCoordinates[1] - 1)] != '?') ||
                            (board[(firstCoordinates[0] + j)][(firstCoordinates[1] + 1)] != '?')
                            ) {
                        return true; // Returns true, if there is at least one letter adjacent to a letter on the board
                    }
                }
            }
            System.out.println("Ürita uuesti, sest sõnas peab olema vähemalt üks täht, mis on lauale pandud tähega kõrvuti.");
            return false;
        }
        return true;
    }*/
/*
    //Check whether user's word is comprised of the letters on the rack
    private boolean checkRackContainsWord(List playersRack, List nextWordArray) {
        if (!playersRack.containsAll(nextWordArray)) {
            System.out.println("Sinu käes olevatest tähtedest ei saa seda sõna moodustada.");
            return false;
        }
        return true;
    }

    //Check whether word is in dictionary
    private boolean checkWordFromDictionary(String newWord) {
        System.out.println(db.wordCount(newWord));
        if (db.wordCount(newWord) == 0) {
            System.out.println("Sõna pole sõnaraamatus.");
            return false;
        }
        return true;
    }

    //Check whether the word consists of at least two characters
    private boolean checkForTwoLetterLength(String newWord) {
        if (newWord.length() < 2) {
            System.out.println("Sõna on liiga lühike");
            return false;
        }
        return true;
    }
*//*
    public boolean isWordInOneLine(newWord) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if ([i][j] != ' ') {

                }
                return false;
            }
        }
        return true;
    }*//*

    //Check if board is empty, return if it is
    public boolean isBoardEmpty() {

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j] != '?') {
                    return false;
                }
            }
        }

        return true;
    }*/

    public void run() {
    }
}