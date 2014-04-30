// DONE
public class IncreasingSubsequences {
    public long count(int[] a) {
        long[] dp = new long[a.length];

        for(int i=0; i<a.length; ++i) {
            if(!hasSmallerElementOnLeft(a,i)) {
                dp[i] = 1;
            }

            for(int j=i-1; j>=0; --j) {
                boolean flag = true;
                if(a[j] < a[i]) {
                    for(int k=j+1; k<i; ++k) {
                        if(a[j] < a[k] && a[k] < a[i]) {
                            flag =  false;
                            break;
                        }
                    }
                    if(flag) {
                        dp[i] += dp[j];
                    }
                }
            }
        }

        long result = 0;

        for(int i=0; i<a.length; ++i) {
            if(!hasLargerElementOnRight(a,i)) {
                result += dp[i];
            }
        }

        return result;
    }

    private boolean hasLargerElementOnRight(int[] a, int idx) {
        for(int i=idx+1; i<a.length; ++i) {
            if(a[i] > a[idx]) {
                return true;
            }
        }

        return false;
    }

    private boolean hasSmallerElementOnLeft(int[] a, int idx) {
        for(int i=0; i<idx; ++i) {
            if(a[i] < a[idx]) {
                return true;
            }
        }

        return false;
    }
}
