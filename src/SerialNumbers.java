/**
 * Created by Balasubramanian on 6/7/14.
 */
public class SerialNumbers
{
    public String[] sortSerials(String[] serialNumbers)
    {
        for(int i=1; i<serialNumbers.length; ++i)
        {
            String tmp = serialNumbers[i];
            int j=i-1;
            while(j >= 0)
            {
                if(!compareSerials(tmp, serialNumbers[j]))
                {
                    break;
                }

                --j;
            }

            for(int k=i-1; k>=j+1; --k)
            {
                serialNumbers[k+1] = serialNumbers[k];
            }

            serialNumbers[j+1] = tmp;
        }

        return serialNumbers;
    }

    private boolean compareSerials(String serial1, String serial2)
    {
        if(serial1.length() < serial2.length())
        {
            return true;
        }

        if(serial1.length() > serial2.length())
        {
            return false;
        }

        int sumOfDigitsSerial1 = sumOfDigits(serial1);
        int sumOfDigitsSerial2 = sumOfDigits(serial2);

        if(sumOfDigitsSerial1 < sumOfDigitsSerial2)
        {
            return true;
        }

        if(sumOfDigitsSerial1 > sumOfDigitsSerial2)
        {
            return false;
        }

        int comparisonResult = serial1.compareTo(serial2);

        if(comparisonResult < 0)
        {
            return true;
        }

        return false;
    }

    private int sumOfDigits(String serial)
    {
        int sum = 0;

        for(int i=0; i<serial.length(); ++i)
        {
            char ch = serial.charAt(i);

            if(ch >= '0' && ch <= '9')
            {
                sum += (ch - '0');
            }
        }

        return sum;
    }

    public static void main(String[] args)
    {
        SerialNumbers tester = new SerialNumbers();

        String[] result = tester.sortSerials(new String[]{"ABCDE", "BCDEF", "ABCDA", "BAAAA", "ACAAA"}
        );

        for(String word : result)
        {
            System.out.println(word);
        }
    }
}
