// DONE

import java.util.ArrayList;

/**
 * Created by balasubn on 2/10/14.
 */
public class UnsealTheSafe {

    int N = 0;
    long[][] dp = null;
    long countPasswords(int N) {
        this.N = N;
        dp = new long[10][N+1];
        initializeDpArray();

        long totalPasswords = 0;

        for(int i=0; i<=9; ++i) {
            totalPasswords += countPasswordCombinations(i, 0);
        }

        return totalPasswords;
    }

    private long countPasswordCombinations(int lastDigit, int idx) {
        if(idx == N-1) {
            return 1;
        }

        if(dp[lastDigit][idx] != -1) {
            return dp[lastDigit][idx];
        }

        dp[lastDigit][idx] = 0;
        ArrayList<Integer> neighbours = getNeighbours(lastDigit);

        for(int neighbour : neighbours) {
            dp[lastDigit][idx] += countPasswordCombinations(neighbour, idx+1);
        }

        return dp[lastDigit][idx];
    }

    private ArrayList<Integer> getNeighbours(int lastDigit) {
        ArrayList<Integer> neighbours = new ArrayList<Integer>();

        switch(lastDigit) {
            case 0 :
                neighbours.add(7);
                break;
            case 1:
                neighbours.add(2);
                neighbours.add(4);
                break;
            case 2:
                neighbours.add(1);
                neighbours.add(3);
                neighbours.add(5);
                break;
            case 3:
                neighbours.add(2);
                neighbours.add(6);
                break;
            case 4:
                neighbours.add(1);
                neighbours.add(5);
                neighbours.add(7);
                break;
            case 5:
                neighbours.add(2);
                neighbours.add(6);
                neighbours.add(8);
                neighbours.add(4);
                break;
            case 6:
                neighbours.add(3);
                neighbours.add(5);
                neighbours.add(9);
                break;
            case 7:
                neighbours.add(4);
                neighbours.add(8);
                neighbours.add(0);
                break;
            case 8:
                neighbours.add(5);
                neighbours.add(7);
                neighbours.add(9);
                break;
            case 9:
                neighbours.add(6);
                neighbours.add(8);
                break;
        }

        return neighbours;
    }

    private void initializeDpArray() {
        for(int i=0; i<10; ++i) {
            for(int j=0; j<=N; ++j) {
                dp[i][j] = -1;
            }
        }
    }


}
