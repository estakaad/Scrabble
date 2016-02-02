package scrabblegame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by esta on 02/02/16.
 */
public class GameTest {

    Game game;
    Board board;
    char[][] newBoard;
    List<Character> testRack;


    @Before
    public void createGame() {
        game = new Game();
        board = new Board();
        newBoard = board.getBoard();
        testRack = new ArrayList<Character>();

        String currentRack = String.valueOf(game.player.getPlayersRack().stream().map(Object::toString).collect(Collectors.joining("")));

        game.player.getTilesRemovedFromRack(currentRack);
    }

    @Test
    public void wordInUpperLeftCornerVertically() throws Exception {

        testRack.add('õ');
        testRack.add('h');
        testRack.add('u');
        testRack.add('p');
        testRack.add('a');
        testRack.add('l');
        testRack.add('l');

        game.player.addLettersToRack(testRack);

        newBoard[0][0] = 'a';
        newBoard[0][1] = 'l';
        newBoard[0][2] = 'l';

        assertTrue(game.makeMoveOnBoard(newBoard));

    }

    @Test
    public void wordInlowerRightCornerHorizontally() throws Exception {

        testRack.add('õ');
        testRack.add('h');
        testRack.add('u');
        testRack.add('p');
        testRack.add('a');
        testRack.add('l');
        testRack.add('l');

        game.player.addLettersToRack(testRack);

        newBoard[12][14] = 'a';
        newBoard[13][14] = 'l';
        newBoard[14][14] = 'l';

        assertTrue(game.makeMoveOnBoard(newBoard));

    }

    @Test
    public void wordInOneRow() throws Exception {

        testRack.add('õ');
        testRack.add('h');
        testRack.add('u');
        testRack.add('p');
        testRack.add('a');
        testRack.add('l');
        testRack.add('l');

        game.player.addLettersToRack(testRack);

        newBoard[5][7] = 'a';
        newBoard[6][8] = 'l';
        newBoard[7][7] = 'l';

        assertFalse(game.makeMoveOnBoard(newBoard));

    }

    @Test
    public void wordInOneColumn() throws Exception {

        testRack.add('õ');
        testRack.add('h');
        testRack.add('u');
        testRack.add('p');
        testRack.add('a');
        testRack.add('l');
        testRack.add('l');

        game.player.addLettersToRack(testRack);

        newBoard[7][5] = 'a';
        newBoard[8][6] = 'l';
        newBoard[7][7] = 'l';

        assertFalse(game.makeMoveOnBoard(newBoard));

    }

    @Test
    public void wordFromRack() throws Exception {

        testRack.add('õ');
        testRack.add('h');
        testRack.add('u');
        testRack.add('p');
        testRack.add('a');
        testRack.add('l');
        testRack.add('l');

        game.player.addLettersToRack(testRack);

        newBoard[7][5] = 'õ';
        newBoard[7][6] = 'h';
        newBoard[7][7] = 'k';

        assertFalse(game.makeMoveOnBoard(newBoard));

    }

    @Test
    public void setAtLeastTwoLetters() throws Exception {

        testRack.add('õ');
        testRack.add('h');
        testRack.add('u');
        testRack.add('p');
        testRack.add('a');
        testRack.add('l');
        testRack.add('l');

        game.player.addLettersToRack(testRack);

        newBoard[7][4] = 'a';

        assertFalse(game.makeMoveOnBoard(newBoard));

    }

    @Test
    public void checkFromDictionary() throws Exception {

        testRack.add('õ');
        testRack.add('h');
        testRack.add('u');
        testRack.add('p');
        testRack.add('a');
        testRack.add('l');
        testRack.add('l');

        game.player.addLettersToRack(testRack);

        newBoard[7][5] = 'a';
        newBoard[7][6] = 'l';
        newBoard[7][7] = 'h';

        assertFalse(game.makeMoveOnBoard(newBoard));

    }

    @Test
    public void checkForSpacesInBetween() throws Exception {

        testRack.add('õ');
        testRack.add('h');
        testRack.add('u');
        testRack.add('p');
        testRack.add('a');
        testRack.add('l');
        testRack.add('l');

        game.player.addLettersToRack(testRack);

        newBoard[7][5] = 'a';
        newBoard[7][7] = 'l';
        newBoard[7][8] = 'l';

        assertFalse(game.makeMoveOnBoard(newBoard));

    }

}