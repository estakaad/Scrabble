package ScrabbleGame;

import com.sun.deploy.util.ArrayUtil;

import java.util.*;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {

    private List<Character> tilesInBag = new ArrayList<Character>();
    HashMap<Character, Integer> values = new HashMap<Character, Integer>();

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


        values.put('a', 1);
        values.put('b', 4);
        values.put('d', 2);
        values.put('e', 1);
        values.put('f', 4);
        values.put('g', 2);
        values.put('h', 4);
        values.put('i', 1);
        values.put('j', 4);
        values.put('k', 1);
        values.put('l', 1);
        values.put('m', 4);
        values.put('n', 2);
        values.put('o', 1);
        values.put('p', 4);
        values.put('r', 2);
        values.put('s', 1);
        values.put('š', 10);
        values.put('z', 10);
        values.put('ž', 10);
        values.put('t', 1);
        values.put('u', 1);
        values.put('v', 3);
        values.put('õ', 4);
        values.put('ä', 5);
        values.put('ö', 5);
        values.put('ü', 6);
    }

    //Get value for letters
    public void getValue(String wordToValue) {

        int sumValue = 0;
        for (int i = 0; i < wordToValue.length(); i++) {
            Character letter = wordToValue.charAt(i);
            char letterLowercase = Character.toLowerCase(letter);
            Integer valueOfLetter = values.get(letterLowercase);
            sumValue += valueOfLetter;
        }
        System.out.println("Need tähed annavad kokku " + sumValue + " punkti.");
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

