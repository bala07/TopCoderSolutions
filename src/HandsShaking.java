/**
 * Created by Balasubramanian on 5/12/14.
 */
public class HandsShaking
{
    long[] dp = null;

    public long countPerfect(int n)
    {
        dp = new long[n+1];
        dp[0] = 1;

        for(int persons = 2; persons <=n; persons += 2)
        {
            dp[persons] = countPerfectHandShakes(persons);
        }

        return dp[n];
    }

    private long countPerfectHandShakes(int personsCount)
    {
        int refPerson = 1;
        long handShakes = 0;

        for(int person = 2; person <= personsCount; ++person)
        {
            int personsBetween = person - refPerson -1;
            int personsBeyond = personsCount - (personsBetween + 2);

            handShakes += dp[personsBetween] * dp[personsBeyond];
        }

        return handShakes;
    }

    public static void main(String[] args)
    {
        HandsShaking tester = new HandsShaking();

        System.out.println(tester.countPerfect(50));
    }
}
