/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 11/6/13
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChangingString {

    public int distance(String A,String B, int K) {

        char[] source = A.toCharArray();
        char[] target = B.toCharArray();

        int totalDistance = calculateDistance(source,target);
        while(K > 0) {
            int curMaxDiffIdx = getMaxDiffIdx(source,target);
            if(curMaxDiffIdx == -1)
                break;

            totalDistance -= Math.abs(source[curMaxDiffIdx] - target[curMaxDiffIdx]);
            source[curMaxDiffIdx] = target[curMaxDiffIdx];
            K--;
        }

        totalDistance += K;

        return totalDistance;
    }

    public int calculateDistance(char[] source,char[] target) {
        int distance = 0;
        for(int i=0; i<source.length; ++i) {
            distance += Math.abs(source[i] - target[i]);
        }

        return distance;
    }

    public int getMaxDiffIdx(char[] source,char[] target) {
        int maxDiff = 0,maxDiffIdx = -1;

        for(int i=0; i<source.length; ++i) {
            int curDiff = Math.abs(source[i] - target[i]);
            if(curDiff > maxDiff) {
                maxDiff = curDiff;
                maxDiffIdx = i;
            }
        }

        return maxDiffIdx;
    }
}

//DONE
