package scrabblegame;

public class Board {

    char[][] board = new char[15][15];

    //DictionaryDatabase db = new DictionaryDatabase();

    //Generate the game board. The board is a square with a 15*15 grid of cells. Each cell accommodates one letter.
    public Board() {

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = ' ';
            }
        }
    }

    //Check if board is empty, return if it is
    public boolean isBoardEmpty() {

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j] != ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] newBoard) {
        board = newBoard;
    }

    //Print board
    public void showBoard() {

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}