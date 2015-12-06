package ScrabbleGame;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {

    List<Character> tilesInBag = new ArrayList<Character>();
    char randomChar;

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

    //Get 7 random letter tiles from the bag
    public char getLetters() {
        for (int i = 0; i < 7; i++) {
            char randomChar = tilesInBag.get(ThreadLocalRandom.current().nextInt(tilesInBag.size()));
            System.out.println(randomChar);
            tilesInBag.remove(randomChar);
        }
        return randomChar;
    }
}

