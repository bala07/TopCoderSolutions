public class BrokenButtons
{
    private int MAX_VALUE = (int)5e7;
    private boolean[] brokenButtons = new boolean[10];

    public int minPresses(int reqPage, int[] broken) {

        if(reqPage == 100) {
            return 0;
        }

        for(int button : broken) {
            brokenButtons[button] = true;
        }

        int largestPageSmallerThanReqPage = Integer.MIN_VALUE;
        int smallestPageLargerThanReqPage = Integer.MAX_VALUE;

        for(int page=0; page<=MAX_VALUE; ++page) {
            if(page != reqPage && canGotoPage(page)) {
                if(page < reqPage && page > largestPageSmallerThanReqPage) {
                    largestPageSmallerThanReqPage = page;
                }

                if(page > reqPage && page < smallestPageLargerThanReqPage) {
                    smallestPageLargerThanReqPage = page;
                    break;
                }
            }
        }

        int result1 = largestPageSmallerThanReqPage != Integer.MIN_VALUE ? countButtonPresses(largestPageSmallerThanReqPage) + (reqPage - largestPageSmallerThanReqPage) : Integer.MAX_VALUE;
        int result2 = smallestPageLargerThanReqPage != Integer.MAX_VALUE ? countButtonPresses(smallestPageLargerThanReqPage) + (smallestPageLargerThanReqPage - reqPage) : Integer.MAX_VALUE;
        int result3 = Math.abs(reqPage - 100);
        int result4 = canGotoPage(reqPage) ? countButtonPresses(reqPage) : Integer.MAX_VALUE;

        return Math.min(result1, Math.min(result2, Math.min(result3, result4)));
    }

    private boolean canGotoPage(int page) {

        if(page == 0) {
            return !brokenButtons[0];
        }

        while(page > 0) {
            if(brokenButtons[page%10]) {
                return false;
            }

            page /= 10;
        }

        return true;
    }

    private int countButtonPresses(int page) {
        return Integer.toString(page).length();
    }
}
