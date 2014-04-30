/**
 * Created by balasubn on 2/12/14.
 */
public class NoEights {
    public int smallestAmount(int low, int high) {
        String start = Integer.toString(low);
        String end = Integer.toString(high);

        if(start.length() != end.length()) {
            return 0;
        }

        if(start.equals(end)) {
            return countEights(start);
        }

        int res = 0;
        for(int i=0; i<start.length(); ++i) {
            char c1 = start.charAt(i), c2 = end.charAt(i);
            if(c1 != c2) {
                break;
            }

            if(c1=='8') {
                res++;
            }
        }

        return res;
    }

    private int countEights(String str) {
        int count = 0;
        for(int i=0; i<str.length(); ++i) {
            if(str.charAt(i) == '8') {
                ++count;
            }
        }

        return count;
    }
}
