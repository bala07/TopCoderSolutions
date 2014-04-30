public class Pathfinding {

    final int UP = 0;
    final int DOWN = 1;
    final int LEFT = 2;
    final int RIGHT = 3;

    public int getDirections(int x, int y) {
        int curX = 0,curY = 0;
        int distanceTravelled = 0;
        int steps = 0;

        while(curX != x || curY != y) {
            if(curX != x) {
                int hDirRequired = getRequiredHDir(curX,x);
                int hDirAllowed = getAllowedHDir(curY);
                if(hDirRequired == hDirAllowed) {
                    distanceTravelled += dist(curX,curY,x,curY);
                    steps += Math.abs(x-curX);
                    curX = x;
                }
                else {
                    int vDirAllowed = getAllowedVDir(curX);
                    if(vDirAllowed == UP) {
                        distanceTravelled += dist(curX,curY,curX,curY+1);
                        curY += 1;

                    }
                    else {
                        distanceTravelled += dist(curX,curY,curX,curY-1);
                        curY -= 1;
                    }
                    distanceTravelled += dist(curX,curY,x,curY);
                    steps += Math.abs(x-curX) + 1;
                    curX = x;
                }
            }
            else {
                int vDirRequired = getRequiredVDir(curY,y);
                int vDirAllowed = getAllowedVDir(curX);
                if(vDirAllowed == vDirRequired) {
                    distanceTravelled += dist(curX,curY,curX,y);
                    steps += Math.abs(y-curY);
                    curY = y;
                }
                else {
                    int hDirAllowed = getAllowedHDir(curY);
                    if(hDirAllowed == LEFT) {
                        distanceTravelled += dist(curX,curY,curX-1,curY);
                        curX -= 1;
                    }
                    else {
                        distanceTravelled += dist(curX,curY,curX+1,curY);
                        curX += 1;
                    }
                    distanceTravelled += dist(curX,curY,curX,y);
                    steps += Math.abs(y-curY) + 1;
                    curY = y;
                }
            }
        }

        return steps;
    }

    private int dist(int x1, int y1, int x2, int y2) {
        int val1 = Math.abs(x2-x1);
        int val2 = Math.abs(y2-y1);

        return val1 + val2;
    }

    private int getRequiredVDir(int curY, int y) {
        if(y>curY) {
            return UP;
        }
        return DOWN;
    }

    private int getAllowedVDir(int x) {
        if(x%2==0) {
            return UP;
        }
        return DOWN;
    }

    private int getAllowedHDir(int y) {
        if(y%2==0) {
            return RIGHT;
        }
        return LEFT;

    }

    private int getRequiredHDir(int curX,int x) {
        if(x>curX) {
            return RIGHT;
        }
        else {
            return LEFT;
        }
    }
}

// NOT DONE
