# Scrabble

One player Scrabble-like game. The list of allowed words is based on the Estonian descriptive dictionary (http://www.eki.ee/dict/ekss/index.cgi).
All rights to the dictionary belong to the Institute of the Estonian Language.

### Implemented rules
* In the beginning of every move, user's rack is filled to have seven random letter tiles on it. The amount of tiles to add depends on the amount of tiles user used for their last move.
* Tiles are picked from the bag randomly.
* User has to set tiles in one row or one column, from left to right or from up to down.
* Declinable words must be in singular nominative, conjugable words in supine.
* Word is a valid word if it exists in the Estonian descriptive dictionary.
* Every new word must be adjacent to an already existing word on the board.
* Every horizontally or vertically formed crossword on the board must be a valid word.
* User gets points for every new crossword that is formed after their move.

### Rules yet to be implemented
* Declinable words' plural nominatives should also be accepted.
* Player should be able to change their tiles instead of making a move.
* The board should have double and triple bonus squares.
* The first word set on the board must always cross the square in the middle of the board.
