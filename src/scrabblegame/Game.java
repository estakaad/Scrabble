package scrabblegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    Board board = new Board();
    Bag bag = new Bag();
    Player player = new Player();
    DictionaryDatabase db = new DictionaryDatabase();
    private ArrayList<String> errorMessages = new ArrayList<String>();
    private int pointsLastMove;

    public Game() {
        List generatedRack = bag.getLetters(7);
        player.addLettersToRack(generatedRack);
        setPointsLastMove(0);
    }

    public String getPlayersRackString() {
        List playersRack = player.getPlayersRack();
        String text = charListToString(playersRack);
        return text;
    }

    public boolean makeMoveOnBoard(char[][] wholeBoard) {

        errorMessages.clear();

        char[][] previousBoardState = board.getBoard();

        if (Arrays.deepEquals(previousBoardState, wholeBoard)) {
            return false;
        }

        Boolean legal = true;

        ArrayList<CoordinatePair> listOfCoordinatePairs = pairsOfNewLetterCoordinates(previousBoardState, wholeBoard);

        if (listOfCoordinatePairs.size() < 2 && board.isBoardEmpty() == true) {
            errorMessages.add("Esimese käigu ajal tuleb lauale panna vähemalt kaks tähte.");
            return false;
        }

        List<Character> enteredArray = getEnteredArray(listOfCoordinatePairs, wholeBoard);
        ArrayList<String> newWords = allTheNewWords(listOfCoordinatePairs, wholeBoard);


        if (isInOneLine(listOfCoordinatePairs) == false) {
            errorMessages.add("Sõna peab olema ühes reas või veerus.");
            legal = false;
        };

        if (isInputComprisedOfRack(enteredArray) == false) {
            errorMessages.add("Sul pole neid tähti.");
            legal = false;
        }

        if (isAdjacent(previousBoardState, listOfCoordinatePairs) == false) {
            errorMessages.add("Vähemalt üks täht peab asuma kõrvuti juba laual oleva tähega.");
            legal = false;
        }

        if (checkWordsFromDict(newWords) == false) {
            errorMessages.add("Sellist sõna ei ole sõnaraamatus.");
            legal = false;
        }

        if (legal == true) {
            setPointsLastMove(calculatePoints(newWords));
            player.addPreviousPoints(getPointsLastMove());
        }

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

    public int getPointsLastMove() {
        return pointsLastMove;
    }

    public void setPointsLastMove(int pointsLastMove) {
        this.pointsLastMove = pointsLastMove;
    }

    public String getMessagesToStrings() {

        String errors = "";

        for (int i = 0; i < errorMessages.size(); i++) {
            errors += errorMessages.get(i) + System.lineSeparator();
        }

        return errors;

    }

    //Get all the new words
    private ArrayList<String> allTheNewWords(ArrayList<CoordinatePair> listOfCoordinatePairs, char[][] wholeBoard) {

        ArrayList<String> newWords = new ArrayList<>();
        ArrayList <CoordinatePair> firstLastLetterCoordinates = new ArrayList<CoordinatePair>();

        if (listOfCoordinatePairs.size() == 1 || ((listOfCoordinatePairs.get(0).x == listOfCoordinatePairs.get(1).x))) { // Word is set horizontally

            firstLastLetterCoordinates.add(getLeftLetterCoordinates(listOfCoordinatePairs, wholeBoard));
            firstLastLetterCoordinates.add(getRightLetterCoordinates(listOfCoordinatePairs, wholeBoard));

        } else { // Word is set vertically

            firstLastLetterCoordinates.add(getUpperLetterCoordinates(listOfCoordinatePairs, wholeBoard));
            firstLastLetterCoordinates.add(getLowerLetterCoordinates(listOfCoordinatePairs, wholeBoard));
        }

        newWords.add(getWordToCheck(firstLastLetterCoordinates, wholeBoard));

        if (listOfCoordinatePairs.size() == 1 || ((listOfCoordinatePairs.get(0).x == listOfCoordinatePairs.get(1).x))) {
            for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
                ArrayList<CoordinatePair> forVerticalCoordinates = new ArrayList<CoordinatePair>();
                forVerticalCoordinates.add(listOfCoordinatePairs.get(i));
                ArrayList<CoordinatePair> verCoordinatesToCheck = new ArrayList<CoordinatePair>();

                verCoordinatesToCheck.add(getUpperLetterCoordinates(forVerticalCoordinates, wholeBoard));
                verCoordinatesToCheck.add(getLowerLetterCoordinates(forVerticalCoordinates, wholeBoard));

                newWords.add(getWordToCheck(verCoordinatesToCheck, wholeBoard));

            }

        } else {
            for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
                ArrayList<CoordinatePair> forHorizontalCoordinates = new ArrayList<CoordinatePair>();
                forHorizontalCoordinates.add(listOfCoordinatePairs.get(i));
                ArrayList<CoordinatePair> horCoordinatesToCheck = new ArrayList<CoordinatePair>();

                horCoordinatesToCheck.add(getLeftLetterCoordinates(forHorizontalCoordinates, wholeBoard));
                horCoordinatesToCheck.add(getRightLetterCoordinates(forHorizontalCoordinates, wholeBoard));

                newWords.add(getWordToCheck(horCoordinatesToCheck, wholeBoard));

            }
        }
        return newWords;
    }

    //Check whether new words exist in the dictionary or consist of only one word
    private boolean checkWordsFromDict(ArrayList<String> newWords) {

        for (int i = 0; i < newWords.size(); i++) {
            if (checkIfWordIsARealWord(newWords.get(i)) == false) {
                return false;
            }
        }

        return true;
    }

    private int calculatePoints(ArrayList<String> newWords) {

        int points = 0;

        for (int i = 0; i < newWords.size(); i++) {
            if (newWords.get(i).length() > 1) {
                points = points + bag.getValue((String) newWords.get(i));
            }
        }

        return points;
    }

    private int getPointsForLastMove() {
        return getPointsLastMove();
    }

    //Which letters are new on the board?
    private ArrayList<CoordinatePair> pairsOfNewLetterCoordinates(char[][] previousBoardState, char[][] wholeBoard) {

        ArrayList <CoordinatePair> listOfCoordinatePairs = new ArrayList<CoordinatePair>(0);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (previousBoardState[i][j] != wholeBoard[i][j]) {
                    CoordinatePair pair = new CoordinatePair(j,i);
                    listOfCoordinatePairs.add(pair);

                }
            }
        }

        return listOfCoordinatePairs;
    }

    private List<Character> getEnteredArray(ArrayList<CoordinatePair> listOfCoordinatePairs, char[][] wholeBoard) {

        ArrayList<Character> enteredArray = new ArrayList<>();

        for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
            enteredArray.add(wholeBoard[listOfCoordinatePairs.get(i).y][listOfCoordinatePairs.get(i).x]);
        }

        return enteredArray;
    }

    //Find the most left tile
    private CoordinatePair getLeftLetterCoordinates(List<CoordinatePair> listOfCoordinatePairs, char[][] wholeBoard) {
        int i = 1;
        CoordinatePair horizontalWordsLeftCoordinate = new CoordinatePair((listOfCoordinatePairs.get(0).x), listOfCoordinatePairs.get(0).y);

        while ((listOfCoordinatePairs.get(0).y - 1) > 0 && wholeBoard[listOfCoordinatePairs.get(0).y - i][listOfCoordinatePairs.get(0).x] != ' ') {
            horizontalWordsLeftCoordinate = new CoordinatePair(listOfCoordinatePairs.get(0).x, listOfCoordinatePairs.get(0).y - i);
            i++;
        }
        return horizontalWordsLeftCoordinate;
    }

    //Find the most right tile
    private CoordinatePair getRightLetterCoordinates(List<CoordinatePair> listOfCoordinatePairs, char[][] wholeBoard) {
        int i = 0;
        CoordinatePair lastLetterCoordinates = listOfCoordinatePairs.get(listOfCoordinatePairs.size() - 1);
        CoordinatePair horizontalWordsRightCoordinate = new CoordinatePair(lastLetterCoordinates.x, lastLetterCoordinates.y);

        while ((lastLetterCoordinates.y + i) < 15 && wholeBoard[lastLetterCoordinates.y + i][lastLetterCoordinates.x] != ' ') {
            horizontalWordsRightCoordinate = new CoordinatePair(lastLetterCoordinates.x, lastLetterCoordinates.y + i);
            i++;
        }

        return horizontalWordsRightCoordinate;
    }

    //Find the uppermost tile
    private CoordinatePair getUpperLetterCoordinates(List<CoordinatePair> listOfCoordinatePairs, char[][] wholeBoard) {
        int i = 1;
        CoordinatePair verticalWordsUpperCoordinate = new CoordinatePair(listOfCoordinatePairs.get(0).x, listOfCoordinatePairs.get(0).y);

        while ((listOfCoordinatePairs.get(0).x - i) > 0 && wholeBoard[listOfCoordinatePairs.get(0).y][listOfCoordinatePairs.get(0).x - i] != ' ') {
            verticalWordsUpperCoordinate = new CoordinatePair((listOfCoordinatePairs.get(0).x - i), (listOfCoordinatePairs.get(0).y));
            i++;
        }

        return verticalWordsUpperCoordinate;
    }

    //Find the lowest tile
    private CoordinatePair getLowerLetterCoordinates(List<CoordinatePair> listOfCoordinatePairs, char[][] wholeBoard) {

        int i = 0;

        CoordinatePair lastLetterCoordinates = listOfCoordinatePairs.get(listOfCoordinatePairs.size() - 1);
        CoordinatePair verticalWordsLowerCoordinate = new CoordinatePair(lastLetterCoordinates.x, lastLetterCoordinates.y);

        while ((lastLetterCoordinates.x + i) < 15 && wholeBoard[lastLetterCoordinates.y][lastLetterCoordinates.x + i] != ' ') {
            verticalWordsLowerCoordinate = new CoordinatePair(lastLetterCoordinates.x + i, lastLetterCoordinates.y);
            i++;
        }

        return verticalWordsLowerCoordinate;
    }

    //Create the string, when the first and last coordinates of the word are known
    private String getWordToCheck(List<CoordinatePair> firstLastLetterCoordinates, char[][] wholeBoard) {
        String wordToLookUp = "";

        if (firstLastLetterCoordinates.size() == 1 || ((firstLastLetterCoordinates.get(0).x == firstLastLetterCoordinates.get(1).x))) {
            for (int i = 0; i < firstLastLetterCoordinates.get(1).y - firstLastLetterCoordinates.get(0).y + 1; i++) {
                wordToLookUp += wholeBoard[firstLastLetterCoordinates.get(0).y + i][firstLastLetterCoordinates.get(0).x];
            }
        } else {
            for (int i = 0; i < firstLastLetterCoordinates.get(1).x - firstLastLetterCoordinates.get(0).x + 1; i++) {
                wordToLookUp += wholeBoard[firstLastLetterCoordinates.get(0).y][firstLastLetterCoordinates.get(0).x + i];
            }
        }

        wordToLookUp = wordToLookUp.toLowerCase();

        return wordToLookUp;
    }

    //Checks whether the input is in one line
    private boolean isInOneLine(ArrayList<CoordinatePair> listOfCoordinatePairs) {
        for (int i = 0; i < listOfCoordinatePairs.size(); i++) {
            if ((listOfCoordinatePairs.get(0).y == listOfCoordinatePairs.get(i).y) ||
                    (listOfCoordinatePairs.get(0).x == listOfCoordinatePairs.get(i).x) ) {
            } else {
                return false;
            }
        }
        return true;
    }

    //Checks if user used only letters from their rack
    private boolean isInputComprisedOfRack(List enteredArray) {

        List playersRack = player.getPlayersRack();

        if (!playersRack.containsAll(enteredArray)) {
            return false;
        }

        return true;
    }

    //Checks whether the input is adjacent to already set letters
    private boolean isAdjacent(char[][] previousBoardState, ArrayList<CoordinatePair> listOfCoordinatePairs) {

        if (!board.isBoardEmpty()) {
            for (int i = 0; i < listOfCoordinatePairs.size(); i++) {

                CoordinatePair coordinatePair = listOfCoordinatePairs.get(i);
                System.out.println(coordinatePair.x + "," + coordinatePair.y);

                if (coordinatePair.y == 0 && coordinatePair.x != 0 && coordinatePair.x != 14) {
                    if (!(isLetterAbove(previousBoardState, coordinatePair) ||
                        isLetterOnTheRight(previousBoardState, coordinatePair) ||
                        isLetterBelow(previousBoardState, coordinatePair) == true)) {
                        return false;
                    }
                }
                if (coordinatePair.x == 0 && coordinatePair.y != 0 && coordinatePair.x != 14) {
                    if (!(isLetterOnTheLeft(previousBoardState, coordinatePair) ||
                        isLetterBelow(previousBoardState, coordinatePair) ||
                        isLetterOnTheRight(previousBoardState, coordinatePair) == true)) {
                        return false;
                    }
                }
                if (coordinatePair.y == 14 && coordinatePair.x != 0 && coordinatePair.x != 14) {
                    if (!(isLetterAbove(previousBoardState, coordinatePair) ||
                        isLetterBelow(previousBoardState, coordinatePair) ||
                        isLetterOnTheLeft(previousBoardState, coordinatePair) == true)) {
                        return false;
                    }
                }
                if (coordinatePair.x == 14 && coordinatePair.y != 0 && coordinatePair.y != 14) {
                    if (!(isLetterOnTheLeft(previousBoardState, coordinatePair) ||
                        isLetterAbove(previousBoardState, coordinatePair) ||
                        isLetterOnTheRight(previousBoardState, coordinatePair) == true)) {
                        return false;
                    }
                }
                if (coordinatePair.x == 0 && coordinatePair.y == 0) {
                    if (!(isLetterOnTheRight(previousBoardState, coordinatePair) ||
                        isLetterBelow(previousBoardState, coordinatePair) == true)) {
                        return false;
                    }
                }
                if (coordinatePair.x == 0 && coordinatePair.y == 14) {
                    if (!(isLetterOnTheLeft(previousBoardState, coordinatePair) ||
                        isLetterBelow(previousBoardState, coordinatePair) == true)) {
                        return false;
                    }
                }
                if (coordinatePair.x == 14 && coordinatePair.y == 14) {
                    if ((!isLetterOnTheLeft(previousBoardState, coordinatePair) ||
                        isLetterAbove(previousBoardState, coordinatePair) == true)) {
                        return false;
                    }
                }
                if (coordinatePair.x == 14 && coordinatePair.y == 0) {
                    if (!(isLetterAbove(previousBoardState, coordinatePair) ||
                        isLetterOnTheRight(previousBoardState, coordinatePair) == true)) {
                        return false;
                    }
                }
                //Letter is somewhere in the middle
                if (coordinatePair.x > 0 && coordinatePair.x < 14 && coordinatePair.y > 0 && coordinatePair.y < 14) {

                    if (!((isLetterAbove(previousBoardState, coordinatePair) ||
                        isLetterBelow(previousBoardState, coordinatePair) ||
                        isLetterOnTheLeft(previousBoardState, coordinatePair) ||
                        isLetterOnTheRight(previousBoardState, coordinatePair)) == true)) {
                        System.out.println("mida returnid?");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Is there already an adjacent letter to the left of one of the entered letters?
    private boolean isLetterOnTheLeft(char[][] previousBoardState, CoordinatePair coordinatePair) {

        if (previousBoardState[coordinatePair.y - 1][coordinatePair.x] == ' ') {
            System.out.println("vasak ruut: x: " + (coordinatePair.x) + "y: " + (coordinatePair.y - 1));
            System.out.println("vasakul ei ole midagi");
            return false;
        }
        return true;
    }

    //Is there already an adjacent letter to the right of one of the entered letters?
    private boolean isLetterOnTheRight(char[][] previousBoardState, CoordinatePair coordinatePair) {

        if (previousBoardState[coordinatePair.y + 1][coordinatePair.x] == ' ') {
            System.out.println("parem ruut: x: " + (coordinatePair.x) + "y: " + (coordinatePair.y + 1));
            System.out.println("paremal ei ole midagi");
            return false;
        }
        return true;
    }

    //Is there already an adjacent letter above one of the entered letters?
    private boolean isLetterAbove(char[][] previousBoardState, CoordinatePair coordinatePair) {

        if (previousBoardState[coordinatePair.y][coordinatePair.x - 1] == ' ') {
            System.out.println("ülemine ruut: x: " + (coordinatePair.x - 1) + "y: " + (coordinatePair.y));
            System.out.println("üleval ei ole midagi");
            return false;
        }
        return true;
    }

    //Is there already an adjacent letter below one of the entered letters?
    private boolean isLetterBelow(char[][] previousBoardState, CoordinatePair coordinatePair) {

        if (previousBoardState[coordinatePair.y][coordinatePair.x + 1] == ' ') {
            System.out.println("alumine ruut: x: " + (coordinatePair.x + 1) + "y: " + (coordinatePair.y));
            System.out.println("all ei ole midagi");
            return false;
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

        } else {
            return false;
        };

        return true;
    }

    //Check whether word is in the dictionary (unless it consists of only one letter)
    private boolean checkIfWordIsARealWord(String wordToCheck) {

        if (wordToCheck.length() == 1) {
            return true;
        }
        if (checkFromDict(wordToCheck) == true) {
            return true;
        }

        return false;
    }

}