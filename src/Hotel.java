
public class Hotel {

    public int marketCost(int minCustomers, int[] customers, int[] cost) {

        int citiesCount = cost.length;

        int[][] dp = new int[citiesCount][minCustomers+2];

        for(int customerCnt = 1; customerCnt <= minCustomers; ++customerCnt) {
            dp[0][customerCnt] = ((int)Math.ceil(customerCnt*1.0/customers[0]*1.0)) * cost[0];
        }

        for(int cityIdx = 1; cityIdx < citiesCount; ++cityIdx) {

            for(int customerCnt = 1; customerCnt <= minCustomers; ++customerCnt) {

                dp[cityIdx][customerCnt] = Integer.MAX_VALUE;

                for(int customerCntFromCurrentCity = 0; customerCntFromCurrentCity <= customerCnt; ++customerCntFromCurrentCity) {

                    int cost1 = ((int)Math.ceil(customerCntFromCurrentCity*1.0/customers[cityIdx]*1.0)) * cost[cityIdx];
                    int customersObtainedFromCurrentCity = cost1/cost[cityIdx] * customers[cityIdx];

                    int cost2 = 0;
                    if(customerCnt - customersObtainedFromCurrentCity >= 0) {
                        cost2 = dp[cityIdx-1][customerCnt - customersObtainedFromCurrentCity];
                    }
//
//                    if(cityIdx == 1 && customerCnt == 1 && customerCntFromCurrentCity == 1) {
//                        System.out.println(cost1 + " " + cost2 + " " + customersObtainedFromCurrentCity);
//                    }

                    dp[cityIdx][customerCnt] = Math.min(dp[cityIdx][customerCnt], cost1 + cost2);
                }
            }
        }

//        for(int i=0; i<3; ++i) {
//            for(int j=0; j<=10; ++j) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        return dp[citiesCount-1][minCustomers];

    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        System.out.println(hotel.marketCost(
                                            100,
                                            new int[]{9, 11, 4, 7, 2, 8},
                                            new int[]{4, 9, 3, 8, 1, 9}));
    }
}
