import java.util.Arrays;
import java.util.HashSet;

public class CyclicWords
{
    public int differentCW(String[] words)
    {
        int count = 0;
        for(int i=0; i<words.length; ++i) {
            if(words[i].equals("")) {
                continue;
            }

            String[] cyclicWords = getCyclicWords(words[i]);
            for(String cyclicWord : cyclicWords) {
                for(int j=i; j<words.length; ++j) {
                    if(words[j].equals(cyclicWord)) {
                        words[j] = "";
                    }
                }
            }

            ++count;
        }

        return count;

    }

    String[] getCyclicWords(String word) {
        String[] cyclicWords = new String[word.length()];
        cyclicWords[0] = word;

        for(int i=1; i<word.length(); ++i) {
            cyclicWords[i] = word.substring(i) + word.substring(0,i);
        }

        return cyclicWords;
    }
}
