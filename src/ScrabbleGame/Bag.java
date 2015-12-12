package ScrabbleGame;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {

    private List<Character> tilesInBag = new ArrayList<Character>();

    //Generate bag of tiles
    public Bag() {

        Collections.addAll(tilesInBag, 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
                                        'B', 'D', 'D', 'D', 'D', 'E', 'E', 'E', 'E', 'E',
                                        'E', 'E', 'E', 'E', 'F', 'G', 'G', 'H', 'H', 'I',
                                        'I', 'I', 'I', 'I', 'I', 'I', 'I', 'I', 'J', 'J',
                                        'K', 'K', 'K', 'K', 'K', 'L', 'L', 'L', 'L', 'L',
                                        'M', 'M', 'M', 'M', 'N', 'N', 'N', 'N', 'O', 'O',
                                        'O', 'O', 'O', 'P', 'P', 'R', 'R', 'S', 'S', 'S',
                                        'S', 'S', 'S', 'S', 'S', 'Š', 'Z', 'Ž', 'T', 'T',
                                        'T', 'T', 'T', 'T', 'T', 'U', 'U', 'U', 'U', 'U',
                                        'V', 'V', 'Õ', 'Õ', 'Ä', 'Ä', 'Ö', 'Ö', 'Ü', 'Ü');

    }

    //Get random letter tiles from the bag and generate an array of them.
    public List getLetters(int j) {
        List<Character> generatedRack = new ArrayList<Character>();

        for (int i = 0; i < j; i++) {
            int randomCharIndex = (int)(Math.random() * ( tilesInBag.size() - 0 ) + 0 );
            char randomChar = tilesInBag.get(randomCharIndex);
            generatedRack.add(i, randomChar);
            tilesInBag.remove(randomCharIndex);
        }
        return generatedRack; //Array of the chars player received from the bag

    }

}

