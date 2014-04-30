// DONE

public class BoggleScore {

    final long MOD = (long)1e13;
    long[][][] dp;
    final int GRID_DIMENSION = 4;
    final int MAX_WORD_LENGTH = 50;
    String[] words;
    String[] grid;

    public long bestScore(String[] grid, String[] words) {
        this.grid = grid;
        this.words = words;
        dp = new long[GRID_DIMENSION][GRID_DIMENSION][MAX_WORD_LENGTH+1];
        long result = 0;


        for(int i=0; i<words.length; ++i) {
            initializeDpArray();
            char firstLetter = words[i].charAt(0);
            for(int j=0; j<GRID_DIMENSION; ++j) {
                for(int k=0; k<GRID_DIMENSION; ++k) {
                    if(grid[j].charAt(k) == firstLetter) {
                        result = (result + getOccurencesOfWord(i, 1, j, k)) % MOD;
                    }
                }
            }
        }

        return result%MOD;
    }

    private long getOccurencesOfWord(int currentWordIdx, int lettersMatched, int row, int col) {
        if(lettersMatched == words[currentWordIdx].length()) {
            return lettersMatched*lettersMatched;
        }

        if(dp[row][col][lettersMatched-1] != -1) {
            return dp[row][col][lettersMatched-1];
        }

        dp[row][col][lettersMatched-1] = 0;
        char letterToMatch = words[currentWordIdx].charAt(lettersMatched);

        for(int i=-1; i<=1; ++i) {
            for(int j=-1; j<=1; ++j) {
                if(i==0 && j==0) {
                    continue;
                }
                if(row+i>=0 && row+i<GRID_DIMENSION && col+j>=0 && col+j<GRID_DIMENSION) {
                    if(grid[row+i].charAt(col+j) == letterToMatch) {
                        dp[row][col][lettersMatched-1] = (dp[row][col][lettersMatched-1] + getOccurencesOfWord(currentWordIdx, lettersMatched+1, row+i, col+j)%MOD)%MOD;
                    }
                }
            }
        }

        return dp[row][col][lettersMatched-1];
    }

    private void initializeDpArray() {
        for(int i=0; i<GRID_DIMENSION; ++i) {
            for(int j=0; j<GRID_DIMENSION; ++j) {
                for(int k=0; k<MAX_WORD_LENGTH; ++k) {
                    dp[i][j][k] = -1;
                }
            }
        }
    }
}
