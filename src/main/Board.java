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

    public void makeMove(int coordinateX, int coordinateY, int coordinateX2, int coordinateY2, String nextWord) {

        //If coordinateX == coordinateX2, the direction is from left to right

        if (coordinateX == coordinateX2) {
            for (int i = 0; i < nextWord.length(); i++) {
                board[coordinateX][coordinateY + i] = nextWord.charAt(i);
            }

        //If coordinateX != coordinateX2, the direction is down
        } else
            for (int j = 0; j < nextWord.length(); j++) {
                board[coordinateX + j][coordinateY] = nextWord.charAt(j);
            }

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
