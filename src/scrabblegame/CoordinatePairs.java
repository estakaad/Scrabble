package scrabblegame;

import javafx.util.Pair;

//The following code is by Skip Head (http://stackoverflow.com/questions/10234487/storing-number-pairs-in-java)

public class CoordinatePairs {

    Integer p1, p2;
    CoordinatePairs(Integer p1, Integer p2) {

        this.p1 = p1;
        this.p2 = p2;

    }

    public String toString() {
        return ""+ p1 +"," + p2;
    }

}
