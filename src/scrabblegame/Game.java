package scrabblegame;

import java.lang.reflect.Array;
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
        String text = charListToString(playersRack);
        return text;
    }

    public boolean makeMoveOnBoard(char[][] wholeBoard) {

        char[][] previousBoardState = board.getBoard();

        Boolean legal = true;

        ArrayList<CoordinatePairs> listOfCoordinatePairs = pairsOfNewLetterCoordinates(previousBoardState, wholeBoard);
        List<Character> enteredArray = getEnteredArray(listOfCoordinatePairs, wholeBoard);

        if (isInOneLine(listOfCoordinatePairs) == false) {
            legal = false;
        };

        if (isInputComprisedOfRack(enteredArray) == false) {
            legal = false;
        }

        if (isAdjacent(wholeBoard, listOfCoordinatePairs) == false) {
            legal = false;
        }


        getEveryWordsEveryCoordinate(listOfCoordinatePairs, wholeBoard);

        //Refresh rack
        if (legal == true) {
            player.getTilesRemovedFromRack(charListToString(enteredArray));
            int amountOfTilesToAdd = player.getAmountOfTilesToAdd();
            List<Character> charsToAdd = bag.getLetters(amountOfTilesToAdd);
            player.addLettersToRack(charsToAdd);
        }

        if (legal == true) {
            board.setBoard(wholeBoard);
        }

        return legal;
    }

    //Which letters are new on the board?
    private ArrayList<CoordinatePairs> pairsOfNewLetterCoordinates(char[][] previousBoardState, char[][] wholeBoard) {

        ArrayList <CoordinatePairs> listOfCoordinatePairs = new ArrayList<CoordinatePairs>(0);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (previousBoardState[i][j] != wholeBoard[i][j]) {
                    CoordinatePairs pair = new CoordinatePairs(j,i);
                    listOfCoordinatePairs.add(pair);

                }
            }
        }

        return listOfCoordinatePairs;
    }

    private List<Character> getEnteredArray(ArrayList<CoordinatePairs> listOfCoordinatePairs, char[][] wholeBoard) {

        ArrayList<Character> enteredArray = new ArrayList<>();

        for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
            enteredArray.add(wholeBoard[listOfCoordinatePairs.get(i).y][listOfCoordinatePairs.get(i).x]);
        }

        return enteredArray;
    }

    private void getEveryWordsEveryCoordinate(ArrayList<CoordinatePairs> listOfCoordinatePairs, char[][] wholeBoard) {

        ArrayList<CoordinatePairs> firstNewWord = new ArrayList<>();

        CoordinatePairs horizontalWordsLeftCoordinate = getFirstLastCoordinates(listOfCoordinatePairs, wholeBoard).get(0);
        CoordinatePairs horizontalWordsRightCoordinate = getFirstLastCoordinates(listOfCoordinatePairs, wholeBoard).get(1);

        CoordinatePairs verticalWordsUpperCoordinate = getFirstLastCoordinates(listOfCoordinatePairs, wholeBoard).get(0);
        CoordinatePairs verticalWordsLowerCoordinate = getFirstLastCoordinates(listOfCoordinatePairs, wholeBoard).get(1);

        if ((listOfCoordinatePairs.size() == 1 || ((listOfCoordinatePairs.get(0).x == listOfCoordinatePairs.get(1).x)))) {
            for (int i = 1; i < horizontalWordsRightCoordinate.y - horizontalWordsLeftCoordinate.y; i++) {
                CoordinatePairs newPair = new CoordinatePairs(horizontalWordsLeftCoordinate.x, horizontalWordsLeftCoordinate.y + i);
            }
        }
            else {
            for (int i = 1; i < verticalWordsLowerCoordinate.x - verticalWordsUpperCoordinate.x; i++) {
                CoordinatePairs newPair = new CoordinatePairs(verticalWordsUpperCoordinate.x + 1, verticalWordsLowerCoordinate.y);
            }
        }

        System.out.println(listOfCoordinatePairs);

        /*for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
           System.out.println(wholeBoard[listOfCoordinatePairs.get(0).x][listOfCoordinatePairs.get(0).y]);
        }*/

    }

    //Get new word's first and last coordinates
    private List<CoordinatePairs> getFirstLastCoordinates(ArrayList<CoordinatePairs> listOfCoordinatePairs, char[][] wholeBoard) {

        ArrayList <CoordinatePairs> firstLastLetterCoordinates = new ArrayList<CoordinatePairs>();

        if (listOfCoordinatePairs.size() == 1 || ((listOfCoordinatePairs.get(0).x == listOfCoordinatePairs.get(1).x))) { // Word is set horizontally

            int i = 1;
            CoordinatePairs horizontalWordsLeftCoordinate = new CoordinatePairs((listOfCoordinatePairs.get(0).x), listOfCoordinatePairs.get(0).y);

            //Find the most left tile
            while (wholeBoard[listOfCoordinatePairs.get(0).y - i][listOfCoordinatePairs.get(0).x] != ' ') {
                horizontalWordsLeftCoordinate = new CoordinatePairs(listOfCoordinatePairs.get(0).x, listOfCoordinatePairs.get(0).y - i);
                i++;
            }

            //Find the most right tile
            CoordinatePairs lastLetterCoordinates = listOfCoordinatePairs.get(listOfCoordinatePairs.size() - 1);
            CoordinatePairs horizontalWordsRightCoordinate = new CoordinatePairs(lastLetterCoordinates.x, lastLetterCoordinates.y);

            while (wholeBoard[lastLetterCoordinates.y + i][lastLetterCoordinates.x] != ' ') {
                horizontalWordsRightCoordinate = new CoordinatePairs(lastLetterCoordinates.x, lastLetterCoordinates.y + i);
                i++;
            }

            firstLastLetterCoordinates.add(horizontalWordsLeftCoordinate);
            firstLastLetterCoordinates.add(horizontalWordsRightCoordinate);

        } else { // Word is set vertically

            //Find the uppermost tile
            int i = 1;
            CoordinatePairs verticalWordsUpperCoordinate = new CoordinatePairs(listOfCoordinatePairs.get(0).x, listOfCoordinatePairs.get(0).y);

            while (wholeBoard[listOfCoordinatePairs.get(0).y][(listOfCoordinatePairs.get(0).x) - i] != ' ') {
                verticalWordsUpperCoordinate = new CoordinatePairs((listOfCoordinatePairs.get(0).x - i), (listOfCoordinatePairs.get(0).y));
                i++;
            }

            //Find the belowmost tile

            CoordinatePairs lastLetterCoordinates = listOfCoordinatePairs.get(listOfCoordinatePairs.size() - 1);
            CoordinatePairs verticalWordsLowerCoordinate = new CoordinatePairs(lastLetterCoordinates.x, lastLetterCoordinates.y);

            while (wholeBoard[lastLetterCoordinates.y][lastLetterCoordinates.x + i] != ' ') {
                verticalWordsLowerCoordinate = new CoordinatePairs(lastLetterCoordinates.x + i, lastLetterCoordinates.y);
                i++;
            }
            firstLastLetterCoordinates.add(verticalWordsUpperCoordinate);
            firstLastLetterCoordinates.add(verticalWordsLowerCoordinate);
        }

        return firstLastLetterCoordinates;
    }

    //Checks whether the input is in one line
    private boolean isInOneLine(ArrayList<CoordinatePairs> listOfCoordinatePairs) {
        for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
            if ((listOfCoordinatePairs.get(0).y == listOfCoordinatePairs.get(i).y) ||
                    (listOfCoordinatePairs.get(0).x == listOfCoordinatePairs.get(i).x) ) {
            } else {
                System.out.println("Sisestatud tähed peavad olema ühes horisontaalses või vertikaalses reas.");
                return false;
            }
        }
        return true;
    }

    //Checks if user used only letters from their rack
    private boolean isInputComprisedOfRack(List enteredArray) {

        List playersRack = player.getPlayersRack();

        if (!playersRack.containsAll(enteredArray)) {
            System.out.println("Sul pole neid tähti.");
            return false;
        }

        return true;
    }

    //Checks whether the input is adjacent to already set letters
    private boolean isAdjacent(char[][] wholeBoard, ArrayList<CoordinatePairs> listOfCoordinatePairs) {

        if (!board.isBoardEmpty()) {
            for (int i = 0; i < listOfCoordinatePairs.size(); i++) {

                if ((wholeBoard[((listOfCoordinatePairs.get(i).y))][((listOfCoordinatePairs.get(i).x) + 1)] != ' ') || //Is there already a letter below the new input?
                        (wholeBoard[((listOfCoordinatePairs.get(i).y) - 1)][(listOfCoordinatePairs.get(i).x)] != ' ') || //Is there already a letter left to the new input?
                        (wholeBoard[((listOfCoordinatePairs.get(i).y) + 1)][(listOfCoordinatePairs.get(i).x)] != ' ') || //Is there already a letter right to the new input?
                        (wholeBoard[listOfCoordinatePairs.get(i).y][((listOfCoordinatePairs.get(i).x) - 1)] != ' ')); //Is there already a letter above the new input?
                else {
                    System.out.println("Vähemalt üks täht peab asuma kõrvuti juba laual oleva tähega.");
                    return false;
                }
            }
        }
        return true;
    }

    //Character list to string
    private String charListToString(List<Character> enteredArray) {

        char[] str = new char[enteredArray.size()];

        for (int i = 0; i < str.length; i++) {
            str[i]= enteredArray.get(i);
        }

        String inputAsString = new String(str);

        return inputAsString;
    }

    //Check from dictionary
    private boolean checkFromDict(String toCheck) {
        if (db.wordCount(toCheck.toLowerCase()) == 1) {
            System.out.println("Sõna on sõnaraamatus.");

        } else {
            System.out.println("Sõna pole sõnaraamatus.");
            return false;
        };

        return true;
    }

    //2. Get new word's every coordinate
    //3. Get coordinates of all the new words that need to be checked

    public void run() {

    }

}