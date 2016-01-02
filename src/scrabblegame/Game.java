package scrabblegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    Board board = new Board();
    Bag bag = new Bag();
    Player player = new Player();
    DictionaryDatabase db = new DictionaryDatabase();

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

        //Get new letters
        ArrayList <CoordinatePairs> listOfCoordinatePairs = new ArrayList<CoordinatePairs>(0);
        List<Character> enteredArray = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (previousBoardState[i][j] != wholeBoard[i][j]) {
                    CoordinatePairs pair = new CoordinatePairs(j,i);
                    listOfCoordinatePairs.add(pair);
                    enteredArray.add(wholeBoard[i][j]);
                }
            }
        }

        //Checks if user used only letters from their rack
        List playersRack = player.getPlayersRack();

        if (!playersRack.containsAll(enteredArray)) {
            legal = false;
            System.out.println("Sul pole neid tähti.");
        }

        //Checks whether the input is adjacent to already set letters
        if (!board.isBoardEmpty()) {
            for (int i = 0; i < listOfCoordinatePairs.size(); i++) {

                if ((wholeBoard[((listOfCoordinatePairs.get(i).y))][((listOfCoordinatePairs.get(i).x) + 1)] != ' ') || //Is there already a letter below the new input?
                        (wholeBoard[((listOfCoordinatePairs.get(i).y) - 1)][(listOfCoordinatePairs.get(i).x)] != ' ') || //Is there already a letter left to the new input?
                        (wholeBoard[((listOfCoordinatePairs.get(i).y) + 1)][(listOfCoordinatePairs.get(i).x)] != ' ') || //Is there already a letter right to the new input?
                        (wholeBoard[listOfCoordinatePairs.get(i).y][((listOfCoordinatePairs.get(i).x) - 1)] != ' ')); //Is there already a letter above the new input?
                else {
                    legal = false;
                    System.out.println("Vähemalt üks täht peab asuma kõrvuti juba laual oleva tähega.");
                }
            }
        }

        //Checks whether the input is in one line
        for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
            if ((listOfCoordinatePairs.get(0).y == listOfCoordinatePairs.get(i).y) ||
                (listOfCoordinatePairs.get(0).x == listOfCoordinatePairs.get(i).x) ) {
            } else {
                legal = false;
                System.out.println("Sisestatud tähed peavad olema ühes horisontaalses või vertikaalses reas.");
            }

        }

        //1. Get new word's first and last coordinates

        if (listOfCoordinatePairs.size() == 1 || ((listOfCoordinatePairs.get(0).x == listOfCoordinatePairs.get(1).x))) { // Word is set horizontally
            System.out.println("h");
            int i = 1;
            CoordinatePairs horizontalWordsLeftCoordinate = new CoordinatePairs((listOfCoordinatePairs.get(0).x), listOfCoordinatePairs.get(0).y);

            //Find the most left tile
            while (wholeBoard[listOfCoordinatePairs.get(0).y - i][listOfCoordinatePairs.get(0).x] != ' ') {
                horizontalWordsLeftCoordinate = new CoordinatePairs(listOfCoordinatePairs.get(0).x, listOfCoordinatePairs.get(0).y - i);
                i++;
            }

            System.out.println("left" + horizontalWordsLeftCoordinate);

            //Find the most right tile
            CoordinatePairs lastLetterCoordinates = listOfCoordinatePairs.get(listOfCoordinatePairs.size() - 1);
            CoordinatePairs horizontalWordsRightCoordinate = new CoordinatePairs(lastLetterCoordinates.x, lastLetterCoordinates.y);

            while (wholeBoard[lastLetterCoordinates.y + i][lastLetterCoordinates.x] != ' ') {
                horizontalWordsRightCoordinate = new CoordinatePairs(lastLetterCoordinates.x, lastLetterCoordinates.y + i);
                i++;
            }

            System.out.println("right" + horizontalWordsRightCoordinate);

        } else { // Word is set vertically
            System.out.println("v");
            //Find the uppermost tile
            int i = 1;
            CoordinatePairs verticalWordsUpperCoordinate = new CoordinatePairs(listOfCoordinatePairs.get(0).x, listOfCoordinatePairs.get(0).y);

            while (wholeBoard[listOfCoordinatePairs.get(0).y][(listOfCoordinatePairs.get(0).x) - i] != ' ') {
                verticalWordsUpperCoordinate = new CoordinatePairs((listOfCoordinatePairs.get(0).x - i), (listOfCoordinatePairs.get(0).y));
                i++;
            }
            System.out.println(verticalWordsUpperCoordinate);

            //Find the belowmost tile

            CoordinatePairs lastLetterCoordinates = listOfCoordinatePairs.get(listOfCoordinatePairs.size() - 1);
            CoordinatePairs verticalWordsLowerCoordinate = new CoordinatePairs(lastLetterCoordinates.x, lastLetterCoordinates.y);

            while (wholeBoard[lastLetterCoordinates.y][lastLetterCoordinates.x + i] != ' ') {
                verticalWordsLowerCoordinate = new CoordinatePairs(lastLetterCoordinates.x + i, lastLetterCoordinates.y);
                i++;
            }
            System.out.println(verticalWordsLowerCoordinate);
        }

        //2. Get new word's every coordinate
        //3. Get coordinates of all the new words that need to be checked

        //Character array to string
        String toCheck = Arrays.toString(enteredArray.toArray(new Character[enteredArray.size()])).replace("[", "").replace("]", "").replace(", ", "");
        toCheck = toCheck.toLowerCase();
        System.out.println(toCheck);

        //Check from dictionary
        if (db.wordCount(toCheck) == 0) {
            legal = false;
            System.out.println("Sõna pole sõnaraamatus.");
        } else {
            System.out.println("Sõna on sõnaraamatus.");
        };

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

        if (legal == true) {
            board.setBoard(wholeBoard);
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