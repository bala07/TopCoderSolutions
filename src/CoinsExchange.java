/**
 * Created by balasubn on 1/5/14.
 */

// DONE

public class CoinsExchange {
    public int countExchanges(int g1, int s1, int b1, int g2, int s2, int b2) {
        int exchanges = 0;
        while(g1<g2) {
            if(s1 >= 11) {
                s1 -= 11;
                g2++;
                exchanges++;
            }
            else if(b1 >= 11) {
                s1 += 1;
                b1 -= 11;
                exchanges++;
            }
            else {
                return -1;
            }
        }

        while(s1<s2) {
            if(g1 > g2) {
                s1 += 9;
                g1--;
                exchanges++;
            }
            else if(b1 >= 11) {
                s1++;
                b1 -= 11;
                exchanges++;
            }
            else {
                return -1;
            }
        }

        while(b1<b2) {
            if(s1 > s2) {
                b1 += 9;
                s1--;
                exchanges++;
            }
            else if(g1 > g2) {
                s1 += 9;
                g1--;
                exchanges++;
            }
            else {
                return -1;
            }
        }

        return exchanges;
    }
}
