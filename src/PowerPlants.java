import java.util.Arrays;

/**
 * Created by Balasubramanian on 5/28/14.
 */
public class PowerPlants
{
    final int MAX_PLANTS = 16;
    int totalPlants = 0;
    int[] dp = new int[(1<<MAX_PLANTS) + 10];
    int[][] costMatrix = null;

    public int minCost(String[] connectionCost, String plantList, int numPlants)
    {
        totalPlants = plantList.length();

        populateCostMatrix(connectionCost);

        Arrays.fill(dp, -1);

        int bitMask = getInitialBitMask(plantList);

        int minCostOfLightingPlants =  findMinCost(bitMask, numPlants - countPlantsAlreadyLit(plantList));

        return minCostOfLightingPlants;
    }

    private int findMinCost(int bitMask, int plantsToBeLit)
    {
        if(plantsToBeLit <= 0)
        {
            return 0;
        }

        if(dp[bitMask] != -1)
        {
            return dp[bitMask];
        }

        dp[bitMask] = Integer.MAX_VALUE;
        for(int idx=0; idx<totalPlants; ++idx)
        {
            if(getBit(bitMask, idx) == 0)
            {
                int minCostToLight = Integer.MAX_VALUE;
                for(int i=0; i<totalPlants; ++i)
                {
                    if( idx != i && getBit(bitMask, i) != 0)
                    {
                        minCostToLight = Math.min(minCostToLight, costMatrix[i][idx]);
                    }
                }

                dp[bitMask] = Math.min(dp[bitMask], findMinCost(setBit(bitMask, idx), plantsToBeLit - 1) + minCostToLight);
                bitMask = clearBit(bitMask, idx);
            }
        }

        return dp[bitMask];
    }

    private void populateCostMatrix(String[] connectionCost)
    {
        costMatrix = new int[connectionCost.length][connectionCost.length];

        for(int i=0; i<connectionCost.length; ++i)
        {
            for(int j=0; j<connectionCost.length; ++j)
            {
                char cost = connectionCost[i].charAt(j);

                if(cost >= '0' && cost <= '9')
                {
                    costMatrix[i][j] = cost - '0';
                }
                else
                {
                    costMatrix[i][j] = (cost - 'A') + 10;
                }
            }
        }
    }

    private int getInitialBitMask(String plantList)
    {
        int bitMask = 0;
        for(int i=0; i<plantList.length(); ++i)
        {
            if(plantList.charAt(i) == 'Y')
            {
                bitMask = setBit(bitMask, i);
            }
        }

        return bitMask;
    }

    private int getBit(int mask, int index)
    {
        return (mask & (1 << index));
    }

    private int setBit(int mask, int index)
    {
        return (mask | (1 << index));
    }

    private int clearBit(int mask, int index)
    {
        return (mask & ~(1 << index ));
    }

    private int countPlantsAlreadyLit(String plantList)
    {
        int count = 0;
        for(int i=0; i < plantList.length(); ++i)
        {
            if(plantList.charAt(i) == 'Y')
            {
                ++count;
            }
        }

        return count;
    }

    public static void main(String[] args)
    {
        PowerPlants tester = new PowerPlants();

        int minCost = tester.minCost(new String[] { "024",
                "203",
                "430"}, "YNN", 3);

        System.out.println(minCost);
    }
}
