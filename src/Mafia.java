public class Mafia {

    final int MAX_PLAYERS = 16;

    String[] responses = null;
    int[] guilt = null;
    int playerIndex = -1;
    int[] dp = null;
    int playersCount = 0;

    public int play(int[] guilt, String[] responses, int playerIndex) {

        this.guilt = guilt;
        this.responses = responses;
        playersCount = guilt.length;
        this.playerIndex = playerIndex;

        dp = new int[(1<<MAX_PLAYERS) + 100];

        int mask = (1<<playersCount) - 1;
        if(guilt.length % 2 != 0) {
            int idx = getMaxGuiltIdx(mask);
            mask ^= (1<<idx);
        }

        int maxNights = findMaxNights(mask);

        return maxNights;
    }

    int findMaxNights(int mask) {
        if(isEndState(mask)) {
            return 0;
        }

        for(int i=0; i<playersCount; ++i) {
            if(i != playerIndex && (mask & (1<<i)) != 0) {
                int newMask = ( mask ^ (1<<i) );
                updateGuilt(i,true);
                int villagerToRemoveIdx = getMaxGuiltIdx(newMask);
                newMask ^= (1<<villagerToRemoveIdx);
                dp[mask] = Math.max(dp[mask],findMaxNights(newMask)+1);
                updateGuilt(i,false);
            }
        }

        return dp[mask];

    }

    private void updateGuilt(int idx, boolean flag) {
        for(int i=0; i<playersCount; ++i) {
            char ch = responses[idx].charAt(i);
            if(ch >= 'A' && ch <= 'Z') {
                guilt[i] += (flag ? (ch-'A')+1 : -((ch-'A')+1));
            }
            else {
                guilt[i] -= (flag ? (ch-'a')+1 : -((ch-'a')+1));
            }
        }

    }

    private boolean isEndState(int mask) {
        if((mask & (1<<playerIndex)) == 0 || (mask & ~(1<<playerIndex)) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private int getMaxGuiltIdx(int mask) {

        int idx = -1;
        for(int i=0; i<playersCount; ++i ) {
            if((mask & (1<<i)) != 0) {
                if(idx==-1 || guilt[i] > guilt[idx]) {
                    idx = i;
                }
            }
        }

        //System.out.println(idx);
        return idx;
    }
}

// DONE
