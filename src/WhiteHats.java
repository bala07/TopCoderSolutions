/**
 * Created by balasubn on 4/21/14.
 */
public class WhiteHats
{
    public int whiteNumber(int[] hatCounts)
    {
        for(int hatCount = 0; hatCount <= hatCounts.length; ++hatCount)
        {
            int prevHatNumberOccurrence = countOccurences(hatCounts, hatCount - 1);
            int curHatNumberOccurrence = countOccurences(hatCounts, hatCount);

            if(prevHatNumberOccurrence == hatCount && curHatNumberOccurrence == hatCounts.length - prevHatNumberOccurrence)
            {
                return hatCount;
            }
        }

        return -1;
    }

    private int countOccurences(int[] hatCounts, int number)
    {
        int count = 0;
        for(int i=0; i<hatCounts.length; ++i)
        {
            if(hatCounts[i] == number)
            {
                ++count;
            }
        }

        return count;
    }

    public static void main(String[] args)
    {
        WhiteHats whiteHats = new WhiteHats();

        System.out.println(whiteHats.whiteNumber(new int[]{2,1,1}));
        System.out.println(whiteHats.whiteNumber(new int[]{2,2,2}));
        System.out.println(whiteHats.whiteNumber(new int[]{0, 0}));
        System.out.println(whiteHats.whiteNumber(new int[]{1, 1, 1, 2}));
        System.out.println(whiteHats.whiteNumber(new int[]{10, 10}));
    }
}
