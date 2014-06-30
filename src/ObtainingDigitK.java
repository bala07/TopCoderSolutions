/**
 * Created by Balasubramanian on 6/10/14.
 */
public class ObtainingDigitK
{
    public int minNumberToAdd(String number, int k)
    {
        if(hasDigit(number, k))
        {
            return 0;
        }

        for(int i=1; i<=9; ++i)
        {
            String sum = addStrings(number, Integer.toString(i));
//            System.out.println("sum for i = " + i + " : " + sum);
            if(hasDigit(sum, k))
            {
                return i;
            }
        }

        return -1;
    }

    private boolean hasDigit(String number, int k)
    {
        for(int i=0; i<number.length(); ++i)
        {
            int digit = Integer.parseInt(number.charAt(i) + "");
            if(digit == k)
            {
                return true;
            }
        }

        return false;
    }

    private String addStrings(String number1, String number2)
    {
        int idx1 = number1.length() - 1;
        int idx2 = number2.length() - 1;
        int sum = 0, carry = 0;

        StringBuilder result = new StringBuilder();

        while(idx1 >= 0 || idx2 >= 0 || carry > 0)
        {
            int digit1 = idx1 >= 0 ? number1.charAt(idx1) - '0' : 0;
            int digit2 = idx2 >= 0 ? number2.charAt(idx2) - '0' : 0;

            sum = digit1 + digit2 + carry;

            result.append((sum%10) + "");
            carry = sum / 10;

            --idx1;
            --idx2;
        }

        return (result.reverse()).toString();
    }

    public static void main(String[] args)
    {
        ObtainingDigitK tester = new ObtainingDigitK();

        System.out.println(tester.minNumberToAdd("999999999999", 1));
    }

}
