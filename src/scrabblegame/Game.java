package scrabblegame;

import com.sun.xml.internal.fastinfoset.util.CharArray;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.lang.reflect.Array;
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

    public boolean printBoard(char[][] wholeBoard) {

        char[][] previousBoardState = board.getBoard();

        Boolean legal = true;

        ArrayList <CoordinatePairs> listOfCoordinatePairs = new ArrayList<CoordinatePairs>(0);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (previousBoardState[i][j] != wholeBoard[i][j]) {
                    CoordinatePairs pair = new CoordinatePairs(j,i);
                    listOfCoordinatePairs.add(pair);
                }
            }
        }

        //Checks whether the input is in one line
        for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
            if ((listOfCoordinatePairs.get(0).p1 == listOfCoordinatePairs.get(i).p1) || (listOfCoordinatePairs.get(0).p2 == listOfCoordinatePairs.get(i).p2) ) {
            } else {

                legal = false;
            }
            if (legal = false) {
                System.out.println("Tähed pole ühes reas, aga peaksid olema.");
            }

        }

        //Checks whether the input is adjacent to already set letters
        if (!board.isBoardEmpty()) {
            for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
                if ((wholeBoard[(listOfCoordinatePairs.get(i).p1) - 1][((listOfCoordinatePairs.get(i).p2) - 1)] != ' ') ||
                        (wholeBoard[listOfCoordinatePairs.get(i).p1][((listOfCoordinatePairs.get(i).p2) - 1)] != ' ') ||
                        (wholeBoard[listOfCoordinatePairs.get(i).p1][((listOfCoordinatePairs.get(i).p2) + 1)] != ' ') ||
                        (wholeBoard[((listOfCoordinatePairs.get(i).p1) + 1)][listOfCoordinatePairs.get(i).p2] != ' ')) ;
                else {
                    legal = false;
                }
            }
            if (legal = false) {
                System.out.println("Vähemalt üks täht peab asuma kõrvuti juba laual oleva tähega.");
            }
        }

        //Checks if user used only letters from their rack
        List<Character> enteredArray = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (wholeBoard[i][j] != ' ') {
                    enteredArray.add(Character.toUpperCase(wholeBoard[i][j]));
                }
            }
        }

        List playersRack = player.getPlayersRack();

        if (!playersRack.containsAll(enteredArray)) {
            System.out.println("Sul ei ole käes tähti, mida tahad lauale panna.");
            legal = false;
        }

        if (legal == true) {
            board.setBoard(wholeBoard);
        }

        return legal;
    }

/*

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
*/

    public void run() {
    }
}