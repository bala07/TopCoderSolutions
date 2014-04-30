import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: balasubn
 * Date: 11/12/13
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */

public class TagalogDictionary {
    public HashMap<String,Integer> letterValueLookup = new HashMap<String,Integer>();

    public String[] sortWords(String[] words) {

        for(int i=0; i<words.length; ++i) {
            words[i] = normalize(words[i]);
        }

        Arrays.sort(words);


        for(int i=0; i<words.length; ++i) {
            words[i] = denormalize(words[i]);
        }

        return words;
    }

    private String normalize(String word) {

        StringBuilder normalizedWord = new StringBuilder();
        int length = word.length();

        for(int i=0; i<length; ++i) {
            char ch = word.charAt(i);
            if(ch=='k') {
                normalizedWord.append('C');
            }
            else if(ch=='n' && i<length-1 && word.charAt(i+1)=='g') {
                normalizedWord.append("n");
                ++i;
            }
            else if(ch>='a' && ch<='n') {
                normalizedWord.append(Character.toUpperCase(ch));
            }
            else {
                normalizedWord.append(ch);
            }

        }
        return normalizedWord.toString();
    }

    private String denormalize(String word) {
        StringBuilder denormalizedWord = new StringBuilder();
        int length = word.length();

        for(int i=0; i<length; ++i) {
            char ch = word.charAt(i);
            if(ch=='C') {
                denormalizedWord.append('k');
            }
            else if(ch=='n') {
                denormalizedWord.append("ng");
            }
            else if(ch>='A' && ch<='N') {
                denormalizedWord.append(Character.toLowerCase(ch));
            }
            else {
                denormalizedWord.append(ch);
            }

        }

        return denormalizedWord.toString();
    }
}

// DONE