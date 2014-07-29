/**
 * Created by Balasubramanian on 7/20/14.
 */
public class ChainOfRectangles
{

    public int getMaximalArea(int[] width, int[] height, String color)
    {
        int[] areas = new int[3];
        int prevArea = 0;

        for(int i = width.length - 1; i >= 0; --i)
        {
            int curArea = width[i] * height[i];
            areas[getColorIndex(color.charAt(i))] += curArea - prevArea;
            prevArea = curArea;
        }

        return Math.max(Math.max(areas[0], areas[1]), areas[2]);
    }

    private int getColorIndex(char color)
    {
        switch (color)
        {
            case 'R':
                return 0;
            case 'G':
                return 1;
            case 'B':
                return 2;
        }

        return -1;
    }
}
