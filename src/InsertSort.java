// DONE

import java.util.Arrays;

/**
 * Created by balasubn on 1/15/14.
 */
public class InsertSort {
    int calcMinimalCost(int[] a) {
        int[] dp = new int[a.length];
        int[] parent = new int[a.length];
        int maxSumEndingIdx = 0;
        int totalSum = arraySum(a);

        Arrays.fill(parent, -1);

        for(int i=0; i<a.length; ++i) {
            dp[i] = a[i];
            for(int j=i-1; j>=0; --j) {
                if(a[j] < a[i] && dp[j] + a[i] > dp[i]) {
                    dp[i] = dp[j] + a[i];
                    parent[i] = j;
                    if(dp[i] > dp[maxSumEndingIdx]) {
                        maxSumEndingIdx = i;
                    }
                }
            }
        }

        int sumOfMaxSubSeq = dp[maxSumEndingIdx];
        int parentIdx = parent[maxSumEndingIdx];
        while(parentIdx >= 0) {
            sumOfMaxSubSeq = dp[parentIdx];
            parentIdx = parent[parentIdx];
        }

        return totalSum - sumOfMaxSubSeq;
    }

    private int arraySum(int[] a) {
        int sum = 0;
        for(int n : a) {
            sum += n;
        }

        return sum;
    }
}
