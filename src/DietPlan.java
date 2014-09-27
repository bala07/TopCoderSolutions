/**
 * Created by balasubn on 9/27/14.
 */
public class DietPlan {

    private final String CHEATER = "CHEATER";

    public String chooseDinner(String diet, String breakfast, String lunch) {
        boolean[] flag = new boolean[26];

        for (int i = 0; i < diet.length(); ++i) {
            flag[diet.charAt(i) - 'A'] = true;
        }

        for(int i = 0; i < breakfast.length(); ++i) {
            int idx = breakfast.charAt(i) - 'A';

            if(!flag[idx]) {
                return CHEATER;
            }

            flag[idx] = false;
        }

        for(int i = 0; i < lunch.length(); ++i) {
            int idx = lunch.charAt(i) - 'A';

            if(!flag[idx]) {
                return CHEATER;
            }

            flag[idx] = false;
        }

        String dinner = "";
        for(int i = 0; i < flag.length; ++i) {
            if(flag[i]) {
                dinner += (char)(i + 'A') + "";
            }
        }

        return dinner;
    }
}
