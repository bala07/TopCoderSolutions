/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 12/16/13
 * Time: 11:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utilities {
    void printArray(Object[] arrayObj) {

        String str = "abc+-def+-ghi";
        String[] tokens = str.split("[+-]");
        System.out.println(tokens.length);
        for(int i=0; i<tokens.length; ++i) {
            System.out.print(tokens[i]+" ");
        }

        System.out.println();

    }

    public static void main(String[] args) {
        Utilities utilities = new Utilities();
        utilities.printArray(new Object[1]);
    }
}
