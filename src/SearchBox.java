import com.sun.tools.javac.util.Pair;

/**
 * Created by balasubn on 4/16/14.
 */
public class SearchBox
{
    public int find(String text, String pattern, String wholeWord, int start)
    {
        if(wholeWord.equals("Y"))
        {
            return wholeWordSearch(text, pattern, start);
        }
        else
        {
            return partialWordSearch(text, pattern, start);
        }
    }

    private int partialWordSearch(String text, String pattern, int startIndex)
    {
        int start = startIndex;

        while(start < text.length())
        {
            Range indices = GetNextWordStartAndEnd(text, start);
            start = indices.start;
            int end = indices.end;

            if(start >= text.length())
            {
                break;
            }

            String word = text.substring(start, end);
            if(word.indexOf(pattern) != -1)
            {
                return word.indexOf(pattern) + start;
            }

            start = end + 1;
        }

        return -1;
    }

    private int wholeWordSearch(String text, String pattern, int startIndex)
    {
        int start = startIndex;
        if(start != 0 && text.charAt(start-1) != ' ')
        {
            while(start < text.length() && text.charAt(start-1) != ' ')
            {
                ++start;
            }
        }

        while(start < text.length())
        {
            Range indices = GetNextWordStartAndEnd(text, start);
            start = indices.start;
            int end = indices.end;

            if(start >= text.length())
            {
                break;
            }

            String word = text.substring(start, end);
            if(word.equals(pattern))
            {
                return start;
            }

            start = end + 1;
        }

        return -1;
    }

    Range GetNextWordStartAndEnd(String text, int from)
    {
        int start = from, end;

        while(start < text.length() && text.charAt(start) == ' ')
        {
            ++start;
        }

        end = start + 1;
        while(end < text.length() && text.charAt(end) != ' ')
        {
            ++end;
        }

        return new Range(start, end);
    }

    public class Range
    {
        int start, end;
        Range(int start, int end)
        {
            this.start = start;
            this.end = end;
        }
    }
}
