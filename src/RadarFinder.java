/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 12/24/13
 * Time: 8:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class RadarFinder {
    public int possibleLocations(int x1, int y1, int r1, int x2, int y2, int r2) {
        long squareOfDistanceBetweenCentres = dist(x1,y1,x2,y2);
        long radiiSum = r1+r2;
        long radiiDiff = Math.abs(r1-r2);

        if(squareOfDistanceBetweenCentres == 0 && r1 == r2) {
            return -1;
        }

        if(squareOfDistanceBetweenCentres == radiiSum*radiiSum) {
            return 1;
        }

        if(squareOfDistanceBetweenCentres == radiiDiff*radiiDiff) {
            return 1;
        }

        if(squareOfDistanceBetweenCentres > radiiSum*radiiSum) {
            return 0;
        }

        if(squareOfDistanceBetweenCentres < radiiDiff*radiiDiff) {
            return 0;
        }

        if(radiiDiff*radiiDiff < squareOfDistanceBetweenCentres && squareOfDistanceBetweenCentres < radiiSum*radiiSum) {
            return 2;
        }

        throw new IllegalArgumentException();
    }

    private long dist(int x1, int y1, int x2, int y2) {
        long dx = (long)(x2-x1) * (long)(x2-x1);
        long dy = (long)(y2-y1) * (long)(y2-y1);

        return dx+dy;
    }
}
