/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 11/22/13
 * Time: 10:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Chessboard {
    public String changeNotation(String cell) {
        String res ="";
        if(isCellMark(cell)) {
            char col = cell.charAt(0);
            int row = Integer.parseInt(cell.charAt(1)+"");

            res = ((row-1)*8 + (col-'a') + 1)+"";
        }
        else {
            int cellNumber = Integer.parseInt(cell);
            char col = (char)((cellNumber-1) % 8 + 'a' -1 );
            int row = cellNumber/8 + 1;
            res = col+""+row;
        }

        return res;
    }

    private boolean isCellMark(String cell) {
        return (cell.charAt(0) >= 'a' && cell.charAt(0) <= 'z');
    }
}

//DONE
