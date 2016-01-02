package scrabblegame;

//The following code is by Skip Head (http://stackoverflow.com/questions/10234487/storing-number-pairs-in-java)

public class CoordinatePairs {

    Integer x, y;
    CoordinatePairs(Integer x, Integer y) {

        this.x = x;
        this.y = y;

    }

    public String toString() {
        return "x:"+ x +", y:" + y;
    }

}
