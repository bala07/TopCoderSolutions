/**
 * Created by balasubn on 9/27/14.
 */
public class RoadConstruction {
    private final char DIPLOMAT = 'D';

    public int getExitTime(String[] currentLanes) {
        boolean[] flag = new boolean[currentLanes.length];
        int exitedCars = 0;

        for (int i = 0; i < currentLanes.length; ++i) {
            while (currentLanes[i].length() > 0) {
                int idx = i;
                if (!flag[i]) {
                    flag[i] = true;
                    for (int k = i + 1; k < currentLanes.length; ++k) {
                        if (currentLanes[k].length() == 0) {
                            continue;
                        }
                        if (flag[k]) {
                            idx = k;
                            break;
                        } else {
                            flag[k] = true;
                            idx = k;
                        }
                    }
                }

                if (currentLanes[idx].charAt(0) == DIPLOMAT) {
                    return exitedCars;
                }

                currentLanes[idx] = currentLanes[idx].substring(1, currentLanes[idx].length());
                flag[idx] = false;
                ++exitedCars;
            }
        }

        return -1;
    }
}
