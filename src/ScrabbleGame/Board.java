package ScrabbleGame;

public class Board {

    char [][] board = new char[15][15];

    //Generate the game board. The board is a square with a 15*15 grid of cells. Each cell accommodates one letter.
    public Board() {

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = '?';
            }
        }
    }

    public void makeMove(int[] firstCoordinates, int[] secondCoordinates, String nextWord) {

        //If firstCoordinates[0] == secondCoordinates[0] (x1 == x2), the direction is from left to right
        if (firstCoordinates[0] == secondCoordinates[0]) {
            for (int i = 0; i < nextWord.length(); i++) {
                board[firstCoordinates[0]][firstCoordinates[1] + i] = nextWord.charAt(i);
            }

        //If firstCoordinates[0] != secondCoordinates[0] (x1 != x2), the direction is down
        } else
            for (int j = 0; j < nextWord.length(); j++) {
                board[firstCoordinates[0] + j][firstCoordinates[1]] = nextWord.charAt(j);
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
