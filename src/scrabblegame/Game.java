package scrabblegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    Board board = new Board();
    Bag bag = new Bag();
    Player player = new Player();

    public Game() {

        List generatedRack = bag.getLetters(7);
        player.addLettersToRack(generatedRack);

    }

    public String getPlayersRackString() {
        List playersRack = player.getPlayersRack();
        String text = Arrays.toString(playersRack.toArray(new Character[playersRack.size()])).replace("[", "").replace("]", "").replace(", ", "");
        return text;
    }

    public boolean makeMoveOnBoard(char[][] wholeBoard) {

        char[][] previousBoardState = board.getBoard();

        Boolean legal = true;

        ArrayList <CoordinatePairs> listOfCoordinatePairs = new ArrayList<CoordinatePairs>(0);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (previousBoardState[i][j] != wholeBoard[i][j]) {
                    CoordinatePairs pair = new CoordinatePairs(j,i);
                    listOfCoordinatePairs.add(pair);
                }
            }
        }

        //Checks if user used only letters from their rack
        List<Character> enteredArray = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (wholeBoard[i][j] != ' ') {
                    enteredArray.add(Character.toUpperCase(wholeBoard[i][j]));
                }
            }
        }

        List playersRack = player.getPlayersRack();
        System.out.println(playersRack);
        System.out.println(enteredArray);

        if (!playersRack.containsAll(enteredArray)) {
            legal = false;
            System.out.println("Sul pole neid tähti.");
        }

        //Checks whether the input is adjacent to already set letters
        if (!board.isBoardEmpty()) {
            for (int i = 0; i < listOfCoordinatePairs.size(); i++) {

                if ((wholeBoard[((listOfCoordinatePairs.get(i).p2) + 1)][((listOfCoordinatePairs.get(i).p1))] != ' ') || //Is there already a letter below the new input?
                        (wholeBoard[(listOfCoordinatePairs.get(i).p2)][((listOfCoordinatePairs.get(i).p1) - 1)] != ' ') || //Is there already a letter left to the new input?
                        (wholeBoard[(listOfCoordinatePairs.get(i).p2)][((listOfCoordinatePairs.get(i).p1) + 1)] != ' ') || //Is there already a letter right to the new input?
                        (wholeBoard[((listOfCoordinatePairs.get(i).p2) - 1)][listOfCoordinatePairs.get(i).p1] != ' ')); //Is there already a letter above the new input?
                else {
                    legal = false;
                    System.out.println("Vähemalt üks täht peab asuma kõrvuti juba laual oleva tähega.");
                }
            }
        }

        //Checks whether the input is in one line
        for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
            if ((listOfCoordinatePairs.get(0).p1 == listOfCoordinatePairs.get(i).p1) ||
                (listOfCoordinatePairs.get(0).p2 == listOfCoordinatePairs.get(i).p2) ) {
            } else {
                legal = false;
                System.out.println("Sisestatud tähed peavad olema ühes horisontaalses või vertikaalses reas.");
            }

        }

        if (legal == true) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    previousBoardState[i][j] = wholeBoard[i][j];
                }
            }
        }


        //Input to string
        char[] str = new char[enteredArray.size()];
        for (int i = 0; i < str.length; i++) {
            str[i]= enteredArray.get(i);
        }

        String inputAsString = new String(str);

        //Refresh rack
        if (legal == true) {
            player.getTilesRemovedFromRack(inputAsString);
            int amountOfTilesToAdd = player.getAmountOfTilesToAdd();
            List<Character> charsToAdd = bag.getLetters(amountOfTilesToAdd);
            player.addLettersToRack(charsToAdd);
        }


        return legal;
    }

/*

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
*/

    public void run() {
    }

}