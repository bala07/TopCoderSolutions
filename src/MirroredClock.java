import com.sun.jdi.Mirror;

/**
 * Created by Balasubramanian on 5/12/14.
 */
public class MirroredClock
{
    final String SEPARATOR = ":";

    public String whatTimeIsIt(String time)
    {
        int hour = Integer.parseInt(time.split(SEPARATOR)[0]);
        int minute = Integer.parseInt(time.split(SEPARATOR)[1]);

        int correctedHour = (12 - hour);
        int correctedMinute = (60 - minute);

        if(minute > 0)
        {
            correctedHour--;
        }

        return String.format("%02d:%02d", correctedHour % 12, correctedMinute % 60);
    }

    public static void main(String[] args)
    {
        MirroredClock tester = new MirroredClock();

        System.out.println(tester.whatTimeIsIt("10:00"));
    }
}
