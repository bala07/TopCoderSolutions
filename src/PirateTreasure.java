/**
 * Created by Balasubramanian on 6/30/14.
 */
public class PirateTreasure
{
    public double getDistance(int[] steps, String[] directions)
    {
        double x = 0, y = 0;

        for(int i=0; i<steps.length; ++i)
        {
            switch(directions[i])
            {
                case "NORTH":
                    y += steps[i];
                    break;
                case "SOUTH":
                    y -= steps[i];
                    break;
                case "EAST":
                    x += steps[i];
                    break;
                case "WEST":
                    x -= steps[i];
                    break;
                case "NORTHEAST":
                    x += steps[i] / Math.sqrt(2);
                    y += steps[i] / Math.sqrt(2);
                    break;
                case "NORTHWEST":
                    x -= steps[i] / Math.sqrt(2);
                    y += steps[i] / Math.sqrt(2);
                    break;
                case "SOUTHEAST":
                    x += steps[i] / Math.sqrt(2);
                    y -= steps[i] / Math.sqrt(2);
                    break;
                case "SOUTHWEST":
                    x -= steps[i] / Math.sqrt(2);
                    y -= steps[i] / Math.sqrt(2);
                    break;
            }
        }

        return Math.sqrt(x * x + y * y);
    }
}
