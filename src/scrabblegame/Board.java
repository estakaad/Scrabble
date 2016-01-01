package scrabblegame;

import javafx.scene.control.TextField;

import java.util.List;

public class Board {

    char[][] board = new char[15][15];

    DictionaryDatabase db = new DictionaryDatabase();

    //Generate the game board. The board is a square with a 15*15 grid of cells. Each cell accommodates one letter.
    public Board() {

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] newBoard) {
        board = newBoard;
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

    public boolean checkNewWordsLegality(String newWord, int[] firstLetterCoordinates, String wordDirection, List playersRack, List nextWordArray) {

        if (isWordAdjacent(firstLetterCoordinates, wordDirection, newWord) == false)
            return false;

        if (checkRackContainsWord(playersRack, nextWordArray) == false)
            return false;

        if (checkWordFromDictionary(newWord) == false)
            return false;

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

    //Check whether user's word is comprised of the letters on the rack
    private boolean checkRackContainsWord(List playersRack, List nextWordArray) {
        if (!playersRack.containsAll(nextWordArray)) {
            System.out.println("Sinu käes olevatest tähtedest ei saa seda sõna moodustada.");
            return false;
        }
        return true;
    }

    //Is the input adjacent to an already set word.
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
    }

    // Where to set the input
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
    }

    public boolean isWordInOneLine(int lastMoveBoard[][]) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (lastMoveBoard[i][j] != ' ') {

                }
                return false;
            }
        }

        return true;

    }
}