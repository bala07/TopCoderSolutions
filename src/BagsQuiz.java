//DONE

public class BagsQuiz {

    public class Bag {
        int idx;
        Bag parent;

        Bag() {
            idx = 0;
            parent = null;
        }
    }

    Bag[] bags = null;

    public int checkIfProper(int n, String[] actions) {
        bags = new Bag[n+1];
        for(int i=1; i<=n; ++i) {
            bags[i] = new Bag();
            bags[i].idx = i;
        }

        for(String action : actions) {
            int retValue = 0;
            if(action.contains("PUT")) {
                retValue = processPutAction(action);
            }
            else if(action.contains("SET")) {
                retValue = processSetLooseAction(action);
            }
            else {
                retValue = processSwapAction(action);
            }
            if(retValue == -1) {
                return -1;
            }
        }

        if(isValidConfiguration()) {
            return countBagsOnFloor();
        }

        return -1;
    }

    private int countBagsOnFloor() {
        int bagCount = 0;
        for(int i=1; i<bags.length; ++i) {
            if(bags[i].parent == null) {
                ++bagCount;
            }
        }

        return bagCount;
    }

    private boolean isValidConfiguration() {
        boolean isValid = true;
        for(int i=1; i<bags.length; ++i) {
            Bag bag = bags[i];
            while(bag.parent != null) {
                if(bag.parent.idx < bag.idx) {
                    return false;
                }
                bag = bag.parent;
            }
        }

        return true;
    }

    private int processSwapAction(String action) {
        String[] tokens = action.split(" ");
        Bag bag1 = bags[Integer.parseInt(tokens[1])];
        Bag bag2 = bags[Integer.parseInt(tokens[3])];

        if(!onFloor(bag1) || !onFloor(bag2)) {
            return -1;
        }

        for(int i=1; i<bags.length; ++i) {
            if(bags[i].parent == bag1) {
                bags[i].parent = bag2;
            }
            else if(bags[i].parent == bag2) {
                bags[i].parent = bag1;
            }
        }

        return 0;
    }

    private int processSetLooseAction(String action) {
        String[] tokens = action.split(" ");
        Bag bag = bags[Integer.parseInt(tokens[1])];

        if(!onFloor(bag)) {
            return -1;
        }

        for(int i=1; i<bags.length; ++i) {
            if(bags[i].parent == bag) {
                bags[i].parent = null;
            }
        }
        return 0;
    }

    private int processPutAction(String action) {
        String[] tokens = action.split(" ");
        Bag outerBag = bags[Integer.parseInt(tokens[3])];
        Bag innerBag = bags[Integer.parseInt(tokens[1])];

        if(!onFloor(outerBag) || !onFloor(innerBag)) {
            return -1;
        }

        innerBag.parent = outerBag;
        return 0;
    }

    private boolean onFloor(Bag bag) {
        return bag.parent == null;
    }


}
