package ScrabbleGame;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public List<Character> playersRack = new ArrayList<Character>();

    //Create a rack for player

    public void addLettersToRack(List generatedRack) {
        playersRack.addAll(generatedRack);
    }

    //Print player's rack
    public void printRack() {
        System.out.println(playersRack);
    }

    //Fill the rack
    /* public List getRackFull() {

        return fullRack;
    }*/

    /*Calculate, how many more tiles are needed to get from the bag to end up with seven tiles on hand.*/

    public int getAmountOfTilesToAdd() {
        int j = 7 - playersRack.size();
        System.out.println(j);
        return j;
    }

    //Remove the tiles the user used to compose their word.

    public List getTilesRemovedFromRack(String usersInput) {
        for (int i = 0; i < usersInput.length(); i++) {
            char toBeRemovedFromRack = usersInput.charAt(i);
            int indexOfRemovableChar = playersRack.indexOf(toBeRemovedFromRack);
            playersRack.remove(indexOfRemovableChar);
        }
        return playersRack;
    }


}
