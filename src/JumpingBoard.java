import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Balasubramanian on 7/16/14.
 */
public class JumpingBoard
{
    boolean[][] vis = null;
    int ROWS;
    int COLUMNS;
    int[][] memoizedValues = null;
    String[] board = null;

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int maxJumps(String[] board)
    {
        this.board = board;
        ROWS = board.length;
        COLUMNS = board[0].length();

        vis = new boolean[ROWS][COLUMNS];
        memoizedValues = new int[ROWS][COLUMNS];

        for (int i = 0; i < ROWS; ++i)
        {
            for (int j = 0; j < COLUMNS; ++j)
            {
                memoizedValues[i][j] = -1;
            }
        }

        int maxJumps = getMaxPossibleJumps(0, 0);
        return maxJumps == Integer.MAX_VALUE ? -1 : maxJumps;
    }

    public int getMaxPossibleJumps(int row, int col)
    {
        if (outOfBounds(row, col) || board[row].charAt(col) == 'H')
        {
            return 0;
        }

        if (vis[row][col])
        {
            return Integer.MAX_VALUE;
        }

        if (memoizedValues[row][col] != -1)
        {
            return memoizedValues[row][col];
        }

        vis[row][col] = true;
        int maxJumps = 1;
        int jumpLength = board[row].charAt(col) - '0';

        for (int i = 0; i < 4; ++i)
        {
            int newRow = row + jumpLength * dx[i];
            int newCol = col + jumpLength * dy[i];

            int curMaxJumps = getMaxPossibleJumps(newRow, newCol);
            if (curMaxJumps == Integer.MAX_VALUE)
            {
                return curMaxJumps;
            }

            maxJumps = Math.max(maxJumps, curMaxJumps + 1);
        }

        memoizedValues[row][col] = maxJumps;
        vis[row][col] = false;

        return maxJumps;
    }

    boolean outOfBounds(int row, int col)
    {
        return (row < 0 || row >= ROWS || col < 0 || col >= COLUMNS);
    }


}
