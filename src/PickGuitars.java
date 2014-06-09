/**
 * Created by Balasubramanian on 6/9/14.
 */
public class PickGuitars
{
    final int MAX_GUITARS = 55;
    int[][] dp = new int[MAX_GUITARS][MAX_GUITARS];

    public int maxValue(int[] guitarValues)
    {
        int result = Integer.MIN_VALUE;

        int totalValue = getSumOfArrayElements(guitarValues, 0, guitarValues.length - 1);

        for(int i=0; i<guitarValues.length; ++i)
        {
            int[] remGuitars = new int[guitarValues.length - 1];

            for(int j = (i+1) % guitarValues.length, k = 0; j != i; j = (j+1) % guitarValues.length, ++k)
            {
                remGuitars[k] = guitarValues[j];
            }

            for(int j=0; j<MAX_GUITARS; ++j)
            {
                for(int k=0; k<MAX_GUITARS; ++k)
                {
                    dp[j][k] = -1;
                }
            }


            result = Math.max(result, totalValue - getMax(remGuitars, 0, remGuitars.length - 1));
        }

        return result;
    }

    private int getMax(int[] guitars, int start, int end)
    {
        if(start > end)
        {
            return 0;
        }

        if(dp[start][end] != -1)
        {
            return dp[start][end];
        }

        int totalValue = getSumOfArrayElements(guitars, start, end);
        int maxValue = Integer.MIN_VALUE;

        for(int i=start; i<=end; ++i)
        {
            maxValue = Math.max(maxValue, totalValue - getMax(guitars, start, i-1) - getMax(guitars, i+1, end));
        }

        dp[start][end] = maxValue;

        return dp[start][end];
    }

    private int getSumOfArrayElements(int[] a, int start, int end)
    {
        int sum = 0;
        for(int idx = start; idx <= end; ++idx)
        {
            sum += a[idx];
        }

        return sum;
    }

    public static void main(String[] args)
    {
        PickGuitars tester = new PickGuitars();

        System.out.println(tester.maxValue(new int[]{2,1,4,1,2,1,8,1}));
    }
}
