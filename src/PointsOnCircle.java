import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Balasubramanian on 6/3/14.
 */
public class PointsOnCircle
{
    long radius;
    public long count(int r)
    {
        this.radius = r;

        ArrayList<Long> divisorsOfR = new ArrayList<>();
        HashSet<Long> divisorsOfRSquared = new HashSet<>();

        long d1 = 0, d3 = 0;

        for(long divisor = 1; divisor * divisor <= radius; ++divisor)
        {
            if(radius % divisor == 0)
            {
                divisorsOfR.add(divisor);
                if(divisor * divisor != radius)
                {
                    divisorsOfR.add(radius / divisor);
                }
            }
        }


        for(int i=0; i<divisorsOfR.size(); ++i)
        {
            for(int j=i; j<divisorsOfR.size(); ++j)
            {
                divisorsOfRSquared.add(divisorsOfR.get(i) * divisorsOfR.get(j));
            }
        }

        for(long divisor : divisorsOfRSquared)
        {
            if(divisor % 4 == 1)
            {
                ++d1;
            }
            else if(divisor % 4 == 3)
            {
                ++d3;
            }
        }

        return 4 * (d1 - d3);
    }

    public static void main(String[] args)
    {
        PointsOnCircle tester = new PointsOnCircle();

        System.out.println(tester.count(2000000000));
    }
}
