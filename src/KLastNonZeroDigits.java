/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 11/6/13
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class KLastNonZeroDigits {

    public String getKDigits(int N, int K) {

        long fact = computeFactorial(N);
        String factStr = Long.toString(fact);
        factStr = removeTrailingZeroes(factStr);

        return ( factStr.length() <= K  ? factStr : factStr.substring(factStr.length() - K,factStr.length()));

    }

    private String removeTrailingZeroes(String str) {
       while(str.length() > 0 && str.charAt(str.length()-1) == '0') {
           str = str.substring(0,str.length()-1);
       }
       return str;
    }

    private long computeFactorial(int N) {
        long res = 1;
        for(int i=2; i<=N; ++i) {
            res *= i;
        }

        return res;
    }


}

// DONE