import java.util.ArrayList;

/**
 * Created by balasubn on 1/15/14.
 */
public class AttendanceShort {
    public String[] shortList(String[] names, String[] attendance) {
        ArrayList<String> studentsNotAllowedForExams = new ArrayList<String>();
        for (int i = 0; i < names.length; i++) {
            int absentDays = countCharOccurence(attendance[i], 'A');
            int presentDays = countCharOccurence(attendance[i], 'P');

            double attendancePercentage = presentDays * 1.0 / (presentDays + absentDays);

            if (attendancePercentage < 75.0) {
                studentsNotAllowedForExams.add(names[i]);
            }
        }

        return convertListToArray(studentsNotAllowedForExams);
    }

    private String[] convertListToArray(ArrayList<String> list) {
        String[] res = new String[list.size()];
        for(int i=0; i<list.size(); ++i) {
            res[i] = list.get(i);
        }

        return res;
    }

    private int countCharOccurence(String str, char ch) {
        int count = 0;

        for(int i=0; i<str.length(); ++i) {
            if(str.charAt(i) == ch) {
                ++count;
            }
        }

        return count;
    }
}
