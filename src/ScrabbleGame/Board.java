package ScrabbleGame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    char[][] board = new char[15][15];

    //Generate the game board. The board is a square with a 15*15 grid of cells. Each cell accommodates one letter.
    public Board() {

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = '?';
            }
        }
    }

    /*
    Check whether the requested move is legal. Constraints that apply:
    1. The word must be within the board's boundaries.
    2. The word must be comprised of the letters available on the rack.
    3. At least one of the letters of the new word must already be on the board.
    Check until true.
    */

    public boolean checkNewWordsLegality(String newWord, int[] firstLetterCoordinates, String wordDirection, List playersRack, List nextWordArray) {

        if (checkHorizontally(newWord, firstLetterCoordinates, wordDirection) == false)
            return false;

        if (checkVertically(newWord, firstLetterCoordinates, wordDirection) == false)
            return false;

        if (checkRackContainsWord(playersRack, nextWordArray) == false)
            return false;

        return true;
    }

    //Check whether user can set the word horizontally

    private boolean checkHorizontally(String newWord, int[] firstLetterCoordinates, String wordDirection) {
        if ((wordDirection.equals("H") && ((firstLetterCoordinates[1] + newWord.length()) > 15))) {
            System.out.println(firstLetterCoordinates[1]);
            System.out.println(newWord.length());
            System.out.println("Sõna ei mahu horisontaalselt lauale.");
            return false;
        }
        return true;
    }

    //Check whether user can set the word vertically

    private boolean checkVertically(String newWord, int[] firstLetterCoordinates, String wordDirection) {
        if ((wordDirection.equals("V") && ((firstLetterCoordinates[0] + newWord.length()) > 15))) {
            System.out.println(firstLetterCoordinates[0]);
            System.out.println(newWord.length());
            System.out.println("Sõna ei mahu vertikaalselt lauale.");
            return false;
        }
        return true;
    }

    //Check whether user's word is comprised of the letters on the rack

    private boolean checkRackContainsWord(List playersRack, List nextWordArray) {
        if (!playersRack.containsAll(nextWordArray)) {
            System.out.println(playersRack);
            System.out.println(nextWordArray);
            System.out.println("Sinu käes olevatest tähtedest ei saa seda sõna moodustada.");
            return false;
        }
        return true;
    }


    //Make the move according to the first coordinates and the direction the user has set.

    public void makeMove(int[] firstLettersCoordinates, String direction, String nextWord) {

        if (direction.equals("H") || direction.equals("h")) {
            for (int i = 0; i < nextWord.length(); i++) {
                board[firstLettersCoordinates[0]][firstLettersCoordinates[1] + i] = nextWord.charAt(i);
            }

        } else
            for (int j = 0; j < nextWord.length(); j++) {
                board[firstLettersCoordinates[0] + j][firstLettersCoordinates[1]] = nextWord.charAt(j);
            }

    }

    //Print board
    public void showBoard() {

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}