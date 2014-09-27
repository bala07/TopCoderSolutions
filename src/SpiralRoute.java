/**
 * Created by balasubn on 9/15/14.
 */
public class SpiralRoute
{
    private int dx[] = {0, 0, 1, -1};
    private int dy[] = {1, -1, 0, 0};

    boolean[][] visited;

    public int[] thronePosition(int width, int length)
    {
        visited = new boolean[width][length];
        int cellsVisited = 0;
        int direction = 2;
        int x = 0, y = 0;
        int prevX = -1, prevY = -1;

        while (cellsVisited < width * length)
        {
            ++cellsVisited;
            visited[x][y] = true;
            int newX = x + dx[direction];
            int newY = y + dy[direction];

            if (!withinBounds(newX, newY, width, length) || visited[newX][newY])
            {
                direction = turnLeft(direction);
                newX = x + dx[direction];
                newY = y + dy[direction];
            }

            prevX = x;
            prevY = y;
            x = newX;
            y = newY;
        }

        return new int[]{prevX, prevY};
    }

    private int turnLeft(int direction)
    {
        switch (direction)
        {
            case 0:
                return 3;
            case 1:
                return 2;
            case 2:
                return 0;
            case 3:
                return 1;
            default:
                return -1;
        }
    }

    private boolean withinBounds(int x, int y, int width, int length)
    {
        return x >= 0 && x < width && y >= 0 && y < length;
    }
}
