package ScrabbleGame;

import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);
    Board board = new Board();
    Bag bag = new Bag();

    public void run() {

        //Bag bag = new Bag();
        //bag.randomCharacters();

        int i = 0;
        i++;
        while (i < 5) {
            bag.getLetters();
            String nextWord = askForWord();
            int[] firstCoordinates = askForFirstCoordinates();
            int[] secondCoordinates = askForSecondCoordinates();
            input.nextLine();
            board.makeMove(firstCoordinates, secondCoordinates, nextWord);
            board.showBoard();
        }

        //Bag bag = new Bag();
        //Rack rack = new Rack();
        //Player player = new Player();

    }


    //Get input for word
    private String askForWord() {

        System.out.println("Mis sõna tahad lauale panna?");

        String nextWord = input.nextLine();

        return nextWord;

    }

    //Get input for coordinates of the first letter
    private int[] askForFirstCoordinates() {

        System.out.println("Kuhu tahad sõna panna? Kirjuta esimese tähe koordinaadid kujul x y.");

        int coordinateX = input.nextInt();
        int coordinateY = input.nextInt();

        while ( ( coordinateX > 15 ) || ( coordinateY > 15 ) ) {
            System.out.println("Koordinaadid peavad olema vahemikus 0-14. Proovi uuesti.");
            coordinateX = input.nextInt();
            coordinateY = input.nextInt();
        }

        int[] firstCoordinates = {coordinateX, coordinateY};

        return firstCoordinates;

    }

    //Get input for coordinates of the second letter, which will show the direction of the word
    private int[] askForSecondCoordinates() {

        System.out.println("Sisesta sõna teise tähe koordinaadid.");

        int coordinateX2 = input.nextInt();
        int coordinateY2 = input.nextInt();

        int[] secondCoordinates = {coordinateX2, coordinateY2};

        return secondCoordinates;
    }
}