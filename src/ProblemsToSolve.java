/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 10/27/13
 * Time: 11:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProblemsToSolve {

    int minNumber(int[] pleasantness, int variety) {

        int len=pleasantness.length;
        int minEvenPosition = 0, maxEvenPosition = 0;
        int minAnyPosition =0,maxAnyPosition = 0;

        for(int i=1; i<len; ++i) {
            if(pleasantness[i] < pleasantness[minEvenPosition])
                minEvenPosition = i;
            else if(pleasantness[i] > pleasantness[maxEvenPosition])
                maxEvenPosition = i;

            if(pleasantness[i] < pleasantness[minAnyPosition])
                minAnyPosition = i;
            else if(pleasantness[i] > pleasantness[maxAnyPosition])
                maxAnyPosition = i;

            if(pleasantness[maxEvenPosition] - pleasantness[minEvenPosition] >= variety)
                return i/2 + 1;
            else if(pleasantness[maxAnyPosition] - pleasantness[minAnyPosition] >= variety)
                return i/2 + 2;
        }

        return len;
    }
}

// DONE
