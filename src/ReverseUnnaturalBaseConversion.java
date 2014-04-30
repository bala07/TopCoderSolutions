public class ReverseUnnaturalBaseConversion {

    public String convertToBase(int x, int b) {

        if(x==0) {
            return "0";
        }

        boolean shouldAddNegativeSign = false;

        String res = "";
        if(x>0 && b<0) {
            shouldAddNegativeSign = true;
            x = -x;
        }

        while(x != 0) {
            res = mod(x,b)+"" + res;
            x = div(x,b);
        }

        if(shouldAddNegativeSign) {
            res = "-" + res;
        }

        return res;
    }

    private int div(int a, int b) {
        return (a-mod(a,b))/b;
    }

    private int mod(int a, int b) {
        int res = a%b;
        if(res < 0)
            res += b;

        return res;
    }

    public static void main(String[] args) {
        ReverseUnnaturalBaseConversion obj = new ReverseUnnaturalBaseConversion();
        obj.convertToBase(1,1);
    }
}

// DONE