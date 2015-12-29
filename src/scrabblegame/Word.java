package scrabblegame;

import java.util.List;
import java.util.stream.Collectors;


public class Word {

    String word;

    public void setWord(String inputWord) {
        word = inputWord;
    }

    public String getWord() {
        return word;
    }

    public int getWordLength(String word) { return word.length(); }

    public List setWordAsCharArray(String inputWord) {
        List<Character> wordArray = inputWord.chars().mapToObj(e->(char)e).collect(Collectors.toList());
        return wordArray;
    }
/*
    public String setUpperCase() {
        String upperCaseWord = word.toUpperCase();
        return upperCaseWord;
    }

    public List getCoordinates() {

        return wordsFirstCoordinates;
    }

    public String getDirection() {
        return wordDirection;
    }*/

}
