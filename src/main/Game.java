package main;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Board board = new Board();

        Scanner input = new Scanner(System.in);
        System.out.println("Mis sõna tahad lauale panna?");

        String nextWord = input.nextLine();

        System.out.println("Kuhu tahad sõna panna? Kirjuta esimese tähe koordinaadid kujul x y.");

        int coordinateX = input.nextInt();
        int coordinateY = input.nextInt();

        board.makeMove(coordinateX, coordinateY, nextWord);
        board.showBoard();
        //Bag bag = new Bag();
        //Rack rack = new Rack();
        //Player player = new Player();

    }
}
