/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 11/11/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class DegreesToRadians {

    final double PI = 3.141592653589793;

    public double convertToRadians(int degrees, int minutes, int seconds) {
        double radians = 0.0;
        radians += convertDegreeToRadians(degrees);
        radians += convertDegreeToRadians(minutes/60.0);
        radians += convertDegreeToRadians(seconds/3600.0);

        return radians;
    }

    private double convertDegreeToRadians(double degrees) {
        return (degrees/180) * PI;
    }
}

// DONE
