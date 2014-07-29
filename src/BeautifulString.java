/**
 * Created by Balasubramanian on 7/29/14.
 */
public class BeautifulString
{
    public int maximumLength(int countA, int countB, int maxA, int maxB)
    {
        if(countA == 0 || maxA == 0)
        {
            return Math.min(countB, maxB);
        }

        if(countB == 0 || maxB == 0)
        {
            return Math.min(countA, maxA);
        }

        return (int)Math.max(getMaxStringLength(countA, maxA, countB, maxB),
                getMaxStringLength(countB, maxB, countA, maxA));
    }

    private long getMaxStringLength(long maxGroup1Letters, long maxSequenceOfGroup1Letters,
                                    long maxGroup2Letters, long maxSequenceOfGroup2Letters)
    {
        long maxLength = 0;
        long maxGroupsPossible = Math.min(maxGroup1Letters, maxGroup2Letters);

        for(int group = 1; group <= maxGroupsPossible; ++group)
        {
            long maxGroup1LettersForCurGroup = getMaxPossibleLetters(maxGroup1Letters, maxSequenceOfGroup1Letters, group);
            long maxGroup2LettersForCurGroup = getMaxPossibleLetters(maxGroup2Letters, maxSequenceOfGroup2Letters, group + 1);

            maxLength = Math.max(maxLength, maxGroup1LettersForCurGroup + maxGroup2LettersForCurGroup);
        }

        return maxLength;
    }

    private long getMaxPossibleLetters(long maxLettersAvailable, long maxLengthOfEachGroup, long totalGroups)
    {
        long maxPossibleLetters = maxLengthOfEachGroup * totalGroups;

        return Math.min(maxPossibleLetters, maxLettersAvailable);
    }
}
