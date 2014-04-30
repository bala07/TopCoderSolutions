// DONE

public class DateFormat {

    boolean[][] dp = null;
    public String fromEuropeanToUs(String[] dateList) {
        String[] dates = getDatesFromDateList(dateList);
        dp = new boolean[dates.length][2];

        dp[0][0] = isValidDate(dates[0]);
        dp[0][1] = isValidDate(reverseDate(dates[0]));

        for(int i=1; i<dates.length; ++i) {
            dp[i][0] = isDatePartOfList(dates[i - 1], dates[i], i);
            dp[i][1] = isDatePartOfList(dates[i-1], reverseDate(dates[i]), i);
        }

        if(!dp[dates.length-1][0] && !dp[dates.length-1][1]) {
            return "";
        }

        String result = "";
        if(dp[dates.length-1][0] && dp[dates.length-1][1]) {
            if(dates[dates.length-1].compareTo(reverseDate(dates[dates.length-1])) < 0) {
                result += dates[dates.length-1];
            }
            else {
                result += reverseDate(dates[dates.length-1]);
            }
        }
        else if(dp[dates.length-1][0]) {
            result += dates[dates.length-1];
        }
        else {
            result += reverseDate(dates[dates.length-1]);
        }

        String prevChosenDate = result;

        for(int i=dates.length-2; i>=0; --i) {
            boolean flag1 = dp[i][0] && (compareDates(dates[i], prevChosenDate) < 0);
            boolean flag2 = dp[i][1] && (compareDates(reverseDate(dates[i]), prevChosenDate) < 0);

            if(flag1 && flag2) {
                if(dates[i].compareTo(reverseDate(dates[i])) < 0) {
                    result = dates[i] + " " + result;
                    prevChosenDate = dates[i];
                }
                else {
                    result = reverseDate(dates[i]) + " " + result;
                    prevChosenDate = reverseDate(dates[i]);
                }
            }
            else if(flag1) {
                result = dates[i] + " " + result;
                prevChosenDate = dates[i];
            }
            else {
                result = reverseDate(dates[i]) + " " + result;
                prevChosenDate = reverseDate(dates[i]);
            }
        }

        return result;
    }

    private int compareDates(String date1, String date2) {
        int date1Month = Integer.parseInt(date1.split("/")[0]);
        int date1Day = Integer.parseInt(date1.split("/")[1]);

        int date2Month = Integer.parseInt(date2.split("/")[0]);
        int date2Day = Integer.parseInt(date2.split("/")[1]);

        if( (date1Month < date2Month) || (date1Month == date2Month && date1Day < date2Day) ) {
            return -1;
        }
        else if(date1Month == date2Month && date1Day == date2Day) {
            return 0;
        }

        return 1;
    }

    private boolean isDatePartOfList(String previousDate, String currentDate, int idx) {
        boolean flag1 = (dp[idx-1][0]) && isValidDate(currentDate) && (compareDates(previousDate, currentDate) == -1);
        boolean flag2 = (dp[idx-1][1]) && isValidDate(currentDate) && (compareDates(reverseDate(previousDate), currentDate) == -1);

        return flag1 || flag2;
    }

    private String reverseDate(String date) {
        String reversedDate = "";
        reversedDate += date.split("/")[1];
        reversedDate += "/";
        reversedDate += date.split("/")[0];

        return reversedDate;
    }

    private boolean isValidDate(String date) {
        int month = Integer.parseInt(date.split("/")[0]);
        int day = Integer.parseInt(date.split("/")[1]);

        if(month > 12) {
            return false;
        }

        if(month==2 && day>29) {
            return false;
        }

        if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month ==12) {
            return day <= 31;
        }
        else {
            return day <= 30;
        }
    }

    private String[] getDatesFromDateList(String[] dateList) {
        StringBuffer concatenatedDates = new StringBuffer();
        for(String date : dateList) {
            concatenatedDates.append(date);
        }

        return concatenatedDates.toString().split(" ");
    }


}
