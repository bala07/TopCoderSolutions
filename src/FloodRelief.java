import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by balasubn on 9/25/14.
 */
public class FloodRelief
{
    Cell[] cells;
    boolean[][] visited;
    String[] height;

    public int minimumPumps(String[] height)
    {
        this.height = height;
        cells = new Cell[height.length * height[0].length()];
        visited = new boolean[height.length][height[0].length()];

        int idx = 0;
        for (int i = 0; i < height.length; ++i)
        {
            for (int j = 0; j < height[0].length(); ++j)
            {
                cells[idx++] = new Cell(i, j);
            }
        }

        Arrays.sort(cells);

        int pumps = 0;
        for (int i = 0; i < cells.length; ++i)
        {
            if (!visited[cells[i].x][cells[i].y])
            {
                doBfs(cells[i].x, cells[i].y);
                ++pumps;
            }
        }

        return pumps;
    }

    private void doBfs(int x, int y)
    {
        Cell startingCell = new Cell(x, y);
        visited[x][y] = true;

        Queue<Cell> queue = new LinkedList<>();
        queue.add(startingCell);

        while (!queue.isEmpty())
        {
            Cell currentCell = queue.poll();
            int curX = currentCell.x;
            int curY = currentCell.y;

            if (withinBounds(curX - 1, curY) && !visited[curX - 1][curY] && height[curX].charAt(curY) <= height[curX - 1].charAt(curY))
            {
                visited[curX - 1][curY] = true;
                queue.offer(new Cell(curX - 1, curY));

            }

            if (withinBounds(curX + 1, curY) && !visited[curX + 1][curY] && height[curX].charAt(curY) <= height[curX + 1].charAt(curY))
            {
                visited[curX + 1][curY] = true;
                queue.offer(new Cell(curX + 1, curY));
            }

            if (withinBounds(curX, curY - 1) && !visited[curX][curY - 1] && height[curX].charAt(curY) <= height[curX].charAt(curY - 1))
            {
                visited[curX][curY - 1] = true;
                queue.offer(new Cell(curX, curY - 1));
            }

            if (withinBounds(curX, curY + 1) && !visited[curX][curY + 1] && height[curX].charAt(curY) <= height[curX].charAt(curY + 1))
            {
                visited[curX][curY + 1] = true;
                queue.offer(new Cell(curX, curY + 1));
            }
        }
    }

    private boolean withinBounds(int x, int y)
    {
        return (x >= 0 && x < height.length && y >= 0 && y < height[0].length());
    }

    class Cell implements Comparable<Cell>
    {
        int x, y;

        public Cell(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Cell cell)
        {
            if (height[x].charAt(y) < height[cell.x].charAt(cell.y))
            {
                return -1;
            }

            if (height[x].charAt(y) == height[cell.x].charAt(cell.y))
            {
                return 0;
            }

            return 1;
        }
    }
}
