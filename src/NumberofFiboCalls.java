// DONE
public class NumberofFiboCalls {

    entity[] dp;
    public int[] fiboCallsMade(int n) {

        if(n==0) {
            return new int[] { 1, 0 };
        }

        if(n==1) {
            return new int[] { 0, 1 };
        }

        dp = new entity[n+1];
        for(int i=0; i<=n; ++i) {
            dp[i] = new entity();
        }
        dp[0].zeroesPrinted = 1;
        dp[0].onesPrinted = 0;
        dp[1].zeroesPrinted = 0;
        dp[1].onesPrinted = 1;

        calculateFiboCalls(n);

        return new int[] { dp[n].zeroesPrinted, dp[n].onesPrinted };
    }

    private void calculateFiboCalls(int n) {
        if(n==0 || n==1 || dp[n].zeroesPrinted != -1) {
            return;
        }

        dp[n].zeroesPrinted = dp[n].onesPrinted = 0;

        calculateFiboCalls(n-2);
        calculateFiboCalls(n-1);

        dp[n].zeroesPrinted = dp[n-2].zeroesPrinted + dp[n-1].zeroesPrinted;
        dp[n].onesPrinted = dp[n-2].onesPrinted + dp[n-1].onesPrinted;
    }

    public class entity {
        int zeroesPrinted, onesPrinted;
        entity() {
            zeroesPrinted = onesPrinted = -1;
        }
    }
}