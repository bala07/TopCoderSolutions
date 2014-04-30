
public class QuantumAlchemy {

    final int MAX_RESOURCES = 26;
    final char REQUIRED_ELEMENT = 'X';
    String initial;
    String[] reactions;
    int[] resourcesAvailable;
    final char SEPARATOR = '-';
    int reactionsPerformed = 0;

    public int minSteps(String initial, String[] reactions) {
        this.initial = initial;
        this.reactions = reactions;
        resourcesAvailable = new int[MAX_RESOURCES];

        populateResourcesAvailableArray();

        boolean flag = findMinReactions(REQUIRED_ELEMENT);

        if(flag && resourcesUsedWithinLimits(resourcesAvailable)) {
            return reactionsPerformed;
        }

        return -1;
    }

    private void populateResourcesAvailableArray() {
        for(int i=0; i<initial.length(); ++i) {
            resourcesAvailable[initial.charAt(i)-'A']++;
        }
    }

    private boolean findMinReactions(char element) {

        if(initial.contains(element+"")) {
            if(resourcesAvailable[element-'A'] > 0) {
                resourcesAvailable[element-'A'] -= 1;
                return true;
            }
        }

        String ingredients = findReactionForElement(element);
        if(ingredients == null) {
            return false;
        }

        for(int i=0; i<ingredients.length(); ++i) {
            boolean flag = findMinReactions(ingredients.charAt(i));
            if(!flag) {
                return flag;
            }
        }

        reactionsPerformed += 1;

        return true;
    }

    private boolean resourcesUsedWithinLimits(int[] resourcesUsed) {
        for(int i=0; i<initial.length(); ++i) {
            char ch = initial.charAt(i);
            if(resourcesAvailable[ch-'A'] < 0) {
                return false;
            }
        }

        return true;
    }

    private String findReactionForElement(char element) {
        for(int i=0; i<reactions.length; ++i) {
            if(reactions[i].charAt(reactions[i].length() - 1) == element) {
                return reactions[i].substring(0,reactions[i].indexOf(SEPARATOR));
            }
        }

        return null;
    }

    public class state {
        int[] resourcesUsed;
        int reactionsDone;

        state() {
            resourcesUsed = new int[MAX_RESOURCES];
            reactionsDone = 0;
        }
    }
}

// NOT DONE
