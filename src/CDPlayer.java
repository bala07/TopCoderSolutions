/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 11/19/13
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class CDPlayer {


    public int isRandom(String[] songlist, int n) {
        String songs = join(songlist);
        int len = songs.length();
        boolean[] dp = new boolean[len];
        dp[len-1] = true;

        for(int i=len-2; i>=0; --i) {
            dp[i] = hasDistinctCharacters(songs.substring(i,Math.min(i+n,len))) && (i+n >= len || dp[i+n]);
        }

        for(int i=0; i<Math.min(n,len); ++i) {
            if(dp[i] && hasDistinctCharacters(songs.substring(0,Math.max(0,i)))) {
                return i;
            }
        }

        return -1;
    }

    private boolean hasDistinctCharacters(String str) {
        boolean[] flag = new boolean[26];
        for(int i=0; i<str.length(); ++i) {
            char ch = str.charAt(i);
            if(flag[ch-'A']) {
                return false;
            }
            flag[ch-'A'] = true;
        }
        return true;
    }

    private String join(String[] str) {
        StringBuilder res = new StringBuilder();
        for(String s : str) {
            res.append(s);
        }

        return res.toString();
    }
}

// DONE
