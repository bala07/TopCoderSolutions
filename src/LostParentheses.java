import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 12/16/13
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class LostParentheses {
    public int minResult(String exp) {
        String[] tokens = splitExpression(exp);
        int numbersCount = (int)Math.ceil(tokens.length/2.0);
        int[][] dp = new int[numbersCount][numbersCount];
        for(int i=0; i<numbersCount; ++i) {
            dp[i][i] = Integer.parseInt(tokens[i*2]);
        }

        for(int len=2; len<=numbersCount; ++len) {
            for(int i=0; numbersCount-i>=len; ++i) {
                int start = i,end = i+len-1;
                dp[start][end] = Integer.MAX_VALUE;
                for(int k=start; k<end; ++k) {
                    dp[start][end] = Math.min(dp[start][end],compute(dp[start][k],dp[k+1][end],tokens[k*2+1]));
                }
            }
        }

        return dp[0][numbersCount-1];
    }

    private int compute(int op1, int op2, String symbol) {
        if(symbol.charAt(0) == '+') {
            return op1 + op2;
        }
        else {
            return op1 - op2;
        }
    }

    String[] splitExpression(String exp) {
        ArrayList<String> tokens = new ArrayList<String>();
        int start = 0;
        for(int i=0; i<exp.length(); ++i) {
            char ch = exp.charAt(i);
            if(ch=='+' || ch=='-') {
                tokens.add(exp.substring(start,i));
                tokens.add(ch+"");
                start = i+1;
            }
        }

        tokens.add(exp.substring(start,exp.length()));

        String[] tokensArr = new String[tokens.size()];
        for(int i=0; i<tokens.size(); ++i) {
            tokensArr[i] = tokens.get(i);
        }

        return tokensArr;
    }
}
