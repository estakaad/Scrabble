package ScrabbleGame;

import java.util.ArrayList;
import java.util.Arrays;

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
    3. At least one of the letter's of the new word must already be on the board.
    Check until true.
    */

    public boolean checkNewWordsLegality(String newWord, int[] firstLetterCoordinates, String wordDirection) {

        if ((wordDirection.equals("H") && (firstLetterCoordinates[0] + newWord.length() > 14))) {
            System.out.println("Sõna ei mahu horisontaalselt lauale.");
            return false;
        }

        if ((wordDirection.equals("V") && (firstLetterCoordinates[1] + newWord.length() > 14))) {
            System.out.println("Sõna ei mahu vertikaalselt lauale.");
            return false;
        }

/*        if (!playersRack.containsAll(newWord)) {
            System.out.println("Sinu käes olevatest tähtedest ei saa seda sõna moodustada.");
            return false;
        }*/

        return true;
    }


    //Make the move according to the first coordinates and the direction the user has set.

    public void makeMove(int[] firstLettersCoordinates, String direction, String nextWord) {

        if (direction.equals("H")) {
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