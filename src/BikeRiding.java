import java.util.Arrays;

public class BikeRiding {

    String[] paths;
    int destination;
    long[] dp;
    boolean[] cycleStart,vis;

    public int countRoutes(String[] paths, int[] startPoints, int endPoint, int n) {
        this.paths = paths;
        destination = endPoint;
        dp = new long[paths.length+1];
        Arrays.fill(dp,Integer.MIN_VALUE);
        cycleStart = new boolean[paths.length];
        vis = new boolean[paths.length];
        long totalPaths = 0;

        for(int i=0; i<startPoints.length; ++i) {
            totalPaths += countTotalPathsFromVertex(startPoints[i]);
            if(totalPaths > n) {
                return -1;
            }
        }

        for(int i=0; i<paths.length; ++i) {
            if(dp[i] != Integer.MIN_VALUE && dp[i] > 0 && cycleStart[i]) {
                return -1;
            }
        }

        return (int)totalPaths;
    }

    private long countTotalPathsFromVertex(int vertex) {
        if(dp[vertex] != Integer.MIN_VALUE) {
            return dp[vertex];
        }

        vis[vertex] = true;
        dp[vertex] = (vertex == destination) ? 1 : 0;

        for(int i=0; i<paths.length; ++i) {
            char ch = paths[vertex].charAt(i);
            if(ch == '1') {
                if(vis[i]) {
                    cycleStart[i] = true;
                }
                else {
                    dp[vertex] += countTotalPathsFromVertex(i);
                }
            }
        }

        vis[vertex] = false;
        return dp[vertex];
    }
}

//DONE
