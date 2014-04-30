import java.util.HashMap;

public class WebsiteRank
{
    private HashMap<String, Integer> websiteLookup = new HashMap<String, Integer>();
    private boolean[][] adjMatrix = null;
    private boolean[][] reachabilityMatrix = null;
    long[] dp;
    private int websiteCount;

    public long countVotes(String[] votes, String website)
    {
        populateWebsiteLookupDictionary(votes);
        websiteCount = websiteLookup.size();

        reachabilityMatrix = new boolean[websiteCount][websiteCount];
        adjMatrix = new boolean[websiteCount +1][websiteCount +1];
        dp = new long[websiteCount +1];

        populateAdjMatrix(votes);

        computeTransitiveClosure();

        long voteCount = getVoteCount(websiteLookup.get(website));

        System.out.println(websiteLookup.get("R") + " " + websiteLookup.get("F") + " " + websiteLookup.get("Q"));

//        System.out.println(dp[websiteLookup.get("K")] + " " + dp[websiteLookup.get("F")] + " " + dp[websiteLookup.get("Q")]);

        return voteCount;
    }

    private void computeTransitiveClosure()
    {
        for(int i=0; i<reachabilityMatrix.length; ++i) {
            for(int j=0; j<reachabilityMatrix.length; ++j) {
                reachabilityMatrix[i][j] = adjMatrix[i][j];
            }
        }

        for(int k=0; k<websiteCount; ++k) {
            for(int i=0; i<websiteCount; ++i) {
                for(int j=0; j<websiteCount; ++j) {
                    if(reachabilityMatrix[i][k] && reachabilityMatrix[k][j]) {
                        reachabilityMatrix[i][j] = true;
                    }
                }
            }
        }
    }

    private void populateAdjMatrix(String[] votes)
    {
        for(String vote : votes) {
            String[] websites = vote.split(" ");
            String destination = websites[0];
            int destinationIndex = websiteLookup.get(destination);

            for(int i=1; i<websites.length; ++i) {
                int sourceIndex = websiteLookup.get(websites[i]);
                adjMatrix[sourceIndex][destinationIndex] = true;
            }
        }
    }

    private void populateWebsiteLookupDictionary(String[] votes)
    {
        int index = 0;
        websiteLookup.clear();
        for(String vote : votes) {
            String[] websites = vote.split(" ");
            for(String website : websites) {
                if(!websiteLookup.containsKey(website)) {
                    websiteLookup.put(website, index++);
                }
            }
        }
    }

    long getVoteCount(int v) {
        if(dp[v] != 0) {
            return dp[v];
        }

        if(v==4) {
            System.out.println("V is 4");
        }
        dp[v] = 1;

        for(int i=0; i<websiteCount; ++i) {
            if(v==4 && i==0) {
                System.out.println(adjMatrix[i][v] + " " + reachabilityMatrix[i][v] + " " +reachabilityMatrix[v][i]);
            }
            if(i != v && adjMatrix[i][v] && !reachabilityMatrix[v][i]) {
                if(v==4)    {
                    System.out.println(i);
                }
                dp[v] += getVoteCount(i);
            }
        }

        return dp[v];
    }

    public static void main(String[] args) {
        WebsiteRank websiteRank = new WebsiteRank();
        System.out.println(websiteRank.countVotes(new String[]{"A B C","B C Q"}, "A"));
    }
}
