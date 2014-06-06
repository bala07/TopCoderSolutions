/**
 * Created by Balasubramanian on 6/3/14.
 */
public class TournamentsAmbiguityNumber
{
    public int scrutinizeTable(String[] table)
    {
        int ambiguityNumber = 0;
        for(int player1 = 0; player1 < table.length; ++player1)
        {
            for(int player2 = player1 + 1; player2 < table.length; ++player2)
            {
                for(int player3 = player2 + 1; player3 < table.length; ++player3)
                {
                    boolean v1 = (table[player1].charAt(player2) == '1' || table[player1].charAt(player3) == '1');
                    boolean v2 = (table[player2].charAt(player1) == '1' || table[player2].charAt(player3) == '1');
                    boolean v3 = (table[player3].charAt(player1) == '1' || table[player3].charAt(player2) == '1');

                    if(v1 && v2 && v3)
                    {
                        ++ambiguityNumber;
                    }
                }
            }
        }

        return ambiguityNumber * 3;
    }
}
