/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 12/1/13
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class Trekking {
    int findCamps(String trail, String[] plans) {
        int len = trail.length();

        int minCamps = Integer.MAX_VALUE;

        for(int i=0; i<plans.length; ++i) {
            int campsCount = 0;
            boolean planValid = true;
            for(int j=0; j<len; ++j) {
                if(plans[i].charAt(j) == 'C') {
                    if(trail.charAt(j)=='^') {
                        planValid = false;
                        break;
                    }
                    else {
                        ++campsCount;
                    }
                }
            }
            if(planValid) {
                minCamps = Math.min(minCamps,campsCount);
            }
        }

        return minCamps == Integer.MAX_VALUE ? -1 : minCamps;
    }
}

// DONE
