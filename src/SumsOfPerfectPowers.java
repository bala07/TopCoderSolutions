//DONE

import java.util.ArrayList;

public class SumsOfPerfectPowers {

    final int MAX_NUM = (int)5e6;
    private ArrayList<Integer> perfectPowers = null;
    boolean[] sumOfTwoPerfectPowers = null;

    public int howMany(int lowerBound, int upperBound) {
        perfectPowers = new ArrayList<Integer>();
        sumOfTwoPerfectPowers = new boolean[MAX_NUM+10];

        computePerfectPowers();

        computeAllSumOfTwoPerfectPowers();

        int count = 0;

        for(int num = lowerBound; num <= upperBound; ++num) {
            if(sumOfTwoPerfectPowers[num]) {
                ++count;
            }
        }
        System.out.println(count);
        return count;
    }

    private void computeAllSumOfTwoPerfectPowers() {
        for(int i=0; i<perfectPowers.size(); ++i) {
            for(int j=i; j<perfectPowers.size(); ++j) {
                if(perfectPowers.get(i) + perfectPowers.get(j) <= MAX_NUM) {
                    sumOfTwoPerfectPowers[perfectPowers.get(i) + perfectPowers.get(j)] = true;
                }
            }
        }
    }

    private void computePerfectPowers() {
        boolean[] flag = new boolean[MAX_NUM+10];
        perfectPowers.add(0);
        perfectPowers.add(1);

        for(long num=2; num*num <= (long)MAX_NUM; ++num) {
            if(flag[(int)num]) {
                continue;
            }
            long value = num * num;
            while(value <= MAX_NUM) {
                if(!flag[(int)value]) {
                    perfectPowers.add((int)value);
                }
                flag[(int)value] = true;
                value *= num;
            }
        }
        System.out.println(perfectPowers.size());
    }


    public static void main(String[] args) {
        SumsOfPerfectPowers sumsOfPerfectPowers = new SumsOfPerfectPowers();
        sumsOfPerfectPowers.howMany(1,5000000);
    }
}
