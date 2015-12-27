package ScrabbleGame;

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

    public void run() {

        List generatedRack = bag.getLetters(7);
        player.addLettersToRack(generatedRack);
        int numberOfCharsNeeded = player.getAmountOfTilesToAdd();
        bag.getLetters(numberOfCharsNeeded);

        int i = 0;
        i++;
        while (i < 5) {

            player.printRack();

            String nextWord = askForWord();
            List<Character> nextWordArray = nextWord.chars().mapToObj(e->(char)e).collect(Collectors.toList());

            int[] firstCoordinates = askForFirstCoordinates();
            String direction = askForDirection();
            input.nextLine();

            while (!board.checkNewWordsLegality(nextWord, firstCoordinates, direction, player.getPlayersRack(), nextWordArray)) {
                nextWord = askForWord();
                nextWordArray = nextWord.chars().mapToObj(e->(char)e).collect(Collectors.toList());
                firstCoordinates = askForFirstCoordinates();
                direction = askForDirection();
                input.nextLine();
            }

            bag.getValue(nextWord);

            player.printRack();

            board.makeMove(firstCoordinates, direction, nextWord);
            player.getTilesRemovedFromRack(nextWord);

            numberOfCharsNeeded = player.getAmountOfTilesToAdd();
            generatedRack = bag.getLetters(numberOfCharsNeeded);
            player.addLettersToRack(generatedRack);
            board.showBoard();
        }

    }

    //Ask for input until the input is legal.
    private void askForInput() {

        String nextWord = askForWord();
        List<Character> nextWordArray = nextWord.chars().mapToObj(e->(char)e).collect(Collectors.toList());

        int[] firstCoordinates = askForFirstCoordinates();
        String direction = askForDirection();
        input.nextLine();

        while (!board.checkNewWordsLegality(nextWord, firstCoordinates, direction, player.getPlayersRack(), nextWordArray)) {
            nextWord = askForWord();
            nextWordArray = nextWord.chars().mapToObj(e->(char)e).collect(Collectors.toList());
            firstCoordinates = askForFirstCoordinates();
            direction = askForDirection();
            input.nextLine();
        }
    }

    //Get input for word
    public String askForWord() {

        System.out.println("Mis sõna tahad lauale panna?");

        String nextWord = input.nextLine();

        nextWord = nextWord.toUpperCase();

        Word word = new Word();

        word.setWord(nextWord);

        return nextWord;

    }

    //Get input for coordinates of the first letter
    public int[] askForFirstCoordinates() {

        System.out.println("Kuhu tahad sõna panna? Kirjuta esimese tähe koordinaadid kujul x y.");

        int coordinateX = input.nextInt();
        int coordinateY = input.nextInt();

        while ( ( coordinateX > 14 ) || ( coordinateY > 14 ) ) {
            System.out.println("Koordinaadid peavad olema vahemikus 0-14. Proovi uuesti.");
            coordinateX = input.nextInt();
            coordinateY = input.nextInt();
        }

        int[] firstCoordinates = {coordinateX, coordinateY};

        return firstCoordinates;

    }

    //Get input for the direction of the word
    private String askForDirection() {

        System.out.println("Kas sa tahad sõna sisestada vasakult paremale ehk horisontaalselt või ülalt alla ehk vertikaalselt? Kirjuta H või V.");

        String direction = input.next();

        direction = direction.toUpperCase();

        return direction;
    }

}