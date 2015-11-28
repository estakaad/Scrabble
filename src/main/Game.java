package main;

import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);
    Board board = new Board();

    public void run() {

        //Bag bag = new Bag();
        //bag.randomCharacters();

        askForWord();
        askForFirstCoordinates();
        askForSecondCoordinates();

        board.makeMove(coordinateX, coordinateY, coordinateX2, coordinateY2, nextWord);

        board.showBoard();

        //Bag bag = new Bag();
        //Rack rack = new Rack();
        //Player player = new Player();

    }

    private void int askForSecondCoordinates() {

        System.out.println("Sisesta sõna teise tähe koordinaadid.");

        int coordinateX2 = input.nextInt();
        int coordinateY2 = input.nextInt();

        return
    }

    private void askForFirstCoordinates() {

        System.out.println("Kuhu tahad sõna panna? Kirjuta esimese tähe koordinaadid kujul x y.");

        int coordinateX = input.nextInt();
        int coordinateY = input.nextInt();

        while ( ( coordinateX > 15 ) || ( coordinateY > 15 ) ) {
            System.out.println("Koordinaadid peavad olema vahemikus 0-14. Proovi uuesti.");
            coordinateX = input.nextInt();
            coordinateY = input.nextInt();
        }

    }

    private void askForWord() {

        System.out.println("Mis sõna tahad lauale panna?");

        String nextWord = input.nextLine();

    }
}
