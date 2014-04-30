public class SimpleRotationDecoder {


    final int MOD = 27;
    final int PASSWORD_LENGTH = 3;
    final int MIN_WORD_LENGTH = 2;
    final int MAX_WORD_LENGTH = 8;
    final String VOWELS = "aeiou";
    int length = 0;


    public String decode(String cipherText) {
        length = cipherText.length();

        for(char c1='a'; c1<='z'; ++c1) {
            for(char c2='a'; c2<='z'; ++c2) {
                for(char c3='a'; c3<='z'; ++c3) {
                    String password = c1+""+c2+""+c3;
                    String originalText = decipher(cipherText,password);
                    if(isValid(originalText)) {
                        return originalText;
                    }
                }
            }
        }

        return "";
    }

    private boolean isValid(String originalText) {
        return hasNoLeadingSpaces(originalText) && hasNoTrailingSpaces(originalText) &&
                hasNoConsecutiveSpaces(originalText) && hasProperWordLengthLimit(originalText) &&
                hasAtLeastOneVowelPerWord(originalText);


    }

    private boolean hasAtLeastOneVowelPerWord(String originalText) {
        String[] words = originalText.split(" ");
        for(int i=0; i<words.length; ++i) {
            if(!hasVowel(words[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean hasVowel(String word) {
        for(int i=0; i<VOWELS.length(); ++i) {
            if(word.contains(VOWELS.charAt(i)+"")) {
                return true;
            }
        }

        return false;
    }

    private boolean hasProperWordLengthLimit(String originalText) {
        String[] words = originalText.split(" ");
        for(int i=0; i<words.length; ++i) {
            if(words[i].length() < MIN_WORD_LENGTH || words[i].length() > MAX_WORD_LENGTH) {
                return false;
            }
        }

        return true;
    }

    private boolean hasNoConsecutiveSpaces(String originalText) {
        for(int i=0; i<originalText.length(); ++i) {
            char ch = originalText.charAt(i);
            if(ch == ' ' && originalText.charAt(i-1) == ' ') {
                return false;
            }
        }

        return true;
    }

    private boolean hasNoTrailingSpaces(String originalText) {
        return originalText.charAt(length-1) != ' ';
    }

    private boolean hasNoLeadingSpaces(String originalText) {
        return originalText.charAt(0) != ' ';
    }

    private String decipher(String cipherText, String password) {
        StringBuilder decipheredText = new StringBuilder();
        int idx = 0;
        for(int i=0; i<length; ++i, idx=(idx+1)%PASSWORD_LENGTH) {
            char ch1 = cipherText.charAt(i);
            char ch2 = password.charAt(idx);
            int val1 = (ch1==' ' ? 0 : ch1-'a'+1);
            int val2 = (ch2==' ' ? 0 : ch2-'a'+1);

            int modValue = mod(val1-val2);
            if(modValue == 0) {
                decipheredText.append(' ');
            }
            else {
                decipheredText.append((char)(modValue+'a'-1));
            }

        }
        return decipheredText.toString();
    }

    private int mod(int num) {
        int res = num%MOD;
        if(res<0) {
            res = (res+MOD)%MOD;
        }
        return res;
    }

}


// DONE