/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 12/8/13
 * Time: 8:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonMultiples {

    final int MAX_FACTOR = 100;
    int[] primeFactors;
    int[] powers;
    private int startNumber;

    public int countCommMult(int[] a, int lower, int upper) {
        this.primeFactors = new int[MAX_FACTOR + 1];
        this.powers = new int[MAX_FACTOR + 1];

        for (int i = 0; i < a.length; ++i) {
            factorise(a[i]);
        }

        int startNumber = getStartNumber(upper);

        int result = startNumber == -1 ? 0 : getNumberOfMultiples(startNumber, lower, upper);

        return result;
    }

    private int getNumberOfMultiples(int startNumber, int lower, int upper) {
        int val1 = upper/startNumber;
        int val2 = (lower-1)/startNumber;

        return val1-val2;
    }

    private void factorise(int num) {

        for(int i=2; i*i<= num; ++i) {
            if(num%i == 0) {
                int powerCount = 0;
                while(num%i == 0) {
                    ++powerCount;
                    num /= i;
                }
                powers[i] = Math.max(powers[i],powerCount);
            }
        }

        if(num > 1) {
            powers[num] = Math.max(powers[num],1);
        }
    }


    public int getStartNumber(int upper) {
        long startNumber = 1;
        for(int i=2; i<MAX_FACTOR; ++i) {
            if(powers[i] > 0) {
                startNumber *= (int)Math.pow(i*1.0,powers[i]*1.0);
                if(startNumber > upper) {
                    return -1;
                }
            }
        }

        return (int)startNumber;
    }
}

// DONE

