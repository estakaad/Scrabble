package main;

import java.util.Scanner;

public class Board {

    char [][] board = new char[15][15];

    public Board() {

        /*Generate the game board. The board is a square with a 15*15 grid of cells. Each cell accommodates
         one letter.
        */

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = 'x';
            }
        }
    }

    public void makeMove(int coordinateX, int coordinateY, String nextWord) {

        board[coordinateX - 1][coordinateY - 1] = nextWord.charAt(0);

    }

    public void showBoard() {

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
