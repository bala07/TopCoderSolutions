// DONE

public class SumOfSelectedCells
{
    int totalRows = 0, totalColumns = 0;
    int[][] board = null;

    public String hypothesis(String[] table)
    {
        String correctHypothesis = "CORRECT";
        String wrongHypothesis = "INCORRECT";

        totalRows = table.length;
        totalColumns = table[0].split(" ").length;
        board = new int[totalRows][totalColumns];

        populateBoard(table);

        if(totalRows == totalColumns)
        {
            for(int row=0; row<totalRows; ++row)
            {
                int smallestInRow = board[row][0];
                for(int col=1; col<totalColumns; ++col)
                {
                    smallestInRow = Math.min(smallestInRow, board[row][col]);
                }

                for(int col=0; col<totalColumns; ++col)
                {
                    board[row][col] -= smallestInRow;
                }
            }

            for(int row=1; row<totalRows; ++row)
            {
                for(int col=0; col<totalColumns; ++col)
                {
                    if(board[row][col] != board[row-1][col]) {
                        return wrongHypothesis;
                    }
                }
            }

            return correctHypothesis;
        }
        else if(totalRows < totalColumns)
        {
            for(int row=0; row<totalRows; ++row)
            {
                for(int col=1; col<totalColumns; ++col)
                {
                    if(board[row][col] != board[row][col-1])
                    {
                        return wrongHypothesis;
                    }
                }
            }

            return correctHypothesis;
        }
        else
        {
            for(int col=0; col<totalColumns; ++col)
            {
                for(int row=1; row<totalRows; ++row)
                {
                    if(board[row][col] != board[row-1][col])
                    {
                        return wrongHypothesis;
                    }
                }
            }

            return correctHypothesis;
        }

    }

    private void populateBoard(String[] table)
    {
        int rowIndex = 0;
        for(String row : table) {
            String[] tokens = row.split(" ");
            for(int columnIndex=0; columnIndex<totalColumns; ++columnIndex) {
                board[rowIndex][columnIndex] = Integer.parseInt(tokens[columnIndex]);
            }

            rowIndex++;
        }
    }
}
