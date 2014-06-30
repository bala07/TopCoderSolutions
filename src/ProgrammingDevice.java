import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Balasubramanian on 6/26/14.
 */
public class ProgrammingDevice
{
    public int minPackets(int[] offset, int[] size, int maxData)
    {

        ArrayList<Entry> entries = new ArrayList<>();
        int totalPacketsRequired = 0;

        for(int i=0; i<offset.length; ++i)
        {
            entries.add(new Entry(offset[i], size[i]));
        }

        Collections.sort(entries);

        int i=0;

        while(i < entries.size())
        {
            Entry entry1 = entries.get(i);
            long packetsRequired = (int)Math.ceil(entry1.size * 1.0 / maxData);
            long end = entry1.offset + packetsRequired * maxData - 1;

            int j = i+1;
            while(j < entries.size())
            {
                Entry entry2 = entries.get(j);

                if(entry2.offset > end)
                {
                    break;
                }
                else if(entry2.offset <= end && end <= entry2.offset + entry2.size - 1)
                {
                    entry2.size = entry2.size - (end + 1 - entry2.offset);
                    entry2.offset = end + 1;
                    break;
                }
                else
                {
                    entries.remove(j);
                }
            }

            totalPacketsRequired += packetsRequired;
            i = j;
        }

        return totalPacketsRequired;
    }

    public class Entry implements Comparable<Entry>
    {
        public long offset;
        public long size;

        public Entry(int offset, int size)
        {
            this.offset = offset;
            this.size = size;
        }

        @Override
        public int compareTo(Entry e)
        {
            return (int)(this.offset - e.offset);
        }
    }

    public static void main(String[] args)
    {
        ProgrammingDevice tester = new ProgrammingDevice();

        System.out.println(tester.minPackets(
                new int[]{ 999999999, (int)1e9 },
                new int[]{ 1, (int)1e9 },
                (int)2e9
        ));
    }

}
