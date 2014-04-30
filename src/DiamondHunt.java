/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 12/8/13
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class DiamondHunt {

    String diamond = "<>";
    public int countDiamonds(String mine) {
        int diamonds = 0;
        boolean allDiamondsFound = false;

        while(!allDiamondsFound) {
            int index = mine.indexOf(diamond);
            if(index == -1) {
                allDiamondsFound = true;
            }
            else {
                ++diamonds;
                mine = mine.replaceFirst(diamond,"");
            }
        }

        return diamonds;
    }
}

// DONE