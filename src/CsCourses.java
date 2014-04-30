/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 11/4/13
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class CsCourses {

    final int MAX_COURSES = 100;

    boolean[][][] vis = new boolean[MAX_COURSES][MAX_COURSES][MAX_COURSES];
    int[][][] cached = new int[MAX_COURSES][MAX_COURSES][MAX_COURSES];
    int[][][] courseFor = new int[MAX_COURSES][MAX_COURSES][MAX_COURSES];
    int skillBound = 0;
    int[] tValue,pValue,expire;

    public int[] getOrder(int[] tValue, int[] pValue, int[] expire, int skillBound) {

        this.skillBound = skillBound;
        this.tValue = tValue;
        this.pValue = pValue;
        this.expire = expire;

        int minCoursesToTake = calcMinCoursesToTake(0,0,0);
        if(minCoursesToTake == Integer.MAX_VALUE) {
            return new int[0];
        }
        else {
            List<Integer> coursesToTake = getCoursesToTake();
            return convertListToIntArray(coursesToTake);
        }

    }

    private int calcMinCoursesToTake(int curTValue,int curPValue,int curMonth) {

        if(curTValue >= skillBound && curPValue >= skillBound) {
            return 0;
        }

        if(curMonth >= tValue.length) {
            return Integer.MAX_VALUE;
        }

        if(vis[curTValue][curPValue][curMonth]) {
            return cached[curTValue][curPValue][curMonth];
        }

        vis[curTValue][curPValue][curMonth] = true;
        int minCost = Integer.MAX_VALUE;

        for(int i=0; i<tValue.length; ++i) {
            if(curTValue >= tValue[i]-1 && curPValue >= pValue[i]-1 && curMonth < expire[i]) {
                int curMinCost = calcMinCoursesToTake(Math.max(curTValue,tValue[i]),Math.max(curPValue,pValue[i]),curMonth+1);
                if(curMinCost != Integer.MAX_VALUE && curMinCost+1 < minCost) {
                    minCost = curMinCost+1;
                    courseFor[curTValue][curPValue][curMonth] = i;
                }
            }
        }
        cached[curTValue][curPValue][curMonth] = minCost;

        return minCost;

    }

    private List<Integer> getCoursesToTake() {
        List<Integer> res =new ArrayList<Integer>();

        int curTValue = 0,curPValue = 0,curMonth = 0;
        while(curTValue < skillBound || curPValue < skillBound) {
            int courseToTake = courseFor[curTValue][curPValue][curMonth];
            res.add(courseToTake);

            curTValue = Math.max(curTValue,tValue[courseToTake]);
            curPValue = Math.max(curPValue,pValue[courseToTake]);
            curMonth++;
        }

        return res;
    }

    private int[] convertListToIntArray(List<Integer> list) {
        int[] resArray = new int[list.size()];

        for(int i=0; i<resArray.length; ++i) {
            resArray[i] = list.get(i).intValue();
        }

        return resArray;
    }
}