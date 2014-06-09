/**
 * Created by Balasubramanian on 6/10/14.
 */
public class WhiteCells
{
    public int countOccupied(String[] board)
    {
        int requiredMod = 0;
        int result = 0;
        int idx = 0;

        for(int i=0; i<board.length; ++i)
        {
            for(int j=0; j<board.length; ++j)
            {
                if(idx % 2 == requiredMod)
                {
                    if(board[i].charAt(j) == 'F')
                    {
                        ++result;
                    }
                }

                ++idx;
            }

            requiredMod = 1 - requiredMod;
        }

        return result;
    }
}
