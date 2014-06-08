/**
 * Created by Balasubramanian on 6/8/14.
 */
public class ChangingSounds
{
    final int MAX_LEVEL = 1000;
    final int MAX_SONGS = 50;
    final int SENTINEL_VALUE = Integer.MAX_VALUE;

    int[][] dp = new int[MAX_LEVEL+10][MAX_SONGS+10];

    public int maxFinal(int[] changeIntervals, int beginLevel, int maxLevel)
    {
        for(int i=0; i<=MAX_LEVEL; ++i)
        {
            for(int j=0; j<=MAX_SONGS; ++j)
            {
                dp[i][j] = SENTINEL_VALUE;
            }
        }

        int maxSound = getMaxFinalSound(beginLevel, 0, maxLevel, changeIntervals);

        return maxSound;
    }

    private int getMaxFinalSound(int curLevel, int idx, int maxLevel, int[] changeIntervals)
    {
        if(curLevel < 0 || curLevel > maxLevel)
        {
            return -1;
        }

        if(idx == changeIntervals.length)
        {
            return curLevel;
        }

        if(dp[curLevel][idx] != SENTINEL_VALUE)
        {
            return dp[curLevel][idx];
        }

        int maxSound1 = getMaxFinalSound(curLevel + changeIntervals[idx], idx + 1, maxLevel, changeIntervals);
        int maxSound2 = getMaxFinalSound(curLevel - changeIntervals[idx], idx + 1, maxLevel, changeIntervals);

        dp[curLevel][idx] = Math.max(maxSound1, maxSound2);

        return dp[curLevel][idx];
    }

    public static void main(String[] args)
    {
        ChangingSounds tester = new ChangingSounds();

        System.out.println(tester.maxFinal(new int[]{74,39,127,95,63,140,99,96,154,18,137,162,14,88}, 40, 243));
    }
}
