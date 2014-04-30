public class TaxiManager {

    final int MAX_ROADS = 50;
    final int MAX_CUSTOMERS = 12;
    int[] from,to;
    int[][] time;
    int[][] dp;

    public int schedule(String[] roads, String[] customers) {
        initialize(roads,customers);
        int overallBestTime = Integer.MAX_VALUE;

        for(int i=0; i<(1<<customers.length); ++i) {
            int mask1 = i;
            int mask2 = ((~mask1)^(~0 << (customers.length)));
            System.out.println(Integer.toString(mask1,2));
            System.out.println(Integer.toString(mask2,2));
            int curBestTime = Math.max(getMinTime(0,mask1),getMinTime(0,mask2));
            overallBestTime = Math.min(overallBestTime,curBestTime);
        }

        return overallBestTime;

    }

    private int getMinTime(int loc,int mask) {
        if(mask == 0) {
            return time[loc][0];
        }
        if(dp[mask][loc] != -1) {
            return dp[loc][mask];
        }

        dp[loc][mask] = Integer.MAX_VALUE;
        int curBest = Integer.MAX_VALUE;

        for(int i=0; i<from.length; ++i) {
            if((mask & (1<<i)) != 0) {
                int newBest = time[loc][from[i]] + time[from[i]][to[i]] + getMinTime(to[i],mask^(1<<i));
                curBest = Math.min(curBest,newBest);
            }
        }

        dp[loc][mask] = curBest;
        return curBest;
    }

    private void initialize(String[] roads, String[] customers) {
        from = new int[customers.length];
        to = new int[customers.length];

        for(int i=0; i<customers.length; ++i) {
            from[i] = Integer.parseInt(customers[i].split(" ")[0]);
            to[i] = Integer.parseInt(customers[i].split(" ")[1]);
        }

        dp = new int[MAX_ROADS+1][1<<MAX_CUSTOMERS];
        for(int i=0; i<MAX_ROADS; ++i) {
            for(int j=0; j<(1<<MAX_CUSTOMERS); ++j) {
                dp[i][j] = -1;
            }
        }

        time = new int[roads.length+1][roads.length+1];

        findAllPairsShortestPath(roads);
    }

    private void findAllPairsShortestPath(String[] roads) {
        for(int i=0; i<roads.length; ++i) {
            for(int j=0; j<roads.length; ++j) {
                time[i][j] = roads[i].charAt(j) - '0';
                if(time[i][j] == 0) {
                    time[i][j] = Integer.MAX_VALUE;
                }

            }
        }

        for(int k=0; k<roads.length; ++k) {
            for(int i=0; i<roads.length; ++i) {
                for(int j=0; j<roads.length; ++j) {
                    if(time[i][k] != Integer.MAX_VALUE && time[k][j] != Integer.MAX_VALUE) {
                        time[i][j] = Math.min(time[i][j],time[i][k] + time[k][j]);
                    }
                }
            }
        }

        for(int i=0; i<roads.length; ++i) {
           for(int j=0; j<roads.length; ++j) {
               System.out.print(time[i][j]+" ");
           }
            System.out.println();
        }
    }
}
