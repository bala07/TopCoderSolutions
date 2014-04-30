/**
 * Created by balasubn on 4/7/14.
 */
public class TakeSubstringGame
{

    final int MAX_N = (int)1e6 + 10;
    boolean[] canWin = new boolean[MAX_N];

    public int winningMove(int n)
    {
        calculateWinningPositions();

        int winningNumber = Integer.MAX_VALUE;
        String[] numberSubStrings = getSubStringsForNumber(n);

        for(String numberSubstring : numberSubStrings)
        {
            int number = Integer.parseInt(numberSubstring);
            if(number > 0 && !canWin[n - number])
            {
                winningNumber = Math.min(winningNumber, number);
            }
        }

        if(winningNumber == Integer.MAX_VALUE)
        {
            return -1;
        }

        return winningNumber;
    }

    private void calculateWinningPositions()
    {
        for(int number = 10; number < MAX_N; ++number)
        {
            String[] numberSubStrings = getSubStringsForNumber(number);

            for(String numberSubString : numberSubStrings)
            {
                int num = Integer.parseInt(numberSubString);
                if(num > 0 && !canWin[number - num]) {
                    canWin[number] = true;
                    break;
                }
            }
        }
    }

    private String[] getSubStringsForNumber(int n)
    {
        String numberString = Integer.toString(n);
        int subStringsCount = (numberString.length()*(numberString.length()+1)) / 2 - 1;
        String[] subStrings = new String[subStringsCount];
        int idx = 0;

        for(int length = 1; length < numberString.length(); ++length)
        {
            for(int start = 0; numberString.length() - start >= length; ++start)
            {
                int end = start + length;
                subStrings[idx++] = new String(numberString.substring(start, end));
            }
        }

        return subStrings;
    }

    public static void main(String[] args)
    {
        TakeSubstringGame game = new TakeSubstringGame();
//        System.out.println(game.winningMove(5));
//        System.out.println(game.winningMove(10));
//        System.out.println(game.winningMove(13));
        System.out.println(game.winningMove(23900));
//        System.out.println(game.winningMove(566));
//        System.out.println(game.winningMove(23900));
    }
}
