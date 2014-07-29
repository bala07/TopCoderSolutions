/**
 * Created by Balasubramanian on 7/7/14.
 */
public class TurningLightOn
{
    public int minFlips(String[] box)
    {
        int[][] board = new int[box.length][box[0].length()];
        populateBoard(board, box);

        int clicks = 0;

        int row = board.length - 1;
        int col = board[0].length - 1;

        while(row >=0 && col >=0)
        {
            if(board[row][col] == 0)
            {
                toggleBoard(board, row, col);
                ++clicks;
            }

            for(int i=row-1; i>=0; --i)
            {
                if(board[i][col] == 0)
                {
                    toggleBoard(board, i, col);
                    ++clicks;
                }
            }

            for(int j=col-1; j>=0; --j)
            {
                if(board[row][j] == 0)
                {
                    toggleBoard(board, row, j);
                    ++clicks;
                }
            }

            --row;
            --col;
        }

        return clicks;
    }

    private void populateBoard(int[][] board, String[] box)
    {
        for(int i=0; i<board.length; ++i)
        {
            for(int j=0; j<board[0].length; ++j)
            {
                board[i][j] = box[i].charAt(j) - '0';
            }
        }
    }

    private void toggleBoard(int[][] board, int rowEnd, int colEnd)
    {
        for(int i=0; i<=rowEnd; ++i)
        {
            for(int j=0; j<=colEnd; ++j)
            {
                board[i][j] = 1 - board[i][j];
            }
        }
    }
}
