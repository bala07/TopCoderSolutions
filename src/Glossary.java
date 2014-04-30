import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by balasubn on 1/22/14.
 */
public class Glossary {
    public String[] buildGlossary(String[] items) {

        Pair[] words = new Pair[items.length];

        for(int i=0; i<items.length; ++i) {
            words[i] = new Pair();
            words[i].word1 = items[i];
            words[i].word2 = items[i].toUpperCase();
        }

        Arrays.sort(words);

        ArrayList<WordList> letterWordsMap = prepareLetterWordsMap(words);

        ArrayList<String> glossaryForAtoM = prepareGlossary(letterWordsMap, 'A', 'M');
        ArrayList<String> glossaryForNtoZ = prepareGlossary(letterWordsMap, 'N', 'Z');

        String[] res = new String[Math.max(glossaryForAtoM.size(), glossaryForNtoZ.size())];
        int idx1 = 0, idx2 = 0, idx3 = 0;
        while(idx1 < glossaryForAtoM.size() || idx2 < glossaryForNtoZ.size()) {
            String column1String = idx1<glossaryForAtoM.size() ? glossaryForAtoM.get(idx1++) : spaceString(19);
            String column2String = idx2<glossaryForNtoZ.size() ? glossaryForNtoZ.get(idx2++) : spaceString(19);

            res[idx3++] = column1String + spaceString(2) + column2String;
        }


        return res;
    }

    private String spaceString(int length) {
        String res = "";
        for(int i=0; i<length; ++i) {
            res += ' ';
        }

        return res;
    }

    private ArrayList<String> prepareGlossary(ArrayList<WordList> letterWordsMap, char first, char last) {
        ArrayList<String> glossary = new ArrayList<String>();

        for(WordList wordList : letterWordsMap) {
            if(wordList.letter < first || wordList.letter > last) {
                continue;
            }
            glossary.add(wordList.letter + spaceString(18));
            glossary.add(dashString(19));
            for(String word : wordList.words) {
                glossary.add(spaceString(2) + word + spaceString(17-word.length()));
            }
        }

        return glossary;
    }

    private String dashString(int length) {
        String res = "";
        for(int i=0; i<length; ++i) {
            res += '-';
        }

        return res;
    }

    private ArrayList<WordList> prepareLetterWordsMap(Pair[] words) {
        ArrayList<WordList> wordLists = new ArrayList<WordList>();
        for(int i=0; i<words.length; ++i) {
            if(i==0 || words[i].word2.charAt(0) != words[i-1].word2.charAt(0)) {
                wordLists.add(new WordList(words[i].word2.charAt(0)));
            }
            wordLists.get(wordLists.size()-1).words.add(words[i].word1);
        }

        return wordLists;
    }

    class WordList {
        char letter;
        ArrayList<String> words;

        WordList(char letter) {
            this.letter = letter;
            words = new ArrayList<String>();
        }
    }

    class Pair implements Comparable<Pair>{
        String word1,word2;

        @Override
        public int compareTo(Pair pair) {
            return this.word2.compareTo(pair.word2);
        }
    }

}
